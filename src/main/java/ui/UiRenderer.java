package ui;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import data.TaskManager;

public class UiRenderer {
    private static final String[] WEEK_DAYS = {"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"};

    private static final int SPACE_COUNT = 15;
    private static final String SINGLE_HORIZONTAL_DIVIDER = "+" + "-".repeat(SPACE_COUNT + 2);
    private static final String END_HORIZONTAL_DIVIDER = "+";
    private static final String VERTICAL_DIVIDER = "|";
    private static final String ENTRY_FORMAT = VERTICAL_DIVIDER + " %-" + SPACE_COUNT + "s ";
    private static final String TASK_DISPLAY_FORMAT = VERTICAL_DIVIDER + " %-" + SPACE_COUNT + "." + SPACE_COUNT + "s ";
    private static final String EMPTY_TASK_DISPLAY_FORMAT = VERTICAL_DIVIDER + " ".repeat(SPACE_COUNT+1) + " ";

    private static final int numberOfDaysInWeek = 7;

    public static void printWeekHeader() {
        printHorizontalDivider();
        printHeaderRow();
        printHorizontalDivider();
    }

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

    public static void printWeekBody(LocalDate startOfWeek, DateTimeFormatter dateFormatter, TaskManager taskManager) {
        LocalDate date = startOfWeek;
        printDateRow(dateFormatter, date);

        printHorizontalDivider();
        int maxTasks = getMaxTasks(startOfWeek, taskManager);
        printWeeksTasks(startOfWeek, maxTasks, taskManager);
        printHorizontalDivider();
    }

    private static void printDateRow(DateTimeFormatter dateFormatter, LocalDate date) {
        for (int i = 0; i < numberOfDaysInWeek; i++) {
            System.out.printf(ENTRY_FORMAT, dateFormatter.format(date));
            date = date.plusDays(1);
        }
        System.out.println(VERTICAL_DIVIDER);
    }

    public static void printWeeksTasks(LocalDate startOfWeek, int maxTasks, TaskManager taskManager) {
        for (int taskIndex = 0; taskIndex < maxTasks; taskIndex++) {
            for (int dayIndex = 0; dayIndex < numberOfDaysInWeek; dayIndex++) {
                LocalDate currentDate = startOfWeek.plusDays(dayIndex);
                List<String> dayTasks = taskManager.getTasksForDate(currentDate);
                printTaskForDay(dayTasks, taskIndex);
            }
            System.out.println(VERTICAL_DIVIDER);
        }
    }

    private static void printTaskForDay(List<String> dayTasks, int taskIndex) {
        if (taskIndex < dayTasks.size()) {
            String task = dayTasks.get(taskIndex);
            System.out.printf(TASK_DISPLAY_FORMAT, task);
        } else {
            System.out.print(EMPTY_TASK_DISPLAY_FORMAT);
        }
    }

    private static int getMaxTasks(LocalDate startOfWeek, TaskManager taskManager) {
        int maxTasks = 0;
        for (int i = 0; i < numberOfDaysInWeek; i++) {
            LocalDate currentDate = startOfWeek.plusDays(i);
            int tasksSize = taskManager.getTasksForDate(currentDate).size();
            if (tasksSize > maxTasks) {
                maxTasks = tasksSize;
            }
        }
        return maxTasks;
    }
  
    public static void printSeparator() {
        for (int i = 0; i < 7; i++) {
            System.out.print("+------------");
        }
        System.out.println("+");
    }
}

