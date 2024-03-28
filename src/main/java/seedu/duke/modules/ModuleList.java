package seedu.duke.modules;

import seedu.duke.enums.CEGModules;
import seedu.duke.exceptions.GpaNullException;
import seedu.duke.exceptions.ModuleException;
import seedu.duke.exceptions.ModuleNotFoundException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;

import static seedu.duke.FAP.LOGGER;

public class ModuleList {
    private static final int NUM_SEMESTERS = 8;

    protected ArrayList<Module> moduleList;

    public ModuleList() {
        this.moduleList = new ArrayList<Module>();
    }

    public Module getModule(String courseCode) throws ModuleNotFoundException {
        if (courseCode == null || courseCode.trim().isEmpty()) {
            throw new IllegalArgumentException("Course code cannot be null or empty.");
        }
        courseCode = courseCode.toUpperCase(); // Convert once and reuse, improving efficiency

        for (Module module : moduleList) {
            if (module.getModuleCode().equals(courseCode)) {
                return module;
            }
        }
        throw new ModuleNotFoundException("Module " + courseCode + " not found!");
    }

    public ArrayList<Module> getTakenModuleList() {
        return moduleList;
    }

    public void addModule(Module module) {
        if (module == null) {
            throw new IllegalArgumentException("Module cannot be null.");
        }
        moduleList.add(module);
        System.out.println("Added the new module: " + module.getModuleCode() + "\n" + module.getModuleDescription());
    }

    public void printModules() {
        for (Module module:moduleList) {
            System.out.println(module.getModuleCode());
        }
    }

    public void removeModule(Module module) {
        assert module != null : "Module cannot be null";
        // The remove operation returns false if the item was not found
        boolean removed = moduleList.remove(module);
        if (!removed) {
            System.out.println("Module not found in either list.");
        }
    }

    public void changeModuleGrade(String moduleCode, String grade) {
        if (moduleCode == null || moduleCode.trim().isEmpty()) {
            throw new IllegalArgumentException("Module code cannot be null or empty.");
        }
        try {
            Module toChange = getModule(moduleCode);
            toChange.setModuleGrade(grade);
            System.out.println("Grade for " + moduleCode + " updated to " + grade);
            assert toChange.getModuleGrade().equals(grade) : "Grade is not updated successfully";

        } catch (ModuleNotFoundException e) {
            System.out.println("Module not found in list");
        } catch (ModuleException e) {
            System.out.println(e.getMessage());
        }
    }

    public double tallyGPA() throws GpaNullException {
        int totalMC = 0;
        double sumOfGPA = 0;

        for (Module module : moduleList) {
            if(module.getModuleGrade() == null || module.getModuleGrade().equals("CS") ||
                    module.getModuleGrade().equals("CU") ) {
                continue;
            }
            totalMC += module.getModuleMC();
            sumOfGPA += module.getGradeNumber() * module.getModuleMC();
        }
        if (totalMC == 0) {
            LOGGER.log(Level.INFO, "No modules with grades available to tabulate GPA.");
            throw new GpaNullException("No countable grades present to tally.");
        }
        return sumOfGPA / (double) totalMC;
    }

    public Map<Integer, ArrayList<Module>> groupModulesBySemester() {
        Map<Integer, ArrayList<Module>> moduleBySemMap = new HashMap<>();
        for (int i = 1; i <= NUM_SEMESTERS; i++) {
            moduleBySemMap.put(i, new ArrayList<>());
        }

        for (Module module : moduleList) {
            moduleBySemMap.get(module.getModuleDate()).add(module);
        }

        return moduleBySemMap;
    }

    public boolean containsModule(String moduleCode) {
        for (Module takenModule : moduleList) {
            if (moduleCode.equals(takenModule.getModuleCode())) {
                return true;
            }
        }
        return false;
    }
    
    public ArrayList<String> getModulesToComplete() {
        ArrayList<String> modulesToComplete = new ArrayList<>();
        for (CEGModules cegModule : CEGModules.values()) {
            if (!containsModule(cegModule.name())) {
                modulesToComplete.add(cegModule.name());
            }
        }
        return modulesToComplete;
    }
}
