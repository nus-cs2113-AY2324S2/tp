package seedu.expense;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an expense in the expense tracker.
 */
public class Expense implements Saveable {
    private final String description;
    private final LocalDateTime date;
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
    public Expense(String description, double amount, LocalDateTime date, String category) {
        this.description = description;
        this.amount = amount;
        this.date = date;
        this.category = category;
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
    public LocalDateTime getDate() {
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
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy, hh:mm a");
        return String.format("%s: $%.2f (%s) [%s]",
                description, amount, date.format(formatter), category.toUpperCase());
    }
}
