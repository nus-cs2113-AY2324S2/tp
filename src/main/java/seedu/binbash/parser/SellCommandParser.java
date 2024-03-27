package seedu.binbash.parser;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import seedu.binbash.command.SellCommand;

public class SellCommandParser extends DefaultParser {
    public SellCommandParser() {
        options = new Options();
        new CommandOptionAdder(options)
            .addNameOption(true, "Name of item sold.")
            .addQuantityOption(true, "Units of item sold.");
    }

    public SellCommand parse(String[] commandArgs) throws ParseException {
        CommandLine commandLine = super.parse(options, commandArgs);

        String itemName = String.join(" ", commandLine.getOptionValues("name"));// Allow multiple arguments
        int sellQuantity = Integer.parseInt(commandLine.getOptionValue("quantity", "0"));

        return new SellCommand(itemName, sellQuantity);
    }
}
