package seedu.duke.command;

import java.util.Map;

public class InitCommand extends Command{
    private final String name;
    public InitCommand(String name) {
        this.name = name;
    }

    @Override
    public void execute(String userInput) {
        System.out.println("Hello " + name + "!");
        System.out.println("What would you like to do today?");
    }
}
