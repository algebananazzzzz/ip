package floydai;

import floydai.command.Command;
import floydai.parser.Parser;
import floydai.storage.Storage;
import floydai.task.TaskList;
import floydai.ui.UI;

import java.io.IOException;

/**
 * Main class for the FloydAI chatbot application.
 * Responsible for initializing the application, handling user input,
 * and executing commands in a loop until exit.
 */
public class FloydAI {
    private final Storage storage;
    private TaskList tasks;
    private final UI ui;

    /**
     * Constructs a FloydAI instance with the given file path for storage.
     * Loads tasks from storage or starts with an empty task list if loading fails.
     *
     * @param filePath path to the save file
     */
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

    /**
     * Runs the main loop of the chatbot, reading user commands and executing them.
     * Continues until an exit command is received.
     */
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

    /**
     * Entry point of the application.
     *
     * @param args command line arguments (not used)
     */
    public static void main(String[] args) {
        new FloydAI("./data/FLOYDAI.txt").run();
    }
}
