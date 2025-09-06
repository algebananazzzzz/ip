package floydai.ui;

import java.util.List;
import java.util.Scanner;

import floydai.task.Task;

/**
 * Handles all interactions with the user, including displaying messages,
 * errors, and tasks, as well as reading user commands.
 * <p>
 * Provides high-level methods for task-related feedback and lower-level
 * rendering helpers to display boxed messages consistently.
 */
public class UI {
    private final Scanner scanner = new Scanner(System.in);
    private MainWindow mainWindow;

    public void setMainWindow(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
    }

    /**
     * Displays a welcome message when the application starts.
     */
    public void showWelcome() {
        showBox("""
✊🏽 Hello, I'm FloydAI.
I was created in loving memory of George Floyd — a reminder to breathe, to live, and to never forget.

What would you like me to remember for you?""");
    }

    /**
     * Displays an error message when tasks cannot be loaded,
     * along with a reason.
     *
     * @param message The reason why loading tasks failed.
     */
    public void showLoadingError(String message) {
        showBox("⚠️ Trouble breathing while loading tasks...\n"
                + "Starting with a fresh list of dreams.\n\nReason: " + message);
    }

    /**
     * Reads a line of input from the user and trims it.
     *
     * @return The trimmed command string entered by the user.
     */
    public String readCommand() {
        return scanner.nextLine().trim();
    }

    // === Printing helpers ===

    /**
     * Prints a horizontal line separator.
     */
    public void showLine() {
        System.out.println("____________________________________________________________");
    }

    /**
     * Displays an error message in a box.
     *
     * @param message The error message to display.
     */
    public void showError(String message) {
        showBox("💔 Error: " + message + "\nTake a deep breath and try again.");
    }

    /**
     * Displays a general informational message in a box.
     *
     * @param message The message to display.
     */
    public void showMessage(String message) {
        showBox(message);
    }

    /**
     * Displays a confirmation message after adding a new task.
     *
     * @param t     The task that was added.
     * @param count The total number of tasks currently in the list.
     */
    public void showAddTask(Task t, int count) {
        showBox("✨ A new breath, a new memory saved:\n  " + t
                + "\nNow you have " + count + " task(s) in your list of dreams.");
    }

    /**
     * Displays all tasks in the list in a numbered format.
     * If the list is empty, shows a corresponding message.
     *
     * @param tasks The list of tasks to display.
     */
    public void showTasks(List<Task> tasks) {
        if (tasks == null || tasks.isEmpty()) {
            showBox("🌫️ Your list is empty... nothing to breathe into yet.");
            return;
        }
        StringBuilder sb = new StringBuilder("Here are the tasks in your list:\n");
        for (int i = 0; i < tasks.size(); i++) {
            sb.append(" ").append(i + 1).append(".").append(tasks.get(i)).append("\n");
        }
        showBox(sb.toString().trim());
    }

    // === Low-level box renderer ===

    /**
     * Renders a given message inside a box with horizontal separators.
     *
     * @param msg The message to display inside the box.
     */
    private void showBox(String msg) {
        if (mainWindow != null) {
            mainWindow.addFloydDialog(msg);
        } else {
            showLine();
            System.out.println(" " + msg.replace("\n", "\n "));
            showLine();
        }
    }
}
