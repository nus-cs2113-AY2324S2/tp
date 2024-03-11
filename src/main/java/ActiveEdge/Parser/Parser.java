package ActiveEdge.Parser;

import ActiveEdge.Command.DailyCaloriesCommand;
import ActiveEdge.Command.HelpCommand;

public class Parser {
    public void handleInput(String input) {
        if(input.contains("help")){
            new HelpCommand();
        } else if (input.startsWith("log")) {

        } else if (input.startsWith("list")) {
            if (input.trim().length() > 4) { //list meals

            } else { //list both

            }
        } else if (input.startsWith("show")) { //show calories, water, and goals
            String[] parts = input.substring(5).split(" ");
            String inputTrimmed = parts[1].trim();
            if (inputTrimmed.startsWith("c")) { //shows calorie
                new DailyCaloriesCommand(input);
            } else if (inputTrimmed.startsWith("w")) { //shows water

            } else if (inputTrimmed.startsWith("g")) { //shows goals

            }
        } else {
            System.out.println("Hello!\n");
        }
    }
}
