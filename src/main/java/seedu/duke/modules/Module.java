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
        this.moduleGrade = null;
    }

    public String getModuleCode() {
        return moduleCode;
    }

    public void setModuleCode(String moduleCode) {
        if (moduleCode == null || moduleCode.trim().isEmpty()) {
            throw new IllegalArgumentException("Module code cannot be null or empty.");
        }
        this.moduleCode = moduleCode;
    }

    public String getModuleGrade() {
        return moduleGrade;
    }

    public void setModuleGrade(String moduleGrade) {

        if (moduleGrade != null && !moduleGrade.matches("A\\+|A|A-|B\\+|B|B-|C\\+|C|D\\+|D|F")) {
            throw new IllegalArgumentException("Invalid module grade.");
        }
        this.moduleGrade = moduleGrade;
    }

    public int getModuleMC() {
        return moduleMC;
    }

    public void setModuleMC(int moduleMC) {
        if (moduleMC <= 0) {
            throw new IllegalArgumentException("Module MC (Modular Credits) must be positive.");
        }
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
        if (moduleDate <= 0) {
            throw new IllegalArgumentException("Module date must be a positive number.");
        }
        this.moduleDate = moduleDate;
    }

    public double getGradeNumber () {
        switch (moduleGrade) {
        case "A+":
            //fall through
        case "A":
            return 5.0;
        case "A-":
            return 4.5;
        case "B+":
            return 4.0;
        case "B":
            return 3.5;
        case "B-":
            return 3.0;
        case "C+":
            return 2.5;
        case "C":
            return 2.0;
        case "D+":
            return 1.5;
        case "D":
            return 1.0;
        case "F":
            return 0;
        default:
            throw new IllegalStateException("Invalid or unassigned module grade.");
        }
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
