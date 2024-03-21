package seedu.duke.command;

import java.util.ArrayList;
import java.util.logging.Level;

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
        try {
            assert mode != null : "mode should be present";
            logger.log(Level.INFO, "mode: " + mode);

            switch (mode) {
            case "all":
                System.out.println("Taken modules: ");
                printModuleList(moduleList.getTakenModuleList());
                System.out.println("Planned modules: ");
                printModuleList(moduleList.getToBeTakenModuleList());
                break;
            case "taken":
                System.out.println("Taken modules: ");
                printModuleList(moduleList.getTakenModuleList());
                break;
            case "plan":
                System.out.println("Planned modules: ");
                printModuleList(moduleList.getToBeTakenModuleList());
                break;
            default:
                System.out.println("Invalid mode.");
            }
        } catch (AssertionError e) {
            System.out.println("ViewCommand instance has invalid mode");
        }
    }
}
