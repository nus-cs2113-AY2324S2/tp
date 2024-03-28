package seedu.duke;

public class Command {
    private String commandName;
    private String commandFunction;
    private String commandUsage;

    public Command(String commandName, String commandFunction, String commandUsage) {
        this.commandName = commandName;
        this.commandFunction = commandFunction;
        this.commandUsage = commandUsage;
    }


    public String getCommandName() {
        return commandName;
    }

    public String getCommandFunction() {
        return commandFunction;
    }

    public String getCommandUsage() {
        return commandUsage;
    }
}
