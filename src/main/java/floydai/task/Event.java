package floydai.task;

import floydai.FloydAIException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task {
    private LocalDate from;
    private LocalDate to;

    private static final DateTimeFormatter INPUT_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter OUTPUT_FORMAT = DateTimeFormatter.ofPattern("MMM d yyyy");

    public Event(String description, String fromStr, String toStr) throws FloydAIException {
        super(description, TaskType.EVENT);
        try {
            this.from = LocalDate.parse(fromStr, INPUT_FORMAT);
            this.to = LocalDate.parse(toStr, INPUT_FORMAT);
        } catch (DateTimeParseException e) {
            throw new FloydAIException("Invalid date format! Use yyyy-MM-dd (e.g., 2019-12-02).");
        }
    }

    public LocalDate getFrom() { return from;  }
    public LocalDate getTo() { return to; }

    @Override
    public String toString() {
        return super.toString() + " (from: " + from.format(OUTPUT_FORMAT) + " to: " + to.format(OUTPUT_FORMAT) + ")";
    }
}
