
import floydai.FloydAIException;
import floydai.task.Deadline;
import floydai.task.Event;
import floydai.task.Task;
import floydai.task.Todo;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TaskTest {

    @Test
    void testTodoMarkUnmark() {
        Task t = new Todo("read book");
        assertFalse(t.isDone());
        t.markAsDone();
        assertTrue(t.isDone());
        t.markAsNotDone();
        assertFalse(t.isDone());
        assertEquals("[T][ ] read book", t.toString());
    }

    @Test
    void testDeadlineToString() throws FloydAIException {
        Task t = new Deadline("return book", "2025-08-26");
        assertEquals("[D][ ] return book (by: Aug 26 2025)", t.toString());
    }

    @Test
    void testEventToString() throws FloydAIException {
        Task t = new Event("project meeting", "2025-08-26", "2025-08-27");
        assertEquals("[E][ ] project meeting (from: Aug 26 2025 to: Aug 27 2025)", t.toString());
    }
}
