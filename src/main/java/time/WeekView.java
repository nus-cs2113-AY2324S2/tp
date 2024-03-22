
package time;

import data.TaskManager;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static ui.UiRenderer.printWeekBody;
import static ui.UiRenderer.printWeekHeader;

public class WeekView extends View {

    public WeekView(LocalDate startOfWeek, DateTimeFormatter dateFormatter) {
        super(startOfWeek, dateFormatter);
    }

    @Override
    public void printView(TaskManager taskManager) {
        LocalDate endOfWeek = startOfView.plusDays(6);
        System.out.println("\nWeek View: " + dateFormatter.format(startOfView) +
                " - " + dateFormatter.format(endOfWeek));

        // Print week header and body using UiRenderer methods
        printWeekHeader();
        printWeekBody(startOfView, dateFormatter, taskManager);
    }

    @Override
    public void next() {
        startOfView = startOfView.plusWeeks(1);
    }

    @Override
    public void previous() {
        startOfView = startOfView.minusWeeks(1);
    }

    /**
     //     * Retrieves the start date of the current week view.
     //     *
     //     * @return the start date of the week view
     //     */
    public LocalDate getStartOfWeek() {
        return startOfView;
    }
}
