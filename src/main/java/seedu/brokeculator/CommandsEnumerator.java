package seedu.brokeculator;

public enum CommandsEnumerator {
    LIST_COMMAND("list"),
    SUMMARY_COMMAND("summarize"),
    EXIT_COMMAND("exit"),
    DELETE_COMMAND("delete"),
    ADD_COMMAND("add");
    private String string;
    private CommandsEnumerator(String string) {
        this.string = string;
    }
    public String getString() {
        return string;
    }
}
