package seedu.duke.command;

import org.junit.jupiter.api.Test;
import seedu.duke.exceptions.ModuleNotFoundException;
import seedu.duke.modules.Module;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static seedu.duke.FAP.moduleList;

class RemoveCommandTest {
    @Test
    public void removeCommandTest() {

        moduleList.addModule(new Module("CS2113", 4,  2, ""));
        moduleList.addModule(new Module("CS1010", 4,  2, ""));
        Map<String, String> args = new HashMap<>();

        args.put("courseCode", "CS2113");
        RemoveCommand command1 = new RemoveCommand(args);
        command1.execute("");
        assertThrows(ModuleNotFoundException.class, () -> moduleList.getModule("CS2113"));

        args.put("courseCode", "CS1010");
        Command command2 = new RemoveCommand(args);
        command2.execute("");
        assertThrows(ModuleNotFoundException.class, () -> moduleList.getModule("CS2113"));
    }

}
