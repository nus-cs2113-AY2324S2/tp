package seedu.duke.command;

public class GradeCommand extends Command {
    private String moduleCode;
    private String moduleGrade;
    public GradeCommand(String moduleCode, String moduleGrade) {
        this.moduleCode = moduleCode;
        this.moduleGrade = moduleGrade;
    }
    @Override
    public void execute(String userInput) {
        moduleList.changeModuleGrade(moduleCode, moduleGrade);
    }
}
