
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
        assert startOfView != null : "Start of Weekday missing!";
        LocalDate endOfWeek = startOfView.plusDays(6);
        System.out.println("\nWeek View: " + dateFormatter.format(startOfView) +
                " - " + dateFormatter.format(endOfWeek));

        printWeekHeader(startOfView, dateFormatter, false);
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

    public LocalDate getStartOfWeek() {
        return startOfView;
    }

    public LocalDate getDateForDay(int dayOfWeek) {
        // Assuming dayOfWeek is 1 for Monday, 2 for Tuesday, ..., 7 for Sunday
        // Adjust the calculation if your week starts on a different day
        return startOfView.plusDays(dayOfWeek - 1);
    }

}
