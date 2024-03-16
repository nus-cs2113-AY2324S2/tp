package ui;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import data.TaskManager;

public class UiRenderer {
    private static final String[] WEEK_DAYS = {"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"};
    private static final String SINGLE_HORIZONTAL_DIVIDER = "+------------";
    private static final String END_HORIZONTAL_DIVIDER = "+";
    private static final String VERTICAL_DIVIDER = "|";
    private static final String ENTRY_FORMAT = VERTICAL_DIVIDER + " %-10s ";
    private static final String TASK_DISPLAY_FORMAT = VERTICAL_DIVIDER + " %-10.10s ";
    private static final String EMPTY_TASK_DISPLAY_FORMAT = VERTICAL_DIVIDER + "            ";

    private static void printHorizontalDivider() {
        for (String day : WEEK_DAYS) {
            System.out.print(SINGLE_HORIZONTAL_DIVIDER);
        }
        System.out.println(END_HORIZONTAL_DIVIDER);
    }
    
    private static void printHeaderRow() {
        for (String day : WEEK_DAYS) {
            System.out.printf(ENTRY_FORMAT, day);
        }
        System.out.println(VERTICAL_DIVIDER);
    }

    public static void printWeekHeader() {
        printHorizontalDivider();
        printHeaderRow();
        printHorizontalDivider();
    }

    public static void printWeekDays(LocalDate startOfWeek, DateTimeFormatter dateFormatter, TaskManager taskManager) {
        LocalDate date = startOfWeek;
        for (int i = 0; i < 7; i++) {
            System.out.printf(ENTRY_FORMAT, dateFormatter.format(date));
            date = date.plusDays(1);
        }
        System.out.println(VERTICAL_DIVIDER);

        printHorizontalDivider();

        int maxTasks = 0;
        for (int i = 0; i < 7; i++) {
            LocalDate currentDate = startOfWeek.plusDays(i);
            int tasksSize = taskManager.getTasksForDate(currentDate).size();
            if (tasksSize > maxTasks) {
                maxTasks = tasksSize;
            }
        }

        for (int taskIndex = 0; taskIndex < maxTasks; taskIndex++) {
            for (int dayIndex = 0; dayIndex < 7; dayIndex++) {
                LocalDate currentDate = startOfWeek.plusDays(dayIndex);
                List<String> dayTasks = taskManager.getTasksForDate(currentDate);
                if (taskIndex < dayTasks.size()) {
                    String task = dayTasks.get(taskIndex);
                    System.out.printf(TASK_DISPLAY_FORMAT, task);
                } else {
                    System.out.print(EMPTY_TASK_DISPLAY_FORMAT);
                }
            }
            System.out.println(VERTICAL_DIVIDER);
        }

        printHorizontalDivider();
    }
}
