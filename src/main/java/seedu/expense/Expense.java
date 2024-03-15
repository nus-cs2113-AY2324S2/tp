package seedu.expense;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Expense implements Saveable {
    private final String description;
    private final LocalDateTime date;
    private final double amount;
    private final String category;

    public Expense(String description, double amount, LocalDateTime date, String category) {
        this.description = description;
        this.amount = amount;
        this.date = date;
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public double getAmount() {
        return amount;
    }

    public String getCategory() {
        return category;
    }

    public String getStringRepresentation() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy, hh:mm a");
        return String.format("%s: $%.2f (%s) [%s]",
                description, amount, date.format(formatter), category.toUpperCase());
    }
}
