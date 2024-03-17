package seedu.duke;

public class Expenditure {
    protected String description;
    protected Float amount;
    protected String date;

    public Expenditure(String description, Float amount, String date) {
        this.description = description;
        this.amount = amount;
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public Float getAmount() {
        return amount;
    }

    public String getDate() {
        return date;
    }

    @Override
    public String toString() {
        return description + " | Cost: $" + amount + " | date: " + date;
    }
}
