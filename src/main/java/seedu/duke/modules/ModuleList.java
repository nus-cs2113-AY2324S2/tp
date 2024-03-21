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
        if (courseCode == null || courseCode.trim().isEmpty()) {
            throw new IllegalArgumentException("Course code cannot be null or empty.");
        }
        courseCode = courseCode.toUpperCase(); // Convert once and reuse, improving efficiency

        for (Module module : takenModuleList) {
            if (module.getModuleCode().equals(courseCode)) {
                return module;
            }
        }
        for (Module module : toBeTakenModuleList) {
            if (module.getModuleCode().equals(courseCode)) {
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
        if (module == null) {
            throw new IllegalArgumentException("Module cannot be null.");
        }
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
        if (module == null) {
            throw new IllegalArgumentException("Module cannot be null.");
        }
        // The remove operation returns false if the item was not found
        boolean removed = toBeTakenModuleList.remove(module) || takenModuleList.remove(module);
        if (!removed) {
            System.out.println("Module not found in either list.");
        }
    }

    public void changeModuleGrade(String moduleCode, String grade) {
        if (moduleCode == null || moduleCode.trim().isEmpty()) {
            throw new IllegalArgumentException("Module code cannot be null or empty.");
        }
        Module toChange = getModule(moduleCode);
        if (toChange == null) {
            throw new IllegalStateException("This module does not exist in the list.");
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
