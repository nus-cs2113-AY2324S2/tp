package brokeculator.enumerators;

public enum CommandErrorMessages {
    INVALID_EXIT_COMMAND("Invalid command. Please enter 'exit' to exit the program."),
    INVALID_ADD_COMMAND("Invalid input format for add command."
            + "Please use the format: add /n <description> /d <date> /a <amount> /c [optional] <category>");
    private String string;
    private CommandErrorMessages(String string) {
        this.string = string;
    }
    public String getString() {
        return string;
    }
}
