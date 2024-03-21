package seedu.duke.modules;

import seedu.duke.exceptions.GpaException;
import seedu.duke.exceptions.ModuleException;
import seedu.duke.exceptions.ModuleNotFoundException;

import java.util.ArrayList;
import java.util.logging.Level;

import static seedu.duke.FAP.LOGGER;

public class ModuleList {
    protected ArrayList<Module> takenModuleList;
    protected ArrayList<Module> toBeTakenModuleList;

    public ModuleList(int size) {
        this.takenModuleList = new ArrayList<Module>(size);
        this.toBeTakenModuleList = new ArrayList<Module>(size);
    }

    public Module getModule(String courseCode) throws ModuleNotFoundException {
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
        throw new ModuleNotFoundException("Module " + courseCode + " not found!");
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
        assert module != null : "Module cannot be null";
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
        try{
            Module toChange = getModule(moduleCode);
            toChange.setModuleGrade(grade);
            System.out.println("Grade for " + moduleCode + " updated to " + grade);
            assert toChange.getModuleGrade().equals(grade) : "Grade is not updated successfully";

        } catch (ModuleNotFoundException e){
            System.out.println("Module not found in list");
        } catch (ModuleException e){
            System.out.println(e.getMessage());
        }
    }

    public double tallyGPA() throws GpaException {
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
        if(sumOfGPA == 0) {
            LOGGER.log(Level.INFO, "No modules with grades available to tabulate GPA.");
            throw new GpaException("No countable grades present to tally.");
        }
        return sumOfGPA/(double)totalMC;
    }
}
