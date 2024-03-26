package health;

import utility.CustomExceptions;
import utility.ErrorConstant;
import utility.HealthConstant;
import utility.Parser;

import java.time.LocalDate;
import java.time.LocalTime;

public class Appointment {
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
    public static String[] getAppointment(String input) throws CustomExceptions.InvalidInput {
        String[] results = new String[HealthConstant.APPOINTMENT_PARAMETERS];

        if (!input.contains(HealthConstant.HEALTH_FLAG)
                || !input.contains(HealthConstant.DATE_FLAG)
                || !input.contains(HealthConstant.TIME_FLAG)
                || !input.contains(HealthConstant.DESCRIPTION_FLAG)) {
            throw new CustomExceptions.InvalidInput(ErrorConstant.UNSPECIFIED_PARAMETER_ERROR);
        }

        int indexH = input.indexOf(HealthConstant.HEALTH_FLAG);
        int indexDate = input.indexOf(HealthConstant.DATE_FLAG);
        int indexTime = input.indexOf(HealthConstant.TIME_FLAG);
        int indexDescription = input.indexOf(HealthConstant.DESCRIPTION_FLAG);

        String command = input.substring(indexH + HealthConstant.H_OFFSET, indexDate).trim();
        String dateSubstring = input.substring(indexDate + HealthConstant.DATE_OFFSET, indexTime).trim();
        String timeSubstring = input.substring(indexTime + HealthConstant.TIME_OFFSET, indexDescription).trim();
        String descriptionSubstring = input.substring(indexDescription + HealthConstant.DESCRIPTION_OFFSET).trim();

        if (command.isEmpty() || dateSubstring.isEmpty() || timeSubstring.isEmpty() || descriptionSubstring.isEmpty()) {
            throw new CustomExceptions.InvalidInput(HealthConstant.INSUFFICIENT_PARAMETERS_FOR_BMI);
        }

        results[0] = command;
        results[1] = dateSubstring;
        results[2] = timeSubstring;
        results[3] = descriptionSubstring;

        return results;
    }

    @Override
    public String toString() {
        return String.format(HealthConstant.PRINT_APPOINTMENT_FORMAT,
                getDate(),
                getTime(),
                this.description);
    }
}
