import java.io.*;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

public class Storage {
    private final Path filePath;

    public Storage(String filePath) {
        this.filePath = Paths.get(filePath);
    }

    // Load tasks from disk
    public List<Task> load() {
        List<Task> tasks = new ArrayList<>();
        try {
            if (!Files.exists(filePath)) {
                // create parent directories if needed
                if (filePath.getParent() != null) {
                    Files.createDirectories(filePath.getParent());
                }
                Files.createFile(filePath);
            }

            BufferedReader reader = Files.newBufferedReader(filePath);
            String line;
            while ((line = reader.readLine()) != null) {
                Task t = parseLine(line);
                if (t != null) tasks.add(t);
            }
            reader.close();
        } catch (Exception e) {
            System.out.println("Error loading data: " + e.getMessage());
            System.out.println("Starting with an empty task list.");
        }
        return tasks;
    }

    // Save tasks to disk
    public void save(List<Task> tasks) {
        try (BufferedWriter writer = Files.newBufferedWriter(filePath)) {
            for (Task t : tasks) {
                writer.write(serializeTask(t));
                writer.newLine();
            }
        } catch (Exception e) {
            System.out.println("Error saving data: " + e.getMessage());
        }
    }

    // Convert task to line string
    private String serializeTask(Task t) {
        // Example format: T|1|read book or D|0|return book|Sunday
        StringBuilder sb = new StringBuilder();
        sb.append(t.getType()).append("|").append(t.isDone() ? "1" : "0").append("|").append(t.getDescription());
        if (t instanceof Deadline) sb.append("|").append(((Deadline) t).getBy());
        if (t instanceof Event) sb.append("|").append(((Event) t).getFrom()).append("|").append(((Event) t).getTo());
        return sb.toString();
    }

    // Convert line string back to Task
    private Task parseLine(String line) {
        try {
            String[] parts = line.split("\\|");
            String type = parts[0];
            boolean done = parts[1].equals("1");
            String desc = parts[2];

            Task t = null;
            switch (type) {
                case "T":
                    t = new Todo(desc);
                    break;
                case "D":
                    t = new Deadline(desc, parts[3]);
                    break;
                case "E":
                    t = new Event(desc, parts[3], parts[4]);
                    break;
            }
            if (t != null && done) t.markAsDone();
            return t;
        } catch (Exception e) {
            System.out.println("Error parsing line: " + line);
            return null;
        }
    }
}
