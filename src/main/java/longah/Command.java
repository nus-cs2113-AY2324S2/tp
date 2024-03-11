/**
 * The Command class parses user input into a keyword and arguments.
 */
public class Command {
    private String[] parts;

    /**
     * Constructs a new Command instance with the given input.
     *
     * @param input The user input.
     */
    public Command(String input) {
        this.parts = input.split(" ");
    }

    /**
     * Gets the command keyword.
     *
     * @return The command keyword.
     */
    public String getKeyword() {
        return parts[0];
    }

    /**
     * Gets the arguments of the command.
     *
     * @return The arguments of the command.
     */
    public String[] getArguments() {
        if (parts.length > 1) {
            return parts[1].split("/");
        }
        return new String[0];
    }

    /**
     * Checks if the command is an "add" command.
     *
     * @return True if the command is an "add" command, false otherwise.
     */
    public boolean isAddCommand() {
        
    }

    /**
     * Checks if the command is a "list" command.
     *
     * @return True if the command is a "list" command, false otherwise.
     */
    public boolean isListCommand() {
       
    }

    /**
     * Checks if the command is a "delete" command.
     *
     * @return True if the command is a "delete" command, false otherwise.
     */
    public boolean isDeleteCommand() {
       
    }

    /**
     * Checks if the command is a "find" command.
     *
     * @return True if the command is a "find" command, false otherwise.
     */
    public boolean isFindCommand() {
     
    }

    /**
     * Checks if the command is a "clear" command.
     *
     * @return True if the command is a "clear" command, false otherwise.
     */
    public boolean isClearCommand() {
       
    }

    /**
     * Checks if the command is an "exit" command.
     *
     * @return True if the command is an "exit" command, false otherwise.
     */
    public boolean isExitCommand() {
       
    }
}
