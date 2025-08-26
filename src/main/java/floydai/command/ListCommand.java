package floydai.command;

import floydai.storage.Storage;
import floydai.ui.UI;
import floydai.task.TaskList;

public class ListCommand extends Command {
    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) {
        ui.showTasks(tasks.getAll());
    }
}
