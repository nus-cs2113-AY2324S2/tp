package command;

import activeedge.ui.CommandUi;

public class FindCommand {
    static final String LINE = "____________________________________________________________\n";

    public FindCommand(String input) throws ActiveEdgeException {
        if(input.trim().length() > 4){
            String word = input.split(" ")[1];
            CommandUi.printMatchingTasks(word);
        } else {
            System.out.println(LINE +"Oh no! You are missing the keyword you want to search for!\n" + LINE);
        }

    }
}