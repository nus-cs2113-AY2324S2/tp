package seedu.duke.modules;

public class Module {
    private String moduleCode;
    private String moduleGrade;
    private String moduleMC;

    public Module(String moduleCode, String moduleGrade, String moduleMC) {
        this.moduleCode = moduleCode;
        this.moduleGrade = moduleGrade;
        this.moduleMC = moduleMC;
    }

    public String getModuleCode() {
        return moduleCode;
    }

    public void setModuleCode(String moduleCode) {
        this.moduleCode = moduleCode;
    }

    public String getModuleGrade() {
        return moduleGrade;
    }

    public void setModuleGrade(String moduleGrade) {
        this.moduleGrade = moduleGrade;
    }

    public String getModuleMC() {
        return moduleMC;
    }

    public void setModuleMC(String moduleMC) {
        this.moduleMC = moduleMC;
    }

    @Override
    public String toString() {
        return "Module{" +
                "moduleCode='" + moduleCode + '\'' +
                ", moduleGrade='" + moduleGrade + '\'' +
                ", moduleMC='" + moduleMC + '\'' +
                '}';
    }
}
