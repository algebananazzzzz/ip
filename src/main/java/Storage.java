import java.io.*;
import java.util.*;

public class Storage {
    private final String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<Task> load() throws FloydAIException {
        ArrayList<Task> tasks = new ArrayList<>();
        File file = new File(filePath);
        if (!file.exists()) {
            return tasks; // return empty if no save file
        }

        try (Scanner sc = new Scanner(file)) {
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] parts = line.split(" \\| ");
                TaskType type = parseType(parts[0].trim());

                boolean isDone = parts[1].trim().equals("1");
                Task task;

                switch (type) {
                    case TODO:
                        task = new Todo(parts[2].trim());
                        break;
                    case DEADLINE:
                        task = new Deadline(parts[2].trim(), parts[3].trim());
                        break;
                    case EVENT:
                        task = new Event(parts[2].trim(), parts[3].trim(), parts[4].trim());
                        break;
                    default:
                        throw new FloydAIException("Unknown task type: " + type);
                }

                if (isDone) {
                    task.markAsDone();
                }
                tasks.add(task);
            }
        } catch (IOException e) {
            throw new FloydAIException("Error loading file: " + e.getMessage());
        }
        return tasks;
    }

    public void save(ArrayList<Task> tasks) throws FloydAIException {
        try {
            File file = new File(filePath);
            File parent = file.getParentFile();
            if (parent != null && !parent.exists()) {
                parent.mkdirs(); // ✅ create ./data/ if it doesn’t exist
            }

            try (FileWriter fw = new FileWriter(file)) {
                for (Task t : tasks) {
                    fw.write(serializeTask(t) + System.lineSeparator());
                }
            }
        } catch (IOException e) {
            throw new FloydAIException("Error saving file: " + e.getMessage());
        }
    }


    private String serializeTask(Task task) {
        StringBuilder sb = new StringBuilder();
        TaskType type = task.getType();
        sb.append(type.getIcon()).append(" | ")
                .append(task.isDone() ? "1" : "0").append(" | ")
                .append(task.getDescription());

        if (type == TaskType.DEADLINE) {
            sb.append(" | ").append(((Deadline) task).getBy());
        } else if (type == TaskType.EVENT) {
            sb.append(" | ").append(((Event) task).getFrom())
                    .append(" | ").append(((Event) task).getTo());
        }
        return sb.toString();
    }

    private TaskType parseType(String icon) throws FloydAIException {
        for (TaskType t : TaskType.values()) {
            if (t.getIcon().equals(icon)) {
                return t;
            }
        }
        throw new FloydAIException("Unknown task type: " + icon);
    }
}
