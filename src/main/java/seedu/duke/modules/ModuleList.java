package seedu.duke.modules;

import java.util.ArrayList;
import seedu.duke.FAP;

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

    public void addModule(Module module) {
        if (module.getModuleStatus()) {
            takenModuleList.add(module);
        } else {
            toBeTakenModuleList.add(module);
        }
    }

    public void removeModule(Module module) {
        //moduleList.remove(module);
    }

    public void changeModuleGrade(int index) {
    }
}
