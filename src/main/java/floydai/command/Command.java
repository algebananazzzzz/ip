package floydai.command;

import floydai.FloydAIException;
import floydai.storage.Storage;
import floydai.task.TaskList;
import floydai.ui.UI;

public abstract class Command {
    public abstract void execute(TaskList tasks, UI ui, Storage storage) throws FloydAIException, java.io.IOException;
    public boolean isExit() {
        return false;
    }
}
