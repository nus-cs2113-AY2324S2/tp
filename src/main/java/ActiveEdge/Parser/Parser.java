package ActiveEdge.Parser;

import ActiveEdge.Command.ActiveEdgeException;
import ActiveEdge.Command.HelpCommand;
import ActiveEdge.Task.LogMeals;
import ActiveEdge.FoodData;



public class Parser {

    public void handleInput(String input) throws ActiveEdgeException {
        if (input.contains("help")) {
            new HelpCommand();
        } else if (input.startsWith("log")) {
            //input parsing logic here to get description, servings, calories from the database
            String[] parts = input.substring(4).split("m/ | s/");
            String description = parts[0].trim();
            String servings = parts[1].trim();
            String calories = findCalories(description);
            new LogMeals(description, servings, calories);
        } else if (input.startsWith("list")) {
            if (input.trim().length() > 4) { //list meals

            } else { //list both

            }
        } else if (input.startsWith("show")) { //show calories, water, and goals
            String[] parts = input.split(" ");
            String inputTrimmed = parts[1].trim();
            if (inputTrimmed.startsWith("c")) { //shows calorie

            } else if (inputTrimmed.startsWith("w")) { //shows water

            } else if (inputTrimmed.startsWith("g")) {  //shows goals

            } else if (inputTrimmed.startsWith("g")) { //shows goals

            } else {
                System.out.println("Hello!\n");
            }
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
