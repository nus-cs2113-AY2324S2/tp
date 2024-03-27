package seedu.duke.command;

import seedu.duke.ui.Ui;

import java.util.ArrayList;

public class ViewGraduateCommand extends Command {

    private final ArrayList<String> modulesToComplete = moduleList.getModulesToComplete();


    @Override
    public void execute(String userInput) {
        Ui.printModulesToComplete(modulesToComplete);
    }
}
