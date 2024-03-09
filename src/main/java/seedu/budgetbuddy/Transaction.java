package seedu.budgetbuddy;

public class Transaction {
    protected String category;
    protected int amount;
    protected String description;

    public Transaction(String category, int amount, String description) {
        this.category = category;
        this.amount = amount;
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public int getAmount() {
        return amount;
    }

    public String getDescription() {
        return description;
    }

    // Getters and setters
}
