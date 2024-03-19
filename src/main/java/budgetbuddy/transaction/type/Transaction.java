package budgetbuddy.transaction.type;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public abstract class Transaction {
    private String description;
    private double amount;
    private String category;
    private LocalDate date;

    public Transaction(String description, double amount, String category,String date) {
        this.description = description;
        this.amount = amount;
        this.category = category;
        this.date = parseDate(date);
    }

    public String getDescription() {
        return description;
    }

    public double getAmount() {
        return amount;
    }

    public LocalDate getDate() {
        return date;
    }

    private LocalDate parseDate(String by) {
        return LocalDate.parse(by, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
    }
  
    public String getCategory() {
        return category;
    }

    public abstract String getTransactionType();

    @Override
    public String toString() {
        return  (" Transaction Type: " + getTransactionType() + " | " +
                " Description: " + getDescription() + " | " +
                " Date: " + getDate() + " | " +
                " Amount: " + getAmount() + " | " +
                " Category: " + getCategory()) ;
    }
}
