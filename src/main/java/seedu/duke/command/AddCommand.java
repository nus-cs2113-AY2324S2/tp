package seedu.duke.command;

import seedu.duke.exceptions.ModuleException;
import seedu.duke.modules.Module;

public class AddCommand extends Command{
    private String moduleCode;
    private String moduleGrade;
    private int moduleMC;
    private boolean moduleStatus;
    private int moduleDate;

    public AddCommand(String moduleCode, int moduleMC, boolean moduleStatus, int moduleDate) {
        assert moduleCode != null && !moduleCode.trim().isEmpty() : "Module code cannot be null or empty";
        assert moduleMC > 0 : "Module MC (Modular Credits) must be positive";
        assert moduleDate > 0 : "Module date must be a positive number";

        this.moduleCode = moduleCode;
        this.moduleMC = moduleMC;
        this.moduleStatus = moduleStatus;
        this.moduleDate = moduleDate;
    }

    //to not throw error
    public AddCommand() {

    }

    @Override
    public void execute(String userInput) {
        try {
            // Assuming moduleList is a class attribute of Command
            if (moduleList == null) {
                throw new ModuleException("Module list is not initialized.");
            }

            Module newModule = new Module(moduleCode, moduleMC, moduleStatus, moduleDate);
            moduleList.addModule(newModule);
            moduleList.printModules();
        } catch (ModuleException e) {
            System.err.println("Failed to add module: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("An unexpected error occurred: " + e.getMessage());
        }
    }
}
