package floydai.command;

import floydai.FloydAIException;
import floydai.storage.Storage;
import floydai.task.Task;
import floydai.ui.UI;
import floydai.task.TaskList;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Command to delete a task from the task list.
 */
public class DeleteCommand extends Command {
    private final int index;

    /**
     * Constructs a DeleteCommand by parsing the task index from user input.
     *
     * @param input the full user input starting with "delete"
     * @throws FloydAIException if the input is invalid or missing the index
     */
    public DeleteCommand(String input) throws FloydAIException {
        try {
            this.index = Integer.parseInt(input.split(" ")[1]) - 1;
        } catch (Exception e) {
            throw new FloydAIException("Please provide a valid task number to delete.");
        }
    }

    /**
     * Executes the command by removing the specified task from the task list,
     * saving the updated list to storage, and showing a message to the user.
     *
     * @param tasks   the TaskList from which to delete a task
     * @param ui      the UI for interacting with the user
     * @param storage the Storage for persisting changes
     * @throws FloydAIException if the task index is out of range
     * @throws IOException      if there is an error saving the updated task list
     */
    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) throws FloydAIException, IOException {
        if (index < 0 || index >= tasks.size()) {
            throw new FloydAIException("Task number out of range.");
        }
        Task removed = tasks.remove(index);
        storage.save(new ArrayList<>(tasks.getAll()));
        ui.showMessage("Noted. I've removed this task:\n  " + removed +
                "\nNow you have " + tasks.size() + " tasks in the list.");
    }
}
