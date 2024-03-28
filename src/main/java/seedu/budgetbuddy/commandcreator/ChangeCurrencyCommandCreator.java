package seedu.budgetbuddy.commandcreator;

import seedu.budgetbuddy.CurrencyConverter;
import seedu.budgetbuddy.ExpenseList;
import seedu.budgetbuddy.SavingList;
import seedu.budgetbuddy.command.ChangeCurrencyCommand;
import seedu.budgetbuddy.command.Command;

import java.util.Currency;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ChangeCurrencyCommandCreator extends CommandCreator {

    private static final Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    private ExpenseList expenses;
    private SavingList savings;
    private String input;
    private CurrencyConverter newCurrency;

    public ChangeCurrencyCommandCreator(String input, SavingList savings, ExpenseList expenses,
                                        CurrencyConverter newCurrency) {

        this.input = input;
        this.savings = savings;
        this.expenses = expenses;
        this.newCurrency = newCurrency;

    }
    public Command handleChangeCurrencyCommand(String input, SavingList savingList, ExpenseList expenseList,
                                               CurrencyConverter currencyConverter) {
        if (input.startsWith("change currency")) {
            String[] parts = input.split(" ");
            assert parts.length > 1 : "Input should contain currency code";

            if (parts.length == 3) {
                String currencyCode = parts[2];
                assert !currencyCode.isEmpty() : "Currency code should not be empty";

                try {
                    Currency newCurrency = Currency.getInstance(currencyCode.toUpperCase());
                    assert newCurrency != null : "Currency code should be valid";
                    LOGGER.log(Level.INFO, "Default currency changed to " + newCurrency);
                    System.out.println("Default currency changed to " + newCurrency);
                    return new ChangeCurrencyCommand(newCurrency, savingList, expenseList, currencyConverter);
                } catch (IllegalArgumentException e) {
                    LOGGER.log(Level.WARNING, "Invalid currency code: " + currencyCode);
                    System.out.println("Invalid currency code.");
                    return null;
                }
            } else {
                LOGGER.log(Level.WARNING, "Invalid command format. Use 'change currency <currency_code>'.");
                System.out.println("Invalid command format. Use 'change currency <currency_code>'.");
                return null;
            }
        }
        return null;
    }
    @Override
    public Command createCommand() {
        return handleChangeCurrencyCommand(input, savings, expenses, newCurrency);
    }
}
