package ActiveEdge.Parser;

import java.util.Scanner;
import ActiveEdge.Command.ActiveEdgeException;
import ActiveEdge.Command.HelpCommand;
import ActiveEdge.Task.Task;
import ActiveEdge.Task.TaskList;


import static ActiveEdge.Task.TaskList.tasksList;

public class Parser {

    public void handleInput(String input) throws ActiveEdgeException {
        String inputTrimmed = null;
        if (input.contains("help")) {
            new HelpCommand();
        } else if (input.startsWith("log")) {
            String[] parts = input.substring(4).split(" ");
            inputTrimmed = parts[1].trim();
            if (inputTrimmed.startsWith("w")) {
            }

        } else if (input.startsWith("list")) {
            if (input.trim().length() > 4) { //list meals

            } else { //list both

            }
        } else if (input.startsWith("show")) { //show calories, water, and goals
            String[] parts = input.substring(5).split(" ");
            inputTrimmed = parts[1].trim();
            if (inputTrimmed.startsWith("c")) { //shows calorie

            } else if (inputTrimmed.startsWith("w")) { //shows water

            } else if (inputTrimmed.startsWith("w")) { //shows water
            } else if (inputTrimmed.startsWith("g")) { //shows goals
            } else {
                System.out.println("Hello!\n");
            }
        }
    }
}
