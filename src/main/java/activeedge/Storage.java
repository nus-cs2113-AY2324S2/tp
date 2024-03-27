package activeedge;

import activeedge.task.GoalTask;
import activeedge.task.MealTask;
import activeedge.task.TaskList;
import activeedge.task.ExerciseTask;
import activeedge.task.WaterTask;
import activeedge.userdetails.LogHeight;
import activeedge.userdetails.LogWeight;
import activeedge.userdetails.UserDetailsList;
import command.AddBMICommand;
import command.AddHeightCommand;
import command.AddWeightCommand;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;



/**
 * The {@code Storage} class handles file operations for the Health Tracker application.
 * It includes methods for ensuring directory existence, creating files, saving logs to files,
 * and fetching data from files.
 */
public class Storage {


    /**
     * Ensures that the directory for a given file path exists.
     * If the directory does not exist, it creates all necessary parent directories.
     *
     * @param filePath The file path for which to ensure directory existence.
     */
    public static void ensureDirectoryExists(String filePath) {
        File file = new File(filePath);
        File parentDir = file.getParentFile();
        if (!parentDir.exists()) {
            parentDir.mkdirs();
        }
    }

    /**
     * Creates a new file at the specified file path.
     * If the file's directory does not exist, it ensures the creation of the directory structure.
     *
     * @param filePath The path of the file to be created.
     */
    public static void createFile(String filePath) {
        ensureDirectoryExists(filePath);
        try {
            FileWriter file = new FileWriter(filePath);
            file.close();
        } catch (IOException e) {
            System.out.println("An error occurred while creating file: " + e.getMessage());
        }
    }

    /**
     * Saves the current logs from {@code TaskList} to a file at the given file path.
     * Each task is converted to a string and written to the file, one task per line.
     *
     * @param filePath The path of the file where logs should be saved.
     */
    public static void saveLogsToFile(String filePath) {
        try (FileWriter fw = new FileWriter(filePath)) {
            for (int i = 0; i < UserDetailsList.detailsList.size(); i++) {
                String out = UserDetailsList.detailsList.get(i).toString();
                fw.write(out + "\n");
            }
            for (int i = 0; i < TaskList.tasksList.size(); i++) {
                String out = TaskList.tasksList.get(i).toString();
                fw.write(out + "\n");
            }
        } catch (IOException e) {
            System.out.println("An error occurred while saving tasks to file: " + e.getMessage());
        }
    }


