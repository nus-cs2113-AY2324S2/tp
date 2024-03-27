package seedu.binbash.parser;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.ParseException;
import seedu.binbash.command.AddCommand;
import seedu.binbash.command.Command;

import java.time.LocalDate;
import java.util.Optional;

public class AddParser extends Parser {

    public AddParser() {
        super();
        addNameOption();
        addDescriptionOption();
        addQuantityOption(false);
        addCostPriceOption();
        addSalePriceOption();
        addExpirationDateOption();
    }

    public Command parse(String[] commandArgs) throws ParseException {
        CommandLine commandLine = defaultParser.parse(options, commandArgs);

        // Required options
        String itemName = String.join(" ", commandLine.getOptionValues("name"));// Allow multiple arguments
        String itemDescription = String.join(" ", commandLine.getOptionValues("description"));
        double itemCostPrice = Double.parseDouble(commandLine.getOptionValue("cost"));

        // Optional options
        int itemQuantity = Integer.parseInt(commandLine.getOptionValue("quantity", "0"));
        double itemSalePrice = Double.parseDouble(commandLine.getOptionValue("salePrice", "0.00"));
        LocalDate itemExpirationDate = Optional.ofNullable(commandLine.getOptionValue("expiration"))
                .map(x -> LocalDate.parse(x, EXPECTED_INPUT_DATE_FORMAT))
                .orElse(LocalDate.MIN);

        return new AddCommand(itemName, itemDescription, itemQuantity, itemExpirationDate, itemSalePrice,
                itemCostPrice);
    }
}
