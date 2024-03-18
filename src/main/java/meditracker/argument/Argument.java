package meditracker.argument;

/**
 * Argument class to store the name of the argument,
 * corresponding flag for it, the prompt that will be used
 * to guide the user to input that argument, help message
 * if user require additional context and whether the argument
 * is optional or not.
 */
public class Argument {
    private final ArgumentName name;
    private final String flag;
    private final String prompt;
    private final String help;
    private final boolean isOptional;

    /**
     * Constructs Argument object with the fields required to
     * handle this input type
     *
     * @param name Name of the argument
     * @param flag Corresponding flag for the argument, takes the form of "-*"
     *             where "*" represents a single letter
     * @param prompt Guided prompt message
     * @param help Help message for additional context
     * @param isOptional Whether this argument is optional or required
     */
    public Argument(ArgumentName name, String flag, String prompt, String help, boolean isOptional) {
        this.name = name;
        this.flag = flag;
        this.prompt = prompt;
        this.help = help;
        this.isOptional = isOptional;
    }

    public ArgumentName getName() {
        return name;
    }

    public String getFlag() {
        return flag;
    }

    public String getPrompt() {
        return prompt;
    }

    public String getHelp() {
        return help;
    }

    public boolean isOptional() {
        return isOptional;
    }
}
