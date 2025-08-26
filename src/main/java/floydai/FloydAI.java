package floydai;

import floydai.command.Command;
import floydai.parser.Parser;
import floydai.storage.Storage;
import floydai.task.TaskList;
import floydai.ui.UI;

import java.io.IOException;

public class FloydAI {
    private Storage storage;
    private TaskList tasks;
    private final UI ui;

    public FloydAI(String filePath) {
        ui = new UI();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (Exception e) {
            ui.showLoadingError(e.getMessage());
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;

        while (!isExit) {
            String fullCommand = ui.readCommand();
            try {
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (FloydAIException | IOException e) {
                ui.showError(e.getMessage());
                ui.showLine();
            }
        }
    }

    public static void main(String[] args) {
        new FloydAI("./data/FLOYDAI.txt").run();
    }
}
