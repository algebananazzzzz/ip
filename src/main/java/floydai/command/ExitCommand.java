package floydai.command;
import floydai.storage.Storage;
import floydai.ui.UI;
import floydai.task.TaskList;

public class ExitCommand extends Command {
    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) {
        ui.showMessage("Bye. Hope to see you again soon!");
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
