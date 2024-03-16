package seedu.duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class WeekView {
    private LocalDate startOfWeek;
    private final DateTimeFormatter dateFormatter;

    public WeekView(LocalDate startOfWeek, DateTimeFormatter dateFormatter) {
        this.startOfWeek = startOfWeek;
        this.dateFormatter = dateFormatter;
    }

    public void printWeekView(TaskManager taskManager) {
        LocalDate endOfWeek = startOfWeek.plusDays(6);
        System.out.println("\nWeek View: " + dateFormatter.format(startOfWeek) + " - " + dateFormatter.format(endOfWeek));
        UIRenderer.printWeekHeader();
        UIRenderer.printWeekDays(startOfWeek, dateFormatter, taskManager);
    }

    public void nextWeek() {
        startOfWeek = startOfWeek.plusWeeks(1);
    }

    public void previousWeek() {
        startOfWeek = startOfWeek.minusWeeks(1);
    }

    public LocalDate getStartOfWeek() {
        return startOfWeek;
    }
}
