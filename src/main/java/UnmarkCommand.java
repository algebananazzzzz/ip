import java.io.IOException;
import java.util.ArrayList;

public class UnmarkCommand extends Command {
    private final int index;

    public UnmarkCommand(String input) throws FloydAIException {
        try {
            this.index = Integer.parseInt(input.split(" ")[1]) - 1;
        } catch (Exception e) {
            throw new FloydAIException("Please provide a valid task number to unmark.");
        }
    }

    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) throws FloydAIException, IOException {
        if (index < 0 || index >= tasks.size()) {
            throw new FloydAIException("Task number out of range.");
        }
        tasks.unmark(index);
        storage.save(new ArrayList<>(tasks.getAll()));
        ui.showMessage("OK, I've marked this task as not done yet:\n  " + tasks.get(index));
    }
}
