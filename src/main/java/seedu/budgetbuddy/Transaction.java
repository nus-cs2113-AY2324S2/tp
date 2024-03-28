package seedu.budgetbuddy;

import java.util.Currency;

public abstract class Transaction {
    String category;
    double amount;
    Currency currency;

    public Transaction(String category, double amount) {
        this.category = category;
        this.amount = amount;
        this.currency = Currency.getInstance("SGD");

    }

    public String getCategory() {
        return category;
    }

    // Getters and setters
    public double getAmount() {
        return amount;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setAmount(double amount){
        this.amount = amount;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }
}
