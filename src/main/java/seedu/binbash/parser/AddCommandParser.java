package seedu.binbash.parser;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import seedu.binbash.command.AddCommand;

import java.time.format.DateTimeFormatter;
import java.time.LocalDate;
import java.util.Optional;

public class AddCommandParser extends DefaultParser {
    protected static final DateTimeFormatter EXPECTED_INPUT_DATE_FORMAT = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    public AddCommandParser() {
        options = new Options();
        new CommandOptionAdder(options)
            .addNameOption(true, "Easily recognizable item name.")
            .addDescriptionOption(true, "A brief description of the item.")
            .addQuantityOption(false, "The units of item to be added.")
            .addCostPriceOption(true, "The cost of the item.")
            .addSalePriceOption(false, "How much you'll sell the item for.")
            .addExpirationDateOption(false, "If the item has an expiration date, specify it here.");
    }

    public AddCommand parse(String[] commandArgs) throws ParseException {
        CommandLine commandLine = super.parse(options, commandArgs);

        // Required options
        String itemName = String.join(" ", commandLine.getOptionValues("name"));// Allow multiple arguments
        String itemDescription = String.join(" ", commandLine.getOptionValues("description"));
        double itemCostPrice = Double.parseDouble(commandLine.getOptionValue("cost"));

        // Optional options
        int itemQuantity = Integer.parseInt(commandLine.getOptionValue("quantity", "0"));
        // catch NumberFormatExcpetion here
        double itemSalePrice = Double.parseDouble(commandLine.getOptionValue("salePrice", "0.00"));
        LocalDate itemExpirationDate = Optional.ofNullable(commandLine.getOptionValue("expiration"))
                .map(x -> LocalDate.parse(x, EXPECTED_INPUT_DATE_FORMAT))
                .orElse(LocalDate.MIN);

        return new AddCommand(itemName, itemDescription, itemQuantity, itemExpirationDate, itemSalePrice,
                itemCostPrice);
    }
}
