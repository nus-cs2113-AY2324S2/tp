package brokeculator.storage.parsing;

import brokeculator.command.AddExpenseFromFileCommand;
import brokeculator.command.Command;
import brokeculator.command.InvalidCommand;
import brokeculator.command.AddCategoryFromFileCommand;

public class GeneralFileParser {
    public static Command getCommandFromFileInput(String fileString) {

        SaveableType saveableType = FileKeyword.getSaveableType(fileString);
        if (saveableType == null) {
            return new InvalidCommand("Saved file isn't recognized. Please check the file format.");
        }
        String fileStringWithoutKeyword = FileKeyword.removeKeyword(fileString);
        switch (saveableType) {
        case EXPENSE:
            return new AddExpenseFromFileCommand(fileStringWithoutKeyword);
        case CATEGORY:
            return new AddCategoryFromFileCommand(fileStringWithoutKeyword);
        default:
            return new InvalidCommand("Saved file isn't recognized. Please check the file format.");
        }
    }
}
