package activeedge.ui;

import static activeedge.task.TaskList.tasksList;

import activeedge.task.ExerciseTask;
import activeedge.task.Task;
import activeedge.task.WaterTask;
import activeedge.task.MealTask;

public class CommandUi {

    static final String LINE = "____________________________________________________________\n";


    public static void printMealList() {
        System.out.println("Here are your logged meals for today");
        int j = 1;
        for (int i = 0; i < tasksList.size(); i++) {
            if (tasksList.get(i).toString().startsWith("Meal")) {
                System.out.print(j + ". " + tasksList.get(i).toString().substring(5));
                System.out.println(" kcal");
                j++;
            }
        }
    }

    public static void printFullList() {
        System.out.println("Logged data for today:");
        System.out.print("Food ");
        System.out.println("(food name, servings, calories):");
        int j = 1;

        for (int i = 0; i < tasksList.size(); i++) {
            if (tasksList.get(i).toString().startsWith("Meal")) {
                System.out.print(j + ". " + tasksList.get(i).toString().substring(5));
                System.out.println(" kcal");
                j++;
            }
        }
        System.out.println("Water:");
        int k = 1;
        for (int i = 0; i < tasksList.size(); i++) {
            if (tasksList.get(i).toString().startsWith("Water")) {
                System.out.print(k + ". " + tasksList.get(i).toString().substring(6));
                System.out.println(" ml");
                k++;
            }
        }

    }

    public static void printMealLogMessage(MealTask mealTask) {
        System.out.println("You've logged " + Integer.toString(mealTask.getServings()) +
                " servings" + " of " + mealTask.getFoodName() + ".") ;
        System.out.println("Estimated calories: " + Integer.toString(mealTask.getMealCalories()) + " kcal");
    }

    public static void printExerciseLogMessage(ExerciseTask exerciseTask) {
        System.out.println("You've logged " + Integer.toString(exerciseTask.getDuration()) +
                " hours" + " of " + exerciseTask.getExerciseName() + ".") ;
        System.out.println("Estimated calories burnt: " + Integer.toString(exerciseTask.getCaloriesBurnt()) + " kcal");
    }

    public static void printShowCalMessage() {
        int totalCalories = 0;
        String goal = "0";
        for (int i = 0; i < tasksList.size(); i++) {
            String[] parts = tasksList.get(i).toString().split(" ");
            int len = parts.length;
            if(tasksList.get(i).toString().startsWith("Meal")) {
                totalCalories = totalCalories + Integer.parseInt(parts[len-1]);
            }
            if(tasksList.get(i).toString().startsWith("Goal")) {
                if (parts[1].equals("c")) {
                    goal = parts[2].toString();
                }
            }
        }
        System.out.print("Total calories today: ");
        System.out.println(totalCalories + " kcal out of " + goal + " kcal");
    }

    public static void printWaterLogMessage(WaterTask newWaterTask) {
        System.out.println("Successfully logged " + newWaterTask.getQuantity() + " ml of water.");
    }

    public static void printWaterIntakeMessage(int totalWaterIntake, int waterGoal) {
        double percentage = ((double) totalWaterIntake / waterGoal) * 100;
        System.out.println("Total water consumed today: " + totalWaterIntake +
                " ml (" + String.format("%.0f%%", percentage) + " of " + waterGoal + "ml goal).");
    }


    public static void printMatchingTasks(String word) {
        System.out.println(LINE + " Here are the matching tasks in your list:");
        int matchingTasksIndex = 1;
        boolean found = false;

        // Search in the food section
        for (int i = 0; i < tasksList.size(); i++) {
            if (tasksList.get(i).toString().startsWith("Meal") && tasksList.get(i).toString().contains(word)) {
                System.out.print(matchingTasksIndex + ". ");
                System.out.println(tasksList.get(i).toString().substring(5) + " kcal");
                matchingTasksIndex++;
                found = true; // Indicate that a match was found
            }
        }

        // Search in the water section
        for (int i = 0; i < tasksList.size(); i++) {
            if (tasksList.get(i).toString().startsWith("Water") && tasksList.get(i).toString().contains(word)) {
                System.out.print(matchingTasksIndex + ". ");
                System.out.println(tasksList.get(i).toString().substring(6) + " ml");
                matchingTasksIndex++;
                found = true; // Indicate that a match was found
            }
        }

        // If no matching tasks were found, print a message
        if (!found) {
            System.out.println("No matching tasks found.");
        }

        System.out.println(LINE);
    }

    public static void printInvalidDeleteFormatMessage() {
        System.out.println("This is an invalid request. Please try again!");
    }

    public static void printTaskDeletedMessage(Task deletedTask) {
        System.out.println("Task deleted: " + deletedTask.getDescription());
    }

    public static void printTaskNotFoundMessage() {
        System.out.println("Task not found.");
    }

    public static void printShowSummaryMessage(int totalCalories,int totalWaterIntake, int totalCaloriesBurnt,
                                               String calorieGoal, String waterGoal) {
        System.out.println("Daily Summary:");
        System.out.println("Total calories consumed: " + totalCalories + " kcal");
        System.out.println("Total water consumed: " + totalWaterIntake + " ml");
        System.out.println("Total calories burnt: " + totalCaloriesBurnt + " kcal");

        System.out.println("Calorie goal: " + calorieGoal + " kcal");
        System.out.println("Water goal: " + waterGoal + " ml");
    }

    public static void printAllTasksClearedMessage() {
        System.out.println("All logged data has been cleared.");
    }
    public static void printDataAlreadyClearedMessage() {
        System.out.println("Logged data has already been cleared.");
    }


}

