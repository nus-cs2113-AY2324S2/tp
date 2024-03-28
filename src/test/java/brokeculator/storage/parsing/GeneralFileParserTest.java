package brokeculator.storage.parsing;
import brokeculator.command.Command;
import brokeculator.command.AddExpenseFromFileCommand;
import brokeculator.command.InvalidCommand;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class GeneralFileParserTest {
    @Test
    void getCommandFromFileInput_fileStringWithExpenseCommand_addExpenseFromFileCommand() {
        String originalStringRepresentation = "expense_details";
        String formattedString = FileKeyword.formatWithKeyword(SaveableType.EXPENSE, originalStringRepresentation);
        Command command = GeneralFileParser.getCommandFromFileInput(formattedString);
        assertTrue(command instanceof AddExpenseFromFileCommand);
    }

    @Test
    void getCommandFromFileInput_fileStringWithInvalidCommand_invalidCommand() {
        String fileString = "invalid_details";
        Command command = GeneralFileParser.getCommandFromFileInput(fileString);
        assertTrue(command instanceof InvalidCommand);
    }
}
