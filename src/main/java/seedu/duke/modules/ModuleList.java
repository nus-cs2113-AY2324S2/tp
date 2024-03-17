package seedu.duke.modules;

import java.util.ArrayList;

public class ModuleList {
    protected ArrayList<Module> moduleList;

    public ModuleList(int size) {
        this.moduleList = new ArrayList<Module>(size);
    }

    public Module getModule(String courseCode) {
        for(Module module : moduleList){
            if(module.getModuleCode().equals(courseCode.toUpperCase())){
                return module;
            }
        }
        return null;
    }

    public void addModule(Module module) {
        moduleList.add(module);
    }

    public void removeModule(Module module) {
        moduleList.remove(module);
    }

    public void changeModuleGrade(int index) {
    }
}
