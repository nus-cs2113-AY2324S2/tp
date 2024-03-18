package command;

import ui.Ui;

public class HelpCommand extends Command {
    public HelpCommand(){
        commandDescription = "HelpMe!!";
    }
    @Override
    public void execute() {
        Ui ui = new Ui();
        ui.printHelpMenu();
    }
}
