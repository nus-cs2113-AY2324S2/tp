package ActiveEdge.Ui;

import static ActiveEdge.Task.TaskList.tasksList;
import ActiveEdge.Task.Task;

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

}
