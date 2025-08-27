package floydai.task;

import floydai.FloydAIException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents an event task with a start date and an end date.
 * <p>
 * Inherits from {@link Task} and adds {@code from} and {@code to} dates.
 * Dates are stored as {@link LocalDate} objects and formatted for display.
 */
public class Event extends Task {
    private final LocalDate from;
    private final LocalDate to;

    /** Formatter for parsing input dates (yyyy-MM-dd). */
    private static final DateTimeFormatter INPUT_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    /** Formatter for displaying dates in a user-friendly format (MMM d yyyy). */
    private static final DateTimeFormatter OUTPUT_FORMAT = DateTimeFormatter.ofPattern("MMM d yyyy");

    /**
     * Constructs an {@code Event} with a description, start date, and end date.
     *
     * @param description Description of the event.
     * @param fromStr Start date as a string in yyyy-MM-dd format.
     * @param toStr End date as a string in yyyy-MM-dd format.
     * @throws FloydAIException If the date strings are not in the expected format.
     */
    public Event(String description, String fromStr, String toStr) throws FloydAIException {
        super(description, TaskType.EVENT);
        try {
            this.from = LocalDate.parse(fromStr, INPUT_FORMAT);
            this.to = LocalDate.parse(toStr, INPUT_FORMAT);
        } catch (DateTimeParseException e) {
            throw new FloydAIException("Invalid date format! Use yyyy-MM-dd (e.g., 2019-12-02).");
        }
    }

    /**
     * Returns the start date of the event.
     *
     * @return {@link LocalDate} representing the start date.
     */
    public LocalDate getFrom() {
        return from;
    }

    /**
     * Returns the end date of the event.
     *
     * @return {@link LocalDate} representing the end date.
     */
    public LocalDate getTo() {
        return to;
    }

    /**
     * Returns a string representation of the event.
     * Includes the task type, status, description, and formatted start and end dates.
     *
     * @return A string representing this event.
     */
    @Override
    public String toString() {
        return super.toString() + " (from: " + from.format(OUTPUT_FORMAT) + " to: " + to.format(OUTPUT_FORMAT) + ")";
    }
}
