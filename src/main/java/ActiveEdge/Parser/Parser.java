package ActiveEdge.Parser;
import ActiveEdge.Command.ActiveEdgeException;
import ActiveEdge.Command.HelpCommand;
import ActiveEdge.Command.LogWaterCommand;
import ActiveEdge.Command.ViewWaterIntakeCommand;
import ActiveEdge.FoodData;
import ActiveEdge.Command.*;

public class Parser {

    public void handleInput(String input) throws ActiveEdgeException {
        String inputTrimmed;
        if (input.contains("help")) {
            new HelpCommand();
        }
        else if (input.equalsIgnoreCase("list foods")) {
            FoodData.printFood();
        }
//        else if (foodStorage.containsFood(input)) {
//            int calories = foodStorage.getCalories(input);
//            System.out.println(input + " contains " + calories + " calories.");
//        } else {
//            System.out.println("Sorry, I don't have information about " + input);
//        }
        else if (input.startsWith("log")) {
            String parts = input.substring(4);
            String[] items = parts.split("/");
            if (items[0].equals("w")) {
                String quantityString = items[1];
                try {
                    int quantity = Integer.parseInt(quantityString);
                    if (quantity <= 0) {
                        System.out.println("Water quantity must be a positive integer.");
                        return;
                    }
                    LogWaterCommand logWaterCommand = new LogWaterCommand(quantity);
                    logWaterCommand.execute();
                } catch (NumberFormatException e) {
                    System.out.println("Invalid water quantity. Please provide a valid integer.");
                }

            }

            new LogMealCommand(input);
        }       else if (input.startsWith("list")) {
                if (input.trim().length() > 4) { //list meals
                    new ListMealsCommand();
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

