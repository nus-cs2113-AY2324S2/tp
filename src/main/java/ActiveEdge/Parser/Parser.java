package ActiveEdge.Parser;

import ActiveEdge.Command.ActiveEdgeException;
import ActiveEdge.Command.HelpCommand;
import ActiveEdge.Command.LogWaterCommand;
import ActiveEdge.Command.ViewWaterIntakeCommand;

public class Parser {
    public void handleInput(String input) throws ActiveEdgeException {
        if(input.contains("help")){
            new HelpCommand();
        }else {
            System.out.println("Hello!\n");
        }
    }
}
