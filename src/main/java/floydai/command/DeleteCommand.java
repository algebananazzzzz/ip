package floydai.command;
import floydai.FloydAIException;
import floydai.storage.Storage;
import floydai.task.Task;
import floydai.ui.UI;
import floydai.task.TaskList;

import java.io.IOException;
import java.util.ArrayList;


public class DeleteCommand extends Command {
    private final int index;

    public DeleteCommand(String input) throws FloydAIException {
        try {
            this.index = Integer.parseInt(input.split(" ")[1]) - 1;
        } catch (Exception e) {
            throw new FloydAIException("Please provide a valid task number to delete.");
        }
    }

    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) throws FloydAIException, IOException {
        if (index < 0 || index >= tasks.size()) {
            throw new FloydAIException("task.Task number out of range.");
        }
        Task removed = tasks.remove(index);
        storage.save(new ArrayList<>(tasks.getAll()));
        ui.showMessage("Noted. I've removed this task:\n  " + removed +
                "\nNow you have " + tasks.size() + " tasks in the list.");
    }
}
