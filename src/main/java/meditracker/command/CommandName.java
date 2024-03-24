package meditracker.command;

/**
 * The CommandName enum represents the names of commands supported by the application.
 */
public enum CommandName {
    EXIT("exit"),
    ADD("add"),
    MODIFY("modify"),
    LIST("list"),
    DELETE("delete"),
    SEARCH("search"),
    TAKE("take"),
    UNTAKE("untake");

    public final String value;

    /**
     * Constructs a CommandName enum with the specified string value.
     *
     * @param value The string value associated with the command name.
     */
    CommandName(String value) {
        this.value = value;
    }

    /**
     * Returns the CommandName enum based on the provided string label.
     *
     * @param label The string label representing a command name.
     * @return The CommandName enum corresponding to the label, or null if not found.
     * @author Baeldung
     * Reused from https://www.baeldung.com/java-enum-values
     * with minor modifications
     */
    public static CommandName valueOfLabel(String label) {
        for (CommandName e : values()) {
            if (e.value.equals(label)) {
                return e;
            }
        }
        return null;
    }
}
