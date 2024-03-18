package ActiveEdge.Command;

import ActiveEdge.Ui.CommandUi;
import ActiveEdge.Task.LogMeals;

import static ActiveEdge.Task.TaskList.tasksList;

public class LogMealCommand {
    protected String description;
    protected int servings;
    protected int mealCalories;

    public LogMealCommand(String description, int servings, int mealCalories) {
        this.description = description;
        this.servings = servings;
        this.mealCalories = mealCalories;
    }

    public void execute() throws ActiveEdgeException {
        LogMeals logMeal = new LogMeals(description, servings, mealCalories);
        tasksList.add(logMeal);
        CommandUi.printMealLogMessage(logMeal);
    }
}
