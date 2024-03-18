package seedu.duke.command;

import java.util.ArrayList;
import seedu.duke.modules.Module;

public class ViewCommand extends Command{

    private String mode;

    public ViewCommand(String mode) {
        this.mode = mode.toLowerCase();
    }

    private void printModuleList(ArrayList<Module> list) {
        for (Module module : list) {
            System.out.print(module.getModuleCode() + ", ");
        }
        System.out.println("");
    }

    @Override
    public void execute(String userInput) {
        switch (mode) {
        case "all":
            System.out.println("Taken modules: ");
            //printModuleList(takenModuleList);
            System.out.println("Planned modules: ");
            //printModuleList(planModuleList);
            break;
        case "taken":
            System.out.println("Taken modules: ");
            //printModuleList(takenModuleList);
            break;
        case "plan":
            System.out.println("Planned modules: ");
            //printModuleList(planModuleList);
            break;
        default:
            System.out.println("Invalid mode.");
        }
    }
}
