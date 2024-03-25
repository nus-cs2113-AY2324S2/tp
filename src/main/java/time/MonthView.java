package time;

import data.Task;
import data.TaskManager;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import static ui.UiRenderer.printSeparator;
import static ui.UiRenderer.printWeekHeader;

public class MonthView extends View {
    private static final Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    public MonthView(LocalDate startOfMonth, DateTimeFormatter dateFormatter) {
        super(startOfMonth, dateFormatter);
    }

    @Override
    public void printView(TaskManager taskManager) {
        logger.log(Level.INFO, "Printing calendar in month view");
        assert startOfView != null : "Start of Month missing!";
        boolean isMonthView = true;
        YearMonth yearMonth = YearMonth.from(startOfView);
        LocalDate firstOfMonth = startOfView.withDayOfMonth(1);
        LocalDate current = firstOfMonth.with(TemporalAdjusters.previousOrSame(java.time.DayOfWeek.SUNDAY));

        System.out.println("\nMonth View: " + yearMonth.getMonth() + " " + yearMonth.getYear());
        printWeekHeader(startOfView, dateFormatter, isMonthView);

        while (current.isBefore(firstOfMonth.plusMonths(1))) {
            printWeek(current, taskManager);
            current = current.plusWeeks(1);
        }
    }

    private void printWeek(LocalDate current, TaskManager taskManager) {
        for (int i = 0; i < 7; i++) {
            if (current.getMonth().equals(YearMonth.from(startOfView).getMonth())) {
                printDay(current);
            } else {
                System.out.print("|            ");
            }
            current = current.plusDays(1);
        }
        System.out.println("|");
        printSeparator();

        int maxTasks = getMaxTasksForWeek(current.minusDays(7), taskManager);
        printTasksForWeek(current.minusDays(7), maxTasks, taskManager);

        if (maxTasks > 0) {
            printSeparator();
        }
    }

    private void printDay(LocalDate current) {
        DateTimeFormatter dayFormatter = DateTimeFormatter.ofPattern("d");
        System.out.printf("| %-10s ", dayFormatter.format(current));
    }

    private int getMaxTasksForWeek(LocalDate weekStart, TaskManager taskManager) {
        int maxTasks = 0;
        for (int i = 0; i < 7; i++) {
            LocalDate date = weekStart.plusDays(i);
            maxTasks = Math.max(maxTasks, taskManager.getTasksForDate(date).size());
        }
        return maxTasks;
    }

    private void printTasksForWeek(LocalDate weekStart, int maxTasks, TaskManager taskManager) {
        for (int taskIndex = 0; taskIndex < maxTasks; taskIndex++) {
            for (int dayIndex = 0; dayIndex < 7; dayIndex++) {
                LocalDate date = weekStart.plusDays(dayIndex);
                List<Task> dayTasks = taskManager.getTasksForDate(date);
                printTaskForDay(dayTasks, taskIndex);
            }
            System.out.println("|");
        }
    }

    private void printTaskForDay(List<Task> dayTasks, int taskIndex) {
        if (taskIndex < dayTasks.size()) {
            Task task = dayTasks.get(taskIndex);
            System.out.printf("| %-10.10s ", task.getName());
        } else {
            System.out.print("|            ");
        }
    }

    @Override
    public void next() {
        startOfView = startOfView.plusMonths(1);
    }

    @Override
    public void previous() {
        startOfView = startOfView.minusMonths(1);
    }

    public LocalDate getStartOfMonth() {
        return startOfView.withDayOfMonth(1);
    }
}
