package brokeculator.storage.parsing;

import brokeculator.command.AddExpenseFromFileCommand;
import brokeculator.command.Command;
import brokeculator.command.InvalidCommand;

public class GeneralFileParser {
    public static Command getCommandFromFileInput(String fileString) {
        String firstWord = (fileString.split(" "))[0];
        switch (firstWord) {
        case FileKeyword.EXPENSE:
            return new AddExpenseFromFileCommand(fileString);
        default:
            return new InvalidCommand();
        }
    }
}
