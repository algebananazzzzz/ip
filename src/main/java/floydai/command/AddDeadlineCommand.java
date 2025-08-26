package floydai.command;
import floydai.FloydAIException;
import floydai.storage.Storage;
import floydai.task.Task;
import floydai.ui.UI;
import floydai.task.TaskList;
import floydai.task.Deadline;
import java.util.ArrayList;


public class AddDeadlineCommand extends Command {
    private final String input;

    public AddDeadlineCommand(String input) {
        this.input = input;
    }

    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) throws FloydAIException {
        try {
            String[] parts = input.substring(8).trim().split("/by", 2);
            String desc = parts[0].trim();
            String by = parts[1].trim();
            if (desc.isEmpty() || by.isEmpty()) {
                throw new FloydAIException("task.Deadline description or time cannot be empty.");
            }
            Task t = new Deadline(desc, by);
            tasks.add(t);
            storage.save(new ArrayList<>(tasks.getAll()));
            ui.showAddTask(t, tasks.size());
        } catch (Exception e) {
            throw new FloydAIException("Usage: deadline <description> /by <time>");
        }
    }
}
