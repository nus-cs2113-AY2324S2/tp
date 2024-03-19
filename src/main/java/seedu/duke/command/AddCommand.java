package seedu.duke.command;

import seedu.duke.modules.Module;

public class AddCommand extends Command{
    private String moduleCode;
    private String moduleGrade;
    private int moduleMC;
    private boolean moduleStatus;
    private int moduleDate;

    public AddCommand(String moduleCode, int moduleMC, boolean moduleStatus, int moduleDate) {
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
        Module newModule = new Module(moduleCode, moduleMC, moduleStatus, moduleDate);
        moduleList.addModule(newModule);
        moduleList.printModules();
    }
}