    /**
     * Fetches and loads data from a specified data file into the application's memory.
     * This method attempts to read tasks from the file, parsing each line to recreate
     * the appropriate {@code Task} objects. The tasks are then added to the {@code TaskList}.
     */
    public static void fetchData() {
        String filePath = Paths.get(System.getProperty("user.dir"), "data", "data.txt").toString();
        File file = new File(filePath);


        if (!file.exists()) {
            createFile(filePath);
        }
        if (file.length() == 0 ) {
            System.out.print("\n");
            int i = 0;
            int j = 0;
            System.out.println("Welcome new user! Please input your height and weight in whole numbers!");
            try {
                int heightInput = 0;
                int weightInput = 0;
                while (j < 1) {
                    System.out.println("Please input your height (in cm): ");
                    Scanner scanner = new Scanner(System.in);
                    try {
                        heightInput = Integer.valueOf(scanner.nextLine());
                        AddHeightCommand addHeightCommand = new AddHeightCommand(heightInput, LocalDateTime.now());
                        addHeightCommand.execute();
                        saveLogsToFile("data/data.txt");
                        j++;
                    } catch (NumberFormatException e) {
                        System.out.println("Please input a whole number only");
                    }
                }
                while (i < 1) {
                    System.out.println("Please input your weight (in kg): ");
                    Scanner scanner = new Scanner(System.in);
                    try {
                        weightInput = Integer.valueOf(scanner.nextLine());
                        AddWeightCommand addWeightCommand = new AddWeightCommand(weightInput, LocalDateTime.now());
                        addWeightCommand.execute();
                        saveLogsToFile("data/data.txt");
                        i++;
                    } catch (NumberFormatException e) {
                        System.out.println("Please input a whole number only");
                    }
                }
                double heightMeters = (double) heightInput/100;
                int bmi = (int) (weightInput/(heightMeters*heightMeters));
                AddBMICommand addBMICommand = new AddBMICommand(bmi, LocalDateTime.now());
                addBMICommand.execute();
                saveLogsToFile("data/data.txt");
                System.out.println("Your BMI is " + bmi);
                if (bmi < 19) {
                    System.out.println("You are in the underweight range.");
                } else if (bmi < 25) {
                    System.out.println("You are in the healthy weight range.");
                } else if (bmi < 30) {
                    System.out.println("You are in the overweight range.");
                } else {
                    System.out.println("You are in the obese weight range.");
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            System.out.println("You can now start logging data!");
        } else {
            try (Scanner scanner = new Scanner(file)) {
                while (scanner.hasNext()) {
                    String task = scanner.nextLine();
                    String dateTimeStr = extractDateTimeString(task);
                    LocalDateTime dateTime = parseDateTime(dateTimeStr);

                    if (task.startsWith("Meal")) {
                        String[] items = task.trim().split(" ");
                        int len = items.length;
                        assert len >= 8;

                        String mealName = "";
                        //len-7 is the last item[] of the mealname. if mealname is fried chicken
                        // then item[len-7] = chicken
                        for(int i = 1; i <= len-7; i++) {
                            if( i < len-7 ) {
                                mealName = mealName + items[i] + " ";
                            } else {
                                mealName = mealName + items[i];
                            }
                        }
                        int servings = Integer.parseInt(items[len-6]);
                        int mealCalories = Integer.parseInt(items[len-5]);
                        MealTask newTask = new MealTask(mealName, servings, mealCalories, dateTime);

                        TaskList.tasksList.add(newTask);

                    } else if (task.startsWith("Goal")) {
                        String[] items = task.trim().split(" ");
                        GoalTask newTask = new GoalTask(items[1], Integer.parseInt(items[2]), dateTime);
                        TaskList.tasksList.add(newTask);
                    } else if (task.startsWith("Water")) {
                        String[] items = task.trim().split(" ");
                        WaterTask newTask = new WaterTask(Integer.parseInt(items[1]), dateTime);
                        TaskList.tasksList.add(newTask);
                    } else if (task.startsWith("Height")) {
                        String[] items = task.trim().split(" ");
                        LogHeight newHeight = new LogHeight(Integer.parseInt(items[1]), dateTime);
                        UserDetailsList.detailsList.add(newHeight);
                    } else if (task.startsWith("Weight")) {
                        String[] items = task.trim().split(" ");
                        LogWeight newWeight = new LogWeight(Integer.parseInt(items[1]), dateTime);
                        UserDetailsList.detailsList.add(newWeight);
                    } else if (task.startsWith("Exercise")){
                        String[] items = task.trim().split(" ");
                        int len = items.length;
                        assert len >= 4;
                        String exerciseName = "";
                        for (int i = 1; i <= len - 3; i++) {
                            exerciseName = exerciseName + items[i];
                        }
                        ExerciseTask newTask = new ExerciseTask(exerciseName,
                                Integer.parseInt(items[len - 3]),
                                Integer.parseInt(items[len - 2]), dateTime);
                        TaskList.tasksList.add(newTask);
                    }
                }
            } catch (FileNotFoundException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
    private static String extractDateTimeString(String task) {
        // Extracting date-time string between "Recorded on: " and ")"
        int startIndex = task.indexOf("Recorded on: ") + "Recorded on: ".length();
        int endIndex = task.lastIndexOf(")");
        return task.substring(startIndex, endIndex);
    }


    private static LocalDateTime parseDateTime(String dateTimeStr) {
        try {
            // Use the correct DateTimeFormatter for your date-time strings
            DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;
            return LocalDateTime.parse(dateTimeStr, formatter);
        } catch (DateTimeParseException e) {
            System.out.println("Error parsing date-time: " + e.getMessage());
            // Handle the error, e.g., by returning a default date-time or logging the error
            return LocalDateTime.now(); // Default to the current date-time
        }
    }

}

