package seedu.binbash.parser;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.ParseException;
import seedu.binbash.command.Command;
import seedu.binbash.command.SellCommand;

public class SellParser extends Parser {
    public SellParser() {
        super();
        addNameOption();
        addQuantityOption(true);
    }

    public Command parse(String[] commandArgs) throws ParseException {
        CommandLine commandLine = defaultParser.parse(options, commandArgs);

        String itemName = String.join(" ", commandLine.getOptionValues("name"));// Allow multiple arguments
        int sellQuantity = Integer.parseInt(commandLine.getOptionValue("quantity", "0"));

        return new SellCommand(itemName, sellQuantity);
    }
}
