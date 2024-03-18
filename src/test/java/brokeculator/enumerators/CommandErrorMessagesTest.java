package brokeculator.enumerators;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CommandErrorMessagesTest {
    @Test
    public void enum_exitErrorMessage_expectExitString() {
        String expectedString = "Invalid command. Please enter 'exit' to exit the program.";
        assertEquals(expectedString, CommandErrorMessages.INVALID_EXIT_COMMAND.getString());
    }
}
