package seedu.budgetbuddy.command;

import seedu.budgetbuddy.Ui;

import java.util.logging.Logger;
import java.util.logging.Level;

public class MenuCommand extends Command {

    private static Logger logger = Logger.getLogger("MenuCommandLogger");
    private int index;
    private Ui ui;

    public MenuCommand(int index) {
        assert index >= 0 : "Index should be a positive number";
        this.index = index;
        ui = new Ui();

    }

    public int getIndex() {
        assert index >= 0 : "Index must be a positive number";

        return this.index;
    }

    /**
     * Executes the menu command by showing the menu titles or a specific menu item.
     *
     */
    @Override
    public void execute() {
        assert index >= 0 : "Index must be a positive number";

        logger.log(Level.INFO, "Starting the processing of Menu Command with Index :" + index);

        if (index == 0) {
            logger.log(Level.INFO, "Displaying all Menu Items");

            ui.showMenuTitles();
        } else {
            logger.log(Level.INFO, "Displaying Menu Items at Index : " + index);

            ui.showMenuItem(index);
        }

        logger.log(Level.INFO, "End of Processing of Menu Command");
    }
}
