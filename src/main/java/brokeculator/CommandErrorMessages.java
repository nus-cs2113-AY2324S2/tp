package brokeculator;

public enum CommandErrorMessages {
    INVALID_EXIT_COMMAND("Invalid command. Please enter 'exit' to exit the program.");
    private String string;
    private CommandErrorMessages(String string) {
        this.string = string;
    }
    public String getString() {
        return string;
    }
}
