package floydai.command;

import floydai.FloydAIException;
import floydai.storage.Storage;
import floydai.ui.UI;
import floydai.task.TaskList;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Command to mark a task as done in the TaskList.
 */
public class MarkCommand extends Command {
    private final int index;

    /**
     * Constructs a MarkCommand from user input.
     *
     * @param input user input string, e.g., "mark 2"
     * @throws FloydAIException if the input is invalid or cannot parse task number
     */
    public MarkCommand(String input) throws FloydAIException {
        try {
            this.index = Integer.parseInt(input.split(" ")[1]) - 1;
        } catch (Exception e) {
            throw new FloydAIException("Please provide a valid task number to mark.");
        }
    }

    /**
     * Executes the mark command, marking the task at the specified index as done.
     *
     * @param tasks   the TaskList containing tasks
     * @param ui      the UI to display feedback
     * @param storage the Storage to save changes
     * @throws FloydAIException if task index is out of range
     * @throws IOException      if saving to storage fails
     */
    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) throws FloydAIException, IOException {
        if (index < 0 || index >= tasks.size()) {
            throw new FloydAIException("Task number out of range.");
        }
        tasks.mark(index);
        storage.save(new ArrayList<>(tasks.getAll()));
        ui.showMessage("Nice! I've marked this task as done:\n  " + tasks.get(index));
    }
}
