package seedu.duke.command;

import java.util.ArrayList;
import java.util.Map;

import seedu.duke.modules.Module;
import seedu.duke.ui.Ui;

public class ViewCommand extends Command{

    private Map<Integer, ArrayList<Module>>  modulesBySemMap = moduleList.groupModulesBySemester();

    @Override
    public void execute(String userInput) {
        Ui.printModulePlan(modulesBySemMap);
    }
}
