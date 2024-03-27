package brokeculator.expense;

import java.util.logging.Logger;

import brokeculator.event.Event;

/**
 * Represents an expense in the expense tracker.
 */
public class Expense implements Saveable {
    private static final Logger logger = Logger.getLogger(Expense.class.getName());
    private static final String FILE_DELIMITER = "EXPENSE_DELIMITER";
    private final String description;
    private final String date;
    private final double amount;
    private final String category;
    private Event owningEvent;

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
        this.category = category == null ? null : category.trim();
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

    public void placeInEvent(Event event) throws Exception{
        if (this.owningEvent != null) {
            throw new Exception("Expense is already in an event.");
        }
        this.owningEvent = event;
    }

    public void removeFromEvent() {
        if (this.owningEvent == null) {
            return;
        }
        this.owningEvent.removeExpense(this);
    }

    /**
     * Returns a string representation of the expense.
     * @return a string representation of the expense.
     */
    public String getStringRepresentation() {
        String stringRepresentation = description
                + FILE_DELIMITER + date
                + FILE_DELIMITER + amount;
        if (this.category != null) {
            stringRepresentation += (FILE_DELIMITER + this.category.toUpperCase());
        }
        return stringRepresentation;
    }

    public static Expense getExpenseFromFile(String stringRepresentation) throws Exception {
        String[] split = stringRepresentation.split(FILE_DELIMITER);
        if (split.length != 3 && split.length != 4) {
            logger.warning("Expense file is corrupted.");
            throw new Exception("Expense file is corrupted.");
        }
        
        String description = split[0];

        String date = split[1];

        String amountString = split[2];
        double amount = Double.parseDouble(amountString);

        String category = null;
        if (split.length == 4) {
            category = split[3];
        }

        return new Expense(description, amount, date, category);
    }

    @Override
    public String toString() {
        if (category == null) {
            return String.format("%s $%.2f (%s)", description, amount, date);
        }
        return String.format("%s $%.2f (%s) [%s]", description, amount, date, category.toUpperCase());
    }
}
