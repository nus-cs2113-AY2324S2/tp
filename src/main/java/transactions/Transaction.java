package transactions;

public class Transaction {
    private String description;
    private float amount;
    private String category;

    public Transaction(String description, float amount, String category) {
        this.description = description;
        this.amount = amount;
        this.category = category;
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

    @Override
    public String toString() {
        return  (" Description: " + getDescription() + " | " +
                " Amount: " + getAmount() + " | " +
                " Category: " + getCategory()) ;
    }
}
