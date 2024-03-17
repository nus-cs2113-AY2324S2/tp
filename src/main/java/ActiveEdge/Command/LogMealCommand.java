package ActiveEdge.Command;

import ActiveEdge.Task.LogWaterTask;
import ActiveEdge.Task.TaskList;
import ActiveEdge.Ui.CommandUi;
import ActiveEdge.Task.LogMeals;
import ActiveEdge.FoodData;

import static ActiveEdge.Task.TaskList.tasksList;

public class LogMealCommand {

    protected String description;
    protected String servings;

    public LogMealCommand(String description, String servings) {
        this.description = description;
        this.servings = servings;
    }

//    private String findCalories(String description) {
//        String calorieOfFood = null;
//        for (String[] food : FoodData.foodItems) {
//            if (food[1].equalsIgnoreCase(description)) { // Match description ignoring case
//                calorieOfFood = food[2];
//                break; // Stop searching once found
//            }
//        }
//        return calorieOfFood;
//    }

    public void execute() throws ActiveEdgeException {
        int servingNum = Integer.parseInt(servings);
//        String calorieOfFood = findCalories(description);
//
//        int foodCalorie = Integer.parseInt(calorieOfFood);
//        int mealCalorie = foodCalorie * servingNum;

        LogMeals logMeals = new LogMeals(servingNum, /*mealCalorie,*/ description);
        TaskList.tasksList.add(logMeals);
        CommandUi.printMealLogMessage(logMeals);
    }

    protected String description;
    protected String servings;

    public LogMealCommand(String description, String servings) {
        this.description = description;
        this.servings = servings;
    }

//    private String findCalories(String description) {
//        String calorieOfFood = null;
//        for (String[] food : FoodData.foodItems) {
//            if (food[1].equalsIgnoreCase(description)) { // Match description ignoring case
//                calorieOfFood = food[2];
//                break; // Stop searching once found
//            }
//        }
//        return calorieOfFood;
//    }

    public void execute() throws ActiveEdgeException {
        try {
            int servingNum = Integer.parseInt(servings);
            //        String calorieOfFood = findCalories(description);
            //
            //        int foodCalorie = Integer.parseInt(calorieOfFood);
            //        int mealCalorie = foodCalorie * servingNum;

            LogMeals logMeals = new LogMeals(servingNum, /*mealCalorie,*/ description);
            TaskList.tasksList.add(logMeals);
            CommandUi.printMealLogMessage(logMeals);
        } catch (NumberFormatException e) {
            throw new ActiveEdgeException("Invalid meal. Please provide a valid integer.");
        }
    }
}


//    public LogMealCommand(String input) throws ActiveEdgeException {
//        if(input.trim().length() > 4 && input.contains("m/") && input.contains("s/")) {
//            String[] parts = input.substring(4).split("m/|s/");
//            String description = parts[0].trim();
//            String servings = parts[1].trim();
//            String calories = findCalories(description);
//
//            LogMeals newMeal = new LogMeals(description, servings, calories);
//            tasksList.add(newMeal);
//
//            CommandUi.printLogMessage(newMeal, tasksList.size());
//        } else {
//            throw new ActiveEdgeException("Invalid input format for logging meal.");
//        }
//    }
//
//    private String findCalories(String description) {
//        String calorieOfFood = null;
//        for (String[] food : FoodData.foodItems) {
//            if (food[1].equalsIgnoreCase(description)) { // Match description ignoring case
//                calorieOfFood = food[2];
//                break; // Stop searching once found
//            }
//        }
//        return calorieOfFood;
//    }
//
//}
