package floydai.command;
import floydai.FloydAIException;
import floydai.storage.Storage;
import floydai.task.Task;
import floydai.ui.UI;
import floydai.task.TaskList;
import floydai.task.Event;
import java.util.ArrayList;

public class AddEventCommand extends Command {
    private final String input;

    public AddEventCommand(String input) {
        this.input = input;
    }

    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) throws FloydAIException {
        try {
            String[] parts = input.substring(5).trim().split("/from", 2);
            String desc = parts[0].trim();
            String[] timeParts = parts[1].split("/to", 2);
            String from = timeParts[0].trim();
            String to = timeParts[1].trim();
            if (desc.isEmpty() || from.isEmpty() || to.isEmpty()) {
                throw new FloydAIException("task.Event description or time cannot be empty.");
            }
            Task t = new Event(desc, from, to);
            tasks.add(t);
            storage.save(new ArrayList<>(tasks.getAll()));
            ui.showAddTask(t, tasks.size());
        } catch (Exception e) {
            throw new FloydAIException("Usage: event <description> /from <time> /to <time>");
        }
    }
}
