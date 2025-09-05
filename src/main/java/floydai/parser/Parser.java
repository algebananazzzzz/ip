package floydai.parser;

import floydai.FloydException;
import floydai.command.AddDeadlineCommand;
import floydai.command.AddEventCommand;
import floydai.command.AddTodoCommand;
import floydai.command.Command;
import floydai.command.DeleteCommand;
import floydai.command.ExitCommand;
import floydai.command.FindCommand;
import floydai.command.ListCommand;
import floydai.command.MarkCommand;
import floydai.command.UnmarkCommand;

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
     * @throws FloydException if the input does not match any known command
     */
    public static Command parse(String input) throws FloydException {
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
            throw new FloydException("I don’t understand that command.");
        }
    }
}
