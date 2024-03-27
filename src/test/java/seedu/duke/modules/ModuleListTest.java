package seedu.duke.modules;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.duke.exceptions.ModuleNotFoundException;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class ModuleListTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    void addModules() {
        ModuleList moduleList = new ModuleList();
        moduleList.addModule(new Module("CS1010", 4,4, ""));
        moduleList.addModule(new Module("CS1231",4, 4, ""));
        assertEquals(moduleList.moduleList.get(0).getModuleCode(), "CS1010");
        assertEquals(moduleList.moduleList.get(1).getModuleCode(), "CS1231");
    }
    @Test
    void getModules() {
        ModuleList moduleList = new ModuleList();
        moduleList.moduleList.add(new Module("CS1010",4, 4, ""));
        try {
            Module obtainedModule = moduleList.getModule("CS1010");
            assertEquals("CS1010", obtainedModule.getModuleCode());
            assertEquals(4, obtainedModule.getModuleMC());
            assertEquals(4, obtainedModule.getModuleDate());
        } catch (ModuleNotFoundException e) {
            System.out.println("Module not found in list");
        }
    }
    @Test
    void changeModuleGrade() {
        ModuleList moduleList = new ModuleList();
        moduleList.addModule(new Module("CS1010", 4,4, ""));
        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));
        moduleList.changeModuleGrade("CG1111A","A");
        assertEquals("Module not found in list", outputStreamCaptor.toString().trim());
        assertNull(moduleList.moduleList.get(0).getModuleGrade());
        try {
            moduleList.getModule("CS1010").setModuleTaken(true);
            moduleList.changeModuleGrade("CS1010","A");
            assertEquals("A",moduleList.getModule("CS1010").getModuleGrade());
        } catch (ModuleNotFoundException e) {
            throw new RuntimeException(e);
        }
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class,
                () -> moduleList.changeModuleGrade("",""));
        assertEquals("Module code cannot be null or empty.", thrown.getMessage());
    }

}
