package transactions;

public class Transaction {
    private String description;
    private float amount;
    private String category;

    private String date;

    public Transaction(String description, float amount, String category, String date) {
        this.description = description;
        this.amount = amount;
        this.category = category;
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public float getAmount() {
        return amount;
    }

    public String getCategory() {
        return category;
    }

    public String getDate() {
        return date;
    }
}
