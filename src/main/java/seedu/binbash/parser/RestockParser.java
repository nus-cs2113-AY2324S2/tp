package seedu.binbash.parser;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.ParseException;
import seedu.binbash.command.Command;
import seedu.binbash.command.RestockCommand;

public class RestockParser extends Parser {
    public RestockParser() {
        super();
        addNameOption();
        addQuantityOption(true);
    }

    public Command parse(String[] commandArgs) throws ParseException {
        CommandLine commandLine = defaultParser.parse(options, commandArgs);

        String itemName = String.join(" ", commandLine.getOptionValues("name"));// Allow multiple arguments
        int restockQuantity = Integer.parseInt(commandLine.getOptionValue("quantity", "0"));

        return new RestockCommand(itemName, restockQuantity);
    }
}
