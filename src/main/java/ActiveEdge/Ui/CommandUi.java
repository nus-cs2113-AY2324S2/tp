package ActiveEdge.Ui;

import static ActiveEdge.Task.TaskList.tasksList;

import ActiveEdge.Task.WaterTask;
import ActiveEdge.Task.LogMeals;

public class CommandUi {

    public static void printMealList() {
        System.out.println("Here are your logged meals for today");
        for (int i = 0; i < tasksList.size(); i++) {
            int j = 1;
            if (tasksList.get(i).toString().startsWith("Meal")) {
                System.out.println(j + ". " + tasksList.get(i));
                j++;
            }
        }
    }

    public static void printMealLogMessage(LogMeals logMeals) {
        System.out.println("You've logged " + Integer.toString(logMeals.getServings()) + " servings" + " of " + logMeals.getFoodName() + ".") ;
        System.out.println("Estimated calories: " + Integer.toString(logMeals.getMealCalories()));
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
        System.out.println(totalCalories + " out of " + goal);


    }

    public static void printWaterLogMessage(WaterTask newWaterTask) {
        System.out.println("Successfully logged " + newWaterTask.getQuantity() + " ml of water.");
    }

    public static void printWaterIntakeMessage(int totalWaterIntake, int waterGoal) {
        double percentage = ((double) totalWaterIntake / waterGoal) * 100;
        System.out.println("Total water consumed today: " + totalWaterIntake + " ml (" + String.format("%.0f%%", percentage) + " of " + waterGoal + "ml goal).");
    }
}
