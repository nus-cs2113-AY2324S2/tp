package brokeculator.expense;

import brokeculator.storage.parsing.FileKeyword;
import brokeculator.storage.parsing.SaveableType;

import java.util.logging.Logger;

/**
 * Represents an expense in the expense tracker.
 */
public class Expense implements Saveable {
    private static final Logger logger = Logger.getLogger(Expense.class.getName());
    private final String description;
    private final String date;
    private final double amount;
    private final String category;

    /**
     * Constructs an Expense object with the given description, amount, date and category.
     *
     * @param description the description of the expense.
     * @param amount the amount of the expense.
     * @param date the date of the expense.
     * @param category the category of the expense.
     */
    public Expense(String description, double amount, String date, String category) {
        this.description = description.trim();
        this.amount = amount;
        this.date = date.trim();
        this.category = category.trim();
    }

    /**
     * Returns the description of the expense.
     * @return the description of the expense.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns the amount of the expense.
     * @return the amount of the expense.
     */
    public double getAmount() {
        return amount;
    }

    /**
     * Returns the date of the expense.
     * @return the date of the expense.
     */
    public String getDate() {
        return date;
    }

    /**
     * Returns the category of the expense.
     * @return the category of the expense.
     */
    public String getCategory() {
        return category;
    }

    /**
     * Returns a string representation of the expense.
     * @return a string representation of the expense.
     */
    public String getStringRepresentation() {
        return FileKeyword.formatWithKeyword(SaveableType.EXPENSE,
                String.format("%s $%.2f (%s) [%s]", description, amount, date, category.toUpperCase())
        );
    }

    public static Expense getExpenseFromFile(String stringRepresentation) throws Exception {
        String[] split = stringRepresentation.split(" ");
        if (split.length != 4) {
            logger.warning("Expense file is corrupted.");
            throw new Exception("Expense file is corrupted.");
        }
        
        String description = split[0];

        String amountString = split[1].substring(1);
        double amount = Double.parseDouble(amountString);

        String date = split[2].substring(1, split[2].length() - 1);

        String category = split[3].substring(1, split[3].length() - 1);

        return new Expense(description, amount, date, category);
    }

    @Override
    public String toString() {
        if (category.equalsIgnoreCase("null")) {
            return String.format("%s $%.2f (%s)", description, amount, date);
        }
        return String.format("%s $%.2f (%s) [%s]", description, amount, date, category.toUpperCase());
    }
}
