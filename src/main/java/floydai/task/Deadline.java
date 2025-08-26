package floydai.task;

import floydai.FloydAIException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    private LocalDate by; // store as LocalDate

    private static final DateTimeFormatter INPUT_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter OUTPUT_FORMAT = DateTimeFormatter.ofPattern("MMM d yyyy");

    public Deadline(String description, String by) throws FloydAIException {
        super(description, TaskType.DEADLINE);
        try {
            this.by = LocalDate.parse(by, INPUT_FORMAT);
        } catch (DateTimeParseException e) {
            throw new FloydAIException("Invalid date format! Use yyyy-MM-dd (e.g., 2019-12-02).");
        }
    }

    public LocalDate getBy() { return by; }

    @Override
    public String toString() {
        return super.toString() + " (by: " + by.format(OUTPUT_FORMAT) + ")";
    }
}
