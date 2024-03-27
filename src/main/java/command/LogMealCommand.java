package command;

import activeedge.ui.CommandUi;
import activeedge.task.MealTask;

import static activeedge.task.TaskList.TASKS_LIST;
import java.time.LocalDateTime;


public class LogMealCommand {
    protected String description;
    protected int servings;
    protected int mealCalories;
    protected LocalDateTime dateTime;

    public LogMealCommand(String description, int servings, int mealCalories, LocalDateTime dateTime) {
        this.description = description;
        this.servings = servings;
        this.mealCalories = mealCalories;
        this.dateTime = dateTime;
    }

    public void execute() throws ActiveEdgeException {
        MealTask logMeal = new MealTask(description, servings, mealCalories, dateTime);
        TASKS_LIST.add(logMeal);
        CommandUi.printMealLogMessage(logMeal);
    }

    public int getMealCalories() {
        return mealCalories;
    }

    public String getDescription() {
        return description;
    }

    public int getServings() {
        return servings;
    }
}
