package floydai.parser;

import floydai.FloydAIException;
import floydai.command.*;

public class Parser {
    public static Command parse(String input) throws FloydAIException {
        if (input.equals("bye")) {
            return new ExitCommand();
        } else if (input.equals("list")) {
            return new ListCommand();
        } else if (input.startsWith("mark ")) {
            return new MarkCommand(input);
        } else if (input.startsWith("unmark ")) {
            return new UnmarkCommand(input);
        } else if (input.startsWith("todo")) {
            return new AddTodoCommand(input);
        } else if (input.startsWith("deadline")) {
            return new AddDeadlineCommand(input);
        } else if (input.startsWith("event")) {
            return new AddEventCommand(input);
        } else if (input.startsWith("find")) {
            return new FindCommand(input);
        } else if (input.startsWith("delete")) {
            return new DeleteCommand(input);
        } else {
            throw new FloydAIException("I don’t understand that command.");
        }
    }
}
