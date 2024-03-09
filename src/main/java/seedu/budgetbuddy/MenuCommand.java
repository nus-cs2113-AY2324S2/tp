package seedu.budgetbuddy;
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

    @Override
    public void execute() {
        if (index == 0) {
            ui.showMenuTitles();
        } else {
            ui.showMenuItem(index);
        }
    }
}
