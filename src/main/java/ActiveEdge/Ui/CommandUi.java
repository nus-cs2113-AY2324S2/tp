package ActiveEdge.Ui;

import static ActiveEdge.Task.TaskList.tasksList;

import ActiveEdge.Task.LogWaterTask;
import ActiveEdge.Task.Task;
import ActiveEdge.Task.LogMeals;

public class CommandUi {

    public static void printMealList() {
        if(tasksList.size() > 0) {
            System.out.println("Here are your logged meals for today");
            for (int i = 0; i < tasksList.size(); i++) {
                int index = 1 + i;
                System.out.println(index + ". " + tasksList.get(i));
            }
        } else {
            System.out.println("You have not logged any meals for today.");
        }
    }

    public static void printMealLogMessage(LogMeals logMeals) {
        System.out.println("You've logged " + logMeals.getServings() + " of" + logMeals.getDescription());
        //System.out.println("Estimated calories: " + logMeals.getMealCalories());
        System.out.println("Total calories: ");
    }

    public static void printShowCalMessage() {
    }

    public static void printWaterLogMessage(LogWaterTask newWaterTask) {
        System.out.println("Successfully logged " + newWaterTask.getQuantity() + " ml of water.");
    }

    public static void printWaterIntakeMessage(int totalWaterIntake) {
        System.out.println("Total water consumed today: " + totalWaterIntake + " ml");
    }




}
