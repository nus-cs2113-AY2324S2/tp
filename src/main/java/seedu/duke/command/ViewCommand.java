package seedu.duke.command;

import java.util.ArrayList;
import java.util.Map;

import seedu.duke.modules.Module;
import seedu.duke.ui.Ui;

public class ViewCommand extends Command{

    private String mode;
    private ArrayList<Module> takenModuleList = moduleList.getTakenModuleList();
    private ArrayList<Module> plannedModuleList = moduleList.getToBeTakenModuleList();

    public ViewCommand(String mode) {
        this.mode = mode.toLowerCase();
    }

    @Override
    public void execute(String userInput) {
        Map<Integer, ArrayList<Module>> takenModulesBySemMap = moduleList.groupModulesBySemester();

        if (mode.equals("plan")) {
            System.out.println("Not available. View taken/all to view the scheduled planner");
        } else {
            Ui.printModulePlan(takenModulesBySemMap);
        }
    }
}
