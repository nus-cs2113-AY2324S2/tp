package seedu.duke.command;

import seedu.duke.exceptions.ModuleException;
import seedu.duke.exceptions.ModuleNotFoundException;
import seedu.duke.modules.Module;

import static seedu.duke.FAP.jsonManager;

public class AddCommand extends Command{
    private String moduleCode;
    private int moduleDate;

    private int moduleMC;

    public AddCommand(String moduleCode, int moduleDate) throws ModuleNotFoundException {
        assert moduleCode != null && !moduleCode.trim().isEmpty() : "Module code cannot be null or empty";
        assert moduleDate > 0 : "Module date must be a positive number";

        if (jsonManager.moduleExist(moduleCode)) {
            jsonManager.getModuleInfo(moduleCode);
            this.moduleMC = jsonManager.getModuleMC();
            this.moduleCode = moduleCode;
            this.moduleDate = moduleDate;
        } else {
            throw new ModuleNotFoundException("Module do not exist in NUS!");
        }
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

            Module newModule = new Module(moduleCode, moduleMC, moduleDate, jsonManager.getModuleTitle());
            moduleList.addModule(newModule);
        } catch (ModuleException e) {
            System.err.println("Failed to add module: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("An unexpected error occurred: " + e.getMessage());
        }
    }
}
