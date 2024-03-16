package ui;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;


import data.TaskManager;

public class UiRenderer {
    private static final String[] WEEK_DAYS = {"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"};

    public static void printWeekHeader() {
        for (String day : WEEK_DAYS) {
            System.out.print("+------------");
        }
        System.out.println("+");

        for (String day : WEEK_DAYS) {
            System.out.printf("| %-10s ", day);
        }
        System.out.println("|");

        for (String day : WEEK_DAYS) {
            System.out.print("+------------");
        }
        System.out.println("+");
    }

    public static void printWeekDays(LocalDate startOfWeek, DateTimeFormatter dateFormatter, TaskManager taskManager) {
        LocalDate date = startOfWeek;
        for (int i = 0; i < 7; i++) {
            System.out.printf("| %-10s ", dateFormatter.format(date));
            date = date.plusDays(1);
        }
        System.out.println("|");

        for (String day : WEEK_DAYS) {
            System.out.print("+------------");
        }
        System.out.println("+");

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
                    System.out.printf("| %-10.10s ", task);
                } else {
                    System.out.print("|            ");
                }
            }
            System.out.println("|");
        }

        for (String day : WEEK_DAYS) {
            System.out.print("+------------");
        }
        System.out.println("+");
    }
}