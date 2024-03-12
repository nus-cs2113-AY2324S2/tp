package seedu.budgetbuddy;

public abstract class Transaction {
    String category;
    int amount;
    String description;

    public Transaction(String category, int amount) {
        this.category = category;
        this.amount = amount;
    }

    public String getCategory() {
        return category;
    }

    public double getAmount() {
        return amount;
    }
}
