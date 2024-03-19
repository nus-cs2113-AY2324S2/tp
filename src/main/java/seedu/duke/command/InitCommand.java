package seedu.duke.command;

public class InitCommand extends Command{
    private final String Name;
    public InitCommand(String name) {
        this.Name = name;
    }

    @Override
    public void execute(String userInput) {
        System.out.println("Hello " + Name + "!");
        System.out.println("What would you like to do today?");
    }
}
