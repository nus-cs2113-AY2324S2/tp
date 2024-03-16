package ActiveEdge.Parser;

import java.util.Scanner;
import ActiveEdge.Command.ActiveEdgeException;
import ActiveEdge.Command.HelpCommand;
import ActiveEdge.Command.LogWaterCommand;
import ActiveEdge.Command.ViewWaterIntakeCommand;
import ActiveEdge.Task.LogMeals;
import ActiveEdge.Task.Task;
import ActiveEdge.Task.TaskList;
import ActiveEdge.Command.*;


public class Parser {

    public void handleInput(String input) throws ActiveEdgeException {
        String inputTrimmed;
        if (input.contains("help")) {
            new HelpCommand();
        } else if (input.startsWith("log")) {
            String parts = input.substring(4);
            String[] items = parts.split("/");
            if (items[0].equals("w")) {
                String quantityString = items[1];
                LogWaterCommand logWaterCommand = new LogWaterCommand(quantityString);
                logWaterCommand.execute();

            } else if(items[0].equals("m")) {
                String[] logParts = parts.split("m/|s/");
                String description = logParts[0].trim();
                String servings = logParts[1].trim();

                LogMealCommand logMealCommand = new LogMealCommand(description, servings);
                logMealCommand.execute();
            }
        } else if (input.startsWith("list")) {
            if (input.trim().length() > 4) { //list meals
                new ListMealsCommand();
            } else { //list both

            }
        } else if (input.startsWith("show")) { //show calories, water, and goals
            String[] parts = input.split(" ");
            if (parts[1].startsWith("c")) { //shows calorie
                new ShowCaloriesCommand();
            } else if (parts[1].startsWith("w")) { //shows water
                ViewWaterIntakeCommand viewWaterIntakeCommand = new ViewWaterIntakeCommand();
                viewWaterIntakeCommand.execute();
            } else if (parts[1].startsWith("g")) {  //shows goals

            } else {
                System.out.println("Hello!\n");
            }
        }
    }

}
