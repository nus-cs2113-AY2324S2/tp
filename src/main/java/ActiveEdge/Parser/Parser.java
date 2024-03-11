package ActiveEdge.Parser;

import ActiveEdge.Command.ActiveEdgeException;
import ActiveEdge.Command.HelpCommand;
import ActiveEdge.Command.LogWaterCommand;
import ActiveEdge.Command.ViewWaterIntakeCommand;

public class Parser {
    public void handleInput(String input) throws ActiveEdgeException {
        if(input.contains("help")){
            new HelpCommand();
        } else if (input.startsWith("log")) {

        } else if (input.startsWith("list")) { //list meals, and list both

        } else if (input.startsWith("show")) { //show calories, water, and goals
            String[] parts = input.substring(5).split(" ");
            String inputTrimmed = parts[1].trim();
            if (inputTrimmed.startsWith("c")) { //shows calorie
                
            } else if (inputTrimmed.startsWith("w")) { //shows water

            } else if (inputTrimmed.startsWith("g")) { //shows goals
        } else {
            System.out.println("Hello!\n");
        }
    }
}
