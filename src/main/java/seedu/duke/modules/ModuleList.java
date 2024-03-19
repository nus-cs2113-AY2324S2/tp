package seedu.duke.modules;

import java.util.ArrayList;

public class ModuleList {
    protected ArrayList<Module> takenModuleList;
    protected ArrayList<Module> toBeTakenModuleList;

    public ModuleList(int size) {
        this.takenModuleList = new ArrayList<Module>(size);
        this.toBeTakenModuleList = new ArrayList<Module>(size);
    }

    public Module getModule(String courseCode) {
        // check if the module is already taken
        for(Module module : takenModuleList){
            if(module.getModuleCode().equals(courseCode.toUpperCase())){
                return module;
            }
        }
        // check if the module is planned to be taken
        for(Module module : toBeTakenModuleList){
            if(module.getModuleCode().equals(courseCode.toUpperCase())){
                return module;
            }
        }
        return null;
    }

    public ArrayList<Module> getTakenModuleList() {
        return takenModuleList;
    }

    public ArrayList<Module> getToBeTakenModuleList() {
        return toBeTakenModuleList;
    }

    public void addModule(Module module) {
        if (module.getModuleStatus()) {
            takenModuleList.add(module);
        } else {
            toBeTakenModuleList.add(module);
        }
    }

    public void printModules() {
        for (Module module:takenModuleList) {
            System.out.println(module.getModuleCode());
        }
        for (Module module:toBeTakenModuleList) {
            System.out.println(module.getModuleCode());
        }
    }
    public void removeModule(Module module) {
        if (toBeTakenModuleList.contains(module)) {
            toBeTakenModuleList.remove(module);
        } else if (takenModuleList.contains(module)) {
            takenModuleList.remove(module);
        }
    }

    public void changeModuleGrade(String moduleCode, String grade) {
        Module toChange = getModule(moduleCode);
        if (toChange == null) {
            System.out.println("This module does not exist in the list");
            return;
        }
        toChange.setModuleGrade(grade);
    }

    public double tallyGPA() {
        int totalMC = 0;
        double sumOfGPA = 0;
        for (Module module : takenModuleList) {
            if(module.getModuleGrade() == null || module.getModuleGrade().equals("CS") ||
                    module.getModuleGrade().equals("CU") ) {
                continue;
            }
            totalMC += module.getModuleMC();
            sumOfGPA += module.getGradeNumber() * module.getModuleMC();
        }
        return sumOfGPA/(double)totalMC;
    }


}
