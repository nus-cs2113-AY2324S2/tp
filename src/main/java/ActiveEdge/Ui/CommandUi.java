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

    public static void printLogMessage(Task newMeal, int size) {
        System.out.println(newMeal);
    }

    public static void printShowCalMessage() {
        System.out.println("Today calories consumed today: " + LogMeals.totalCalories);
    }

    public static void printWaterLogMessage(LogWaterTask newWaterTask) {
        System.out.println("Successfully logged " + newWaterTask.getQuantity() + " ml of water.");
    }

    public static void printWaterIntakeMessage(int totalWaterIntake) {
        System.out.println("Total water intake: " + totalWaterIntake + " ml");
    }




}
