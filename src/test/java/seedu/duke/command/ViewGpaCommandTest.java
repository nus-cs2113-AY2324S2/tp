package seedu.duke.command;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.duke.exceptions.ModuleNotFoundException;
import seedu.duke.modules.Module;
import seedu.duke.modules.ModuleList;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ViewGpaCommandTest {
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
    public void viewGpaCommandTest() {
        try {
            ModuleList moduleList = new ModuleList();
            ViewGpaCommand command = new ViewGpaCommand();
            command.setData(moduleList);
            command.execute("");
            assertEquals("No countable grades present to tally.", outContent.toString().trim());
            moduleList.addModule(new Module("CS1010", 4,4, ""));
            moduleList.addModule(new Module("CS1231",4, 4, ""));
            moduleList.getModule("CS1010").setModuleTaken(true);
            moduleList.getModule("CS1231").setModuleTaken(true);
            moduleList.changeModuleGrade("CS1010", "A-");
            moduleList.changeModuleGrade("CS1231", "A");
            ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
            System.setOut(new PrintStream(outputStreamCaptor));
            command.execute("");
            assertEquals("Your current GPA is: 4.75", outputStreamCaptor.toString().trim());
        } catch (ModuleNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

}
