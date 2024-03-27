package seedu.duke.modules;

import org.junit.jupiter.api.Test;
import seedu.duke.exceptions.ModuleNotFoundException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ModuleListTest {

    @Test
    void addModules() {
        ModuleList moduleList = new ModuleList();
        moduleList.addModule(new Module("CS1010", 4,4, ""));
        moduleList.addModule(new Module("CS1231",4, 4, ""));
        assertEquals(moduleList.moduleList.get(0).getModuleCode(), "CS1010");
        assertEquals(moduleList.moduleList.get(1).getModuleCode(), "CS1231");
    }

    void getModules() {
        ModuleList moduleList = new ModuleList();
        moduleList.moduleList.add(new Module("CS1010",4, 4, ""));
        moduleList.moduleList.add(new Module("CS1231",4,4, ""));
        try {
            assertTrue(new Module("CS1010", 4, 4, "") == moduleList.getModule("CS1010"));
        } catch (ModuleNotFoundException e) {
            System.out.println("Module not found in list");
        }
    }
}
