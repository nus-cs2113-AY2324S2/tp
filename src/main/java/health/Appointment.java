package health;

import utility.HealthConstant;
import utility.Parser;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * The Appointment class extends the Health class.
 * It contains information about the date, time, and description of the appointment.
 */
public class Appointment extends Health {
    /**
     * The date of the appointment.
     */
    protected LocalDate date;
    /**
     * The time of the appointment.
     */
    protected LocalTime time;
    /**
     * The description of the appointment.
     */
    protected String description;

    /**
     * Constructor for Appointment object.
     *
     * @param stringDate A string representing the date of the appointment
     * @param stringTime A string representing the time of the appointment
     * @param description A string describing the appointment
     */
    public Appointment(String stringDate, String stringTime, String description) {
        this.date = Parser.parseDate(stringDate);
        this.time = Parser.parseTime(stringTime);
        this.description = description;
    }

    /**
     * Retrieves the date of the appointment.
     *
     * @return The date of appointment in LocalDate
     */
    public LocalDate getDate() {
        return this.date;
    }

    /**
     * Retrieves the time of the appointment
     *
     * @return The time of appointment in LocalTime
     */
    public LocalTime getTime() {
        return this.time;
    }

    /**
     * Retrieves the description of the appointment.
     *
     * @return The description of appointment in String
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Retrieves the string representation of an Appointment object.
     *
     * @return A formatted string representing an Appointment object.
     */
    @Override
    public String toString() {
        return String.format(HealthConstant.PRINT_APPOINTMENT_FORMAT,
                getDate(),
                getTime(),
                this.description);
    }
}
