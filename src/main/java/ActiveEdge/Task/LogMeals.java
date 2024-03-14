package ActiveEdge.Task;

import static ActiveEdge.Task.TaskList.tasksList;
import ActiveEdge.Task.Task;

public class LogMeals extends Task {

    protected String servings;

    protected String calories;

    public LogMeals(String description, String servings, String calories) {
        super(description);
        this.servings = servings;
        this.calories = calories;
    }

    int servingsNum = Integer.parseInt(servings);
    int caloriesNum = Integer.parseInt(calories);
    int mealCalories = servingsNum * caloriesNum;

    public static int totalCalories;

    public int addCalories() {
        if(tasksList.size()==0) {
            totalCalories = 0;
        } else {
            totalCalories += mealCalories;
        }
        return totalCalories;
    }

    public String toString() {
        return "You've logged " + servings + " serving of " + super.getDescription() + ".\n" +
                "Estimated calories: " + mealCalories +"\n" +
                "Total calories: " + totalCalories;
    }

}
