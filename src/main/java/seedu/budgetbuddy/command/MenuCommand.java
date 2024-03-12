package seedu.budgetbuddy.command;

import seedu.budgetbuddy.Ui;

public class MenuCommand extends Command {
    private int index;
    private Ui ui;

    public MenuCommand(int index) {
        this.index = index;
        ui = new Ui();
    }

    public int getIndex() {
        return this.index;
    }

    /**
     * Executes the menu command by showing the menu titles or a specific menu item.
     *
     */
    @Override
    public void execute() {
        if (index == 0) {
            ui.showMenuTitles();
        } else {
            ui.showMenuItem(index);
        }
    }
}
