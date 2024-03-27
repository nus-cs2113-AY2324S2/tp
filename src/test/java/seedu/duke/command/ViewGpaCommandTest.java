package seedu.duke.command;

import seedu.duke.modules.Module;
import seedu.duke.modules.ModuleList;

import static org.junit.jupiter.api.Assertions.*;

public class ViewGpaCommandTest {
    public void viewGpaCommandTest() {
        ModuleList testList = new ModuleList(0);
        testList.addModule(new Module("CG2111A", 4, true,2));
        testList.addModule(new Module("CG1111A", 4, true,1));


    }

}