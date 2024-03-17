package seedu.duke.command;

import seedu.duke.modules.Module;
import seedu.duke.modules.ModuleList;

public class AddCommand extends Command{
    private String moduleCode;
    private String moduleGrade;
    private String moduleMC;
    private ModuleList moduleList;

    public AddCommand(String moduleCode, String moduleGrade, String moduleMC, ModuleList moduleList) {
        this.moduleCode = moduleCode;
        this.moduleGrade = moduleGrade;
        this.moduleMC = moduleMC;
    }

    //to not throw error
    public AddCommand() {

    }

    @Override
    public void execute(String userInput) {
        Module newModule = new Module(moduleCode, moduleGrade, moduleMC);
        moduleList.addModule(newModule);
    }
}
