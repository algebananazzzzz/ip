import floydai.FloydAIException;
import floydai.command.AddTodoCommand;
import floydai.command.Command;
import floydai.command.MarkCommand;
import floydai.parser.Parser;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ParserTest {

    @Test
    void testParseTodoCommand() throws FloydAIException {
        Command cmd = Parser.parse("todo read book");
        assertTrue(cmd instanceof AddTodoCommand);
    }

    @Test
    void testParseInvalidCommandThrows() {
        Exception exception = assertThrows(FloydAIException.class, () -> {
            Parser.parse("foobar");
        });

        String expectedMessage = "I don’t understand that command.";
        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    void testParseMarkCommand() throws FloydAIException {
        Command cmd = Parser.parse("mark 2");
        assertTrue(cmd instanceof MarkCommand);
    }
}
