package seedu.duke.command;

import seedu.duke.ui.Ui;

import java.util.ArrayList;

public class ViewGraduateCommand extends Command {

    private final ArrayList<String> modulesToComplete = moduleList.getModulesToComplete();
    private final Integer unrestrictedElectiveMCToComplete = moduleList.getUEModuleMCToComplete();


    @Override
    public void execute(String userInput) {
        Ui.printModulesToComplete(modulesToComplete, unrestrictedElectiveMCToComplete);
    }
}
