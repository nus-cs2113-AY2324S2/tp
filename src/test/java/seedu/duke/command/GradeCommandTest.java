package seedu.duke.command;

import org.junit.jupiter.api.Test;
import seedu.duke.exceptions.ModuleNotFoundException;
import seedu.duke.modules.Module;
import seedu.duke.modules.ModuleList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GradeCommandTest {

    @Test
    void execute() {
        try {
            ModuleList moduleList = new ModuleList();
            moduleList.addModule(new Module("CS1010", 4,4, ""));
            GradeCommand command = new GradeCommand("CS1010","B+");
            command.setData(moduleList);
            moduleList.getModule("CS1010").setModuleTaken(true);
            command.execute("");
            assertEquals("B+", moduleList.getModule("CS1010").getModuleGrade());
        } catch (ModuleNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
