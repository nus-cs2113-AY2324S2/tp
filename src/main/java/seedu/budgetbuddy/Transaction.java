package seedu.budgetbuddy;

public abstract class Transaction {
    String category;
    double amount;

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

    public void setCategory(String category) {
        this.category = category;
    }

    public void setAmount(double amount){
        this.amount = amount;
    }

}
