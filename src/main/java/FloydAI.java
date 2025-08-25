import java.util.ArrayList;
import java.util.Scanner;

public class FloydAI {
    private static ArrayList<Task> tasks = new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Greeting
        printBox("Hello! I'm FloydAI\nWhat can I do for you?");

        while (true) {
            String input = sc.nextLine().trim();

            try {
                if (input.equals("bye")) {
                    printBox("Bye. Hope to see you again soon!");
                    break;
                } else if (input.equals("list")) {
                    handleList();
                } else if (input.startsWith("mark ")) {
                    handleMark(input);
                } else if (input.startsWith("unmark ")) {
                    handleUnmark(input);
                } else if (input.startsWith("todo")) {
                    handleTodo(input);
                } else if (input.startsWith("deadline")) {
                    handleDeadline(input);
                } else if (input.startsWith("event")) {
                    handleEvent(input);
                } else if (input.startsWith("delete")) {   // ✅ new feature
                    handleDelete(input);
                } else {
                    throw new FloydAIException("I don’t understand that command. Try 'todo', 'deadline', 'event', 'list', 'mark', or 'unmark'.");
                }
            } catch (FloydAIException e) {
                printBox("Error: " + e.getMessage());
            }
        }

        sc.close();
    }

    // === Handlers ===

    private static void handleList() throws FloydAIException {
        if (tasks.isEmpty()) {
            throw new FloydAIException("Your task list is empty! Add something first.");
        }
        StringBuilder sb = new StringBuilder("Here are the tasks in your list:\n");
        for (int i = 0; i < tasks.size(); i++) {
            sb.append(" ").append(i + 1).append(".").append(tasks.get(i)).append("\n");
        }
        printBox(sb.toString().trim());
    }

    private static void handleMark(String input) throws FloydAIException {
        int index = parseIndex(input.substring(5));
        Task t = tasks.get(index);
        t.markAsDone();
        printBox("Nice! I've marked this task as done:\n  " + t);
    }

    private static void handleUnmark(String input) throws FloydAIException {
        int index = parseIndex(input.substring(7));
        Task t = tasks.get(index);
        t.markAsNotDone();
        printBox("OK, I've marked this task as not done yet:\n  " + t);
    }

    private static void handleTodo(String input) throws FloydAIException {
        String desc = input.replaceFirst("todo", "").trim();
        if (desc.isEmpty()) {
            throw new FloydAIException("The description of a todo cannot be empty.");
        }
        Task t = new Todo(desc);
        tasks.add(t);
        printAddedTask(t);
    }

    private static void handleDeadline(String input) throws FloydAIException {
        if (!input.contains("/by")) {
            throw new FloydAIException("A deadline must have '/by <time>'.");
        }
        String[] parts = input.split("/by", 2);
        String desc = parts[0].replaceFirst("deadline", "").trim();
        String by = parts[1].trim();
        if (desc.isEmpty() || by.isEmpty()) {
            throw new FloydAIException("Deadline requires both a description and a '/by' time.");
        }
        Task t = new Deadline(desc, by);
        tasks.add(t);
        printAddedTask(t);
    }

    private static void handleEvent(String input) throws FloydAIException {
        if (!input.contains("/from") || !input.contains("/to")) {
            throw new FloydAIException("An event must have '/from <time> /to <time>'.");
        }
        String[] parts1 = input.split("/from", 2);
        String desc = parts1[0].replaceFirst("event", "").trim();
        String[] parts2 = parts1[1].split("/to", 2);
        String from = parts2[0].trim();
        String to = parts2[1].trim();
        if (desc.isEmpty() || from.isEmpty() || to.isEmpty()) {
            throw new FloydAIException("Event requires description, '/from', and '/to' times.");
        }
        Task t = new Event(desc, from, to);
        tasks.add(t);
        printAddedTask(t);
    }

    private static void handleDelete(String input) throws FloydAIException {
        int index = parseIndex(input.substring(6));
        Task removed = tasks.remove(index);
        printBox("Noted. I've removed this task:\n  " + removed + "\nNow you have " + tasks.size() + " tasks in the list.");
    }

    // === Helpers ===

    private static int parseIndex(String numStr) throws FloydAIException {
        try {
            int idx = Integer.parseInt(numStr.trim()) - 1;
            if (idx < 0 || idx >= tasks.size()) {
                throw new FloydAIException("Task number out of range.");
            }
            return idx;
        } catch (NumberFormatException e) {
            throw new FloydAIException("Please provide a valid task number.");
        }
    }

    private static void printAddedTask(Task t) {
        printBox("Got it. I've added this task:\n  " + t + "\nNow you have " + tasks.size() + " tasks in the list.");
    }

    private static void printBox(String msg) {
        System.out.println("____________________________________________________________");
        System.out.println(" " + msg.replace("\n", "\n "));
        System.out.println("____________________________________________________________");
    }
}
