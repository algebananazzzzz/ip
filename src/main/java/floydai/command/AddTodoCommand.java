package floydai.command;

import floydai.FloydAIException;
import floydai.storage.Storage;
import floydai.task.Task;
import floydai.ui.UI;
import floydai.task.TaskList;
import floydai.task.Todo;

import java.io.IOException;
import java.util.ArrayList;

public class AddTodoCommand extends Command {
    private final String input;

    public AddTodoCommand(String input) {
        this.input = input;
    }

    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) throws FloydAIException, IOException {
        String desc = input.substring(4).trim();
        if (desc.isEmpty()) {
            throw new FloydAIException("The description of a todo cannot be empty.");
        }
        Task t = new Todo(desc);
        tasks.add(t);
        storage.save(new ArrayList<>(tasks.getAll()));
        ui.showAddTask(t, tasks.size());
    }
}
