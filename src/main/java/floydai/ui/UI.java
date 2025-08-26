package floydai.ui;

import floydai.task.Task;

import java.util.List;
import java.util.Scanner;

public class UI {
    private final Scanner scanner = new Scanner(System.in);

    // === High-level interactions ===
    public void showWelcome() {
        showBox("Hello! I'm floydai.FloydAI\nWhat can I do for you?");
    }

    public void showLoadingError(String message) {
        showBox("Error loading tasks! Starting with an empty list.\nReason: " + message);
    }

    public String readCommand() {
        return scanner.nextLine().trim();
    }

    // === Printing helpers ===
    public void showLine() {
        System.out.println("____________________________________________________________");
    }

    public void showError(String message) {
        showBox("Error: " + message);
    }

    public void showMessage(String message) {
        showBox(message);
    }

    public void showAddTask(Task t, int count) {
        showBox("Got it. I've added this task:\n  " + t
                + "\nNow you have " + count + " tasks in the list.");
    }

    public void showTasks(List<Task> tasks) {
        if (tasks == null || tasks.isEmpty()) {
            showBox("Your task list is empty! Add something first.");
            return;
        }
        StringBuilder sb = new StringBuilder("Here are the tasks in your list:\n");
        for (int i = 0; i < tasks.size(); i++) {
            sb.append(" ").append(i + 1).append(".").append(tasks.get(i)).append("\n");
        }
        showBox(sb.toString().trim());
    }

    // === Low-level box renderer ===
    private void showBox(String msg) {
        System.out.println("____________________________________________________________");
        System.out.println(" " + msg.replace("\n", "\n "));
        System.out.println("____________________________________________________________");
    }
}
