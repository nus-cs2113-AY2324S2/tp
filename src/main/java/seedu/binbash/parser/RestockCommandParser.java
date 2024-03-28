package seedu.binbash.parser;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import seedu.binbash.command.RestockCommand;
import seedu.binbash.logger.BinBashLogger;
import java.util.logging.Logger;

public class RestockCommandParser extends DefaultParser {
    private static final Logger logger = Logger.getLogger(RestockCommandParser.class.getName());
    private static final BinBashLogger binBashLogger = new BinBashLogger(RestockCommandParser.class.getName());
    public RestockCommandParser() {
        options = new Options();
        new CommandOptionAdder(options)
            .addNameOption(true, "Name of item.")
            .addQuantityOption(true, "Units of item to restock.");
    }

    public RestockCommand parse(String[] commandArgs) throws ParseException {
        CommandLine commandLine = super.parse(options, commandArgs);

        String itemName = String.join(" ", commandLine.getOptionValues("name"));// Allow multiple arguments
        int restockQuantity = Integer.parseInt(commandLine.getOptionValue("quantity", "0"));

        binBashLogger.info("Parsing RestockCommand...");

        return new RestockCommand(itemName, restockQuantity);
    }
}
