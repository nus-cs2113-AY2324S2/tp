package seedu.duke.modules;

import java.util.ArrayList;

public class ModuleList {
    protected ArrayList<Module> moduleList;

    public ModuleList(int size) {
        this.moduleList = new ArrayList<Module>(size);
    }

    public void addModule(Module module) {
        moduleList.add(module);
    }
}
