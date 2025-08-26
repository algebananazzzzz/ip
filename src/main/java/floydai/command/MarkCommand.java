package floydai.command;

import floydai.FloydAIException;
import floydai.storage.Storage;
import floydai.ui.UI;
import floydai.task.TaskList;

import java.io.IOException;
import java.util.ArrayList;
public class MarkCommand extends Command {
    private final int index;

    public MarkCommand(String input) throws FloydAIException {
        try {
            this.index = Integer.parseInt(input.split(" ")[1]) - 1;
        } catch (Exception e) {
            throw new FloydAIException("Please provide a valid task number to mark.");
        }
    }

    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) throws FloydAIException, IOException {
        if (index < 0 || index >= tasks.size()) {
            throw new FloydAIException("task.Task number out of range.");
        }
        tasks.mark(index);
        storage.save(new ArrayList<>(tasks.getAll()));
        ui.showMessage("Nice! I've marked this task as done:\n  " + tasks.get(index));
    }
}
