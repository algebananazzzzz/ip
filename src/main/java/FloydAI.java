import java.util.Scanner;

public class FloydAI {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Greet the user
        System.out.println("____________________________________________________________");
        System.out.println(" Hello! I'm FloydAI");
        System.out.println(" What can I do for you?");
        System.out.println("____________________________________________________________");

        while (true) {
            String input = sc.nextLine(); // read user input

            if (input.equals("bye")) {
                System.out.println("____________________________________________________________");
                System.out.println(" Bye. Hope to see you again soon!");
                System.out.println("____________________________________________________________");
                break; // exit loop
            }

            // Echo the input
            System.out.println("____________________________________________________________");
            System.out.println(" " + input);
            System.out.println("____________________________________________________________");
        }

        sc.close();
    }
}
