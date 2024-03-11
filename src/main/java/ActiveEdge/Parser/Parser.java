package ActiveEdge.Parser;

import ActiveEdge.Command.HelpCommand;

public class Parser {
    public void handleInput(String input) {
        if(input.contains("help")){
            new HelpCommand();
        } else {
            System.out.println("Hello!\n");
        }
    }
}
