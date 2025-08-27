package floydai.parser;

import floydai.FloydAIException;
import floydai.command.*;

/**
 * Parser class responsible for interpreting user input
 * and converting it into a corresponding {@link Command}.
 */
public class Parser {

    /**
     * Parses a user input string and returns the corresponding {@link Command}.
     *
     * @param input the raw user input
     * @return a Command object representing the requested action
     * @throws FloydAIException if the input does not match any known command
     */
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
