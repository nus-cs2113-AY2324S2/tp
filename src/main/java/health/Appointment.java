package health;

import utility.HealthConstant;
import utility.Parser;

import java.time.LocalDate;
import java.time.LocalTime;

public class Appointment extends Health {
    //@@author syj02
    protected LocalDate date;
    protected LocalTime time;
    protected String description;

    public Appointment(String stringDate, String stringTime, String description) {
        this.date = Parser.parseDate(stringDate);
        this.time = Parser.parseTime(stringTime);
        this.description = description;
    }

    public LocalDate getDate() {
        return this.date;
    }

    public LocalTime getTime() {
        return this.time;
    }

    public String getDescription() {
        return this.description;
    }

    @Override
    public String toString() {
        return String.format(HealthConstant.PRINT_APPOINTMENT_FORMAT,
                getDate(),
                getTime(),
                this.description);
    }
}
