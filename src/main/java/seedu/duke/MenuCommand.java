package seedu.duke;
public class MenuCommand extends Command {
    private int index;
    private Ui ui;

    public MenuCommand(int index) {
        this.index = index;
        ui = new Ui();
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
