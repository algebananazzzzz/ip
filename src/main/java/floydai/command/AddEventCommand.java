package floydai.command;

import floydai.FloydAIException;
import floydai.storage.Storage;
import floydai.task.Task;
import floydai.ui.UI;
import floydai.task.TaskList;
import floydai.task.Event;

import java.util.ArrayList;

/**
 * Command to add a new Event task to the task list.
 */
public class AddEventCommand extends Command {
    private final String input;

    /**
     * Constructs an AddEventCommand with the raw user input.
     *
     * @param input the full user input starting with "event"
     */
    public AddEventCommand(String input) {
        this.input = input;
    }

    /**
     * Executes the command by creating an Event task, adding it to the task list,
     * saving the updated list to storage, and showing the added task to the user.
     * Expected format: "event <description> /from <start-date> /to <end-date>"
     *
     * @param tasks   the TaskList to add the Event to
     * @param ui      the UI for interacting with the user
     * @param storage the Storage for persisting tasks
     * @throws FloydAIException if the input format is invalid or any field is empty
     */
    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) throws FloydAIException {
        try {
            String[] parts = input.substring(5).trim().split("/from", 2);
            String desc = parts[0].trim();
            String[] timeParts = parts[1].split("/to", 2);
            String from = timeParts[0].trim();
            String to = timeParts[1].trim();
            if (desc.isEmpty() || from.isEmpty() || to.isEmpty()) {
                throw new FloydAIException("Event description or time cannot be empty.");
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
