package ActiveEdge.Command;

import ActiveEdge.Ui.CommandUi;
import ActiveEdge.Task.LogMeals;
import ActiveEdge.FoodData;

import static ActiveEdge.Task.TaskList.tasksList;

public class LogMealCommand {
    public LogMealCommand(String input) {
        if(input.trim().length() > 4 && input.contains("m/") && input.contains("s/")) {
            String[] parts = input.substring(4).split("m/ | s/");
            String description = parts[0].trim();
            String servings = parts[1].trim();
            String calories = findCalories(description);

            LogMeals newMeal = new LogMeals(description, servings, calories);
            tasksList.add(newMeal);

            CommandUi.printLogMessage(newMeal, tasksList.size());
        }
    }

    private String findCalories(String description) {
        String calorieOfFood = null;
        for (String[] food : FoodData.foodItems) {
            if (food[1].equalsIgnoreCase(description)) { // Match description ignoring case
                calorieOfFood = food[2];
                break; // Stop searching once found
            }
        }
        return calorieOfFood;
    }

}
