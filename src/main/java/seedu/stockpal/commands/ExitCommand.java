package seedu.stockpal.commands;

import static seedu.stockpal.ui.Ui.printGoodbyeMessage;

public class ExitCommand extends Command {
    public static final String COMMAND_KEYWORD = "exit";
    public static final String COMMAND_USAGE = COMMAND_KEYWORD
            + ": Exits the program.\n"
            + "Format: exit";

    @Override
    public void execute() {
        printGoodbyeMessage();
    }
}
