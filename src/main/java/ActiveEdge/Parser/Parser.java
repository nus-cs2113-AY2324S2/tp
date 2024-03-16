package ActiveEdge.Parser;

import java.io.IOException;
import java.util.Scanner;
import ActiveEdge.Command.ActiveEdgeException;
import ActiveEdge.Command.HelpCommand;
import ActiveEdge.Command.LogWaterCommand;
import ActiveEdge.Command.ViewWaterIntakeCommand;
import ActiveEdge.Task.LogMeals;
import ActiveEdge.Task.Task;
import ActiveEdge.Task.TaskList;
import ActiveEdge.Command.*;

import static ActiveEdge.Task.TaskList.tasksList;


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
            if (tasksList.size() > 0) {
                if (input.substring(4).trim().contains("meals")) { //list meals
                    new ListMealsCommand();
                } else { //list both

                }
            } else {
                System.out.println("There are no items in your list!");
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
                ShowGoalsCommand showGoalsCommand = new ShowGoalsCommand();
                showGoalsCommand.execute();
            } else {
                System.out.println("Hello!\n");
            }
        }
        else if (input.startsWith("set goal")) {
            // Handle setting goals
            String[] parts = input.split("/");
            if (parts.length != 2) {
                System.out.println("Invalid command. Please use the format 'set goal c/NUMBER' or 'set goal w/NUMBER'.");
                return;
            }
            String goalType = parts[0].substring(parts[0].length() - 1);
            try {
                int goalAmount = Integer.parseInt(parts[1]);
                if (goalAmount <= 0) {
                    System.out.println("Goal amount must be a positive integer.");
                    return;
                }
                if (goalType.equals("c")) {
                    new AddGoalsCommand(goalType, goalAmount).execute(); // Execute AddGoalCommand for setting calorie goal
                } else if (goalType.equals("w")) {
                    new AddGoalsCommand(goalType, goalAmount).execute(); // Execute AddGoalCommand for setting water goal
                } else {
                    System.out.println("Invalid goal type. Please use 'c' for calories or 'w' for water.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid goal amount. Please provide a valid integer.");
            }
        } else {
            System.out.println("Unknown command.");
        }
    }

}
