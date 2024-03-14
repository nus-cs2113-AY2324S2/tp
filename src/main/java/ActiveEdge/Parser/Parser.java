package ActiveEdge.Parser;

import ActiveEdge.Command.ActiveEdgeException;
import ActiveEdge.Command.HelpCommand;
import ActiveEdge.Command.ListMealsCommand;
import ActiveEdge.Command.LogMealCommand;
import ActiveEdge.Task.LogMeals;
import ActiveEdge.FoodData;

import java.util.List;


public class Parser {

    public void handleInput(String input) throws ActiveEdgeException {
        if (input.contains("help")) {
            new HelpCommand();
        } else if (input.startsWith("log")) {
            new LogMealCommand(input);
        } else if (input.startsWith("list")) {
            if (input.trim().length() > 4) { //list meals
                new ListMealsCommand();
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

}
