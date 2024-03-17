package seedu.duke.modules;

public class Module {
    private String moduleCode;
    private String moduleGrade;
    private int moduleMC;
    private boolean moduleStatus;
    private int moduleDate;

    public Module(String moduleCode, int moduleMC, boolean moduleStatus, int moduleDate) {
        this.moduleCode = moduleCode;
        this.moduleMC = moduleMC;
        this.moduleStatus = moduleStatus;
        this.moduleDate = moduleDate;
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

    public int getModuleMC() {
        return moduleMC;
    }

    public void setModuleMC(int moduleMC) {
        this.moduleMC = moduleMC;
    }

    public boolean getModuleStatus() {
        return moduleStatus;
    }

    public void setModuleStatus(boolean moduleStatus) {
        this.moduleStatus = moduleStatus;
    }

    public int getModuleDate() {
        return moduleDate;
    }

    public void setModuleDate(int moduleDate) {
        this.moduleDate = moduleDate;
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
