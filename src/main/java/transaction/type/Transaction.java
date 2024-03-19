package transaction.type;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Transaction {
    private String description;
    private float amount;
    private String category;
    private LocalDate date;

    public Transaction(String description, float amount, String category,String date) {
        this.description = description;
        this.amount = amount;
        this.category = category;
        this.date = parseDate(date);
    }

    public String getDescription() {
        return description;
    }

    public float getAmount() {
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
  
    @Override
    public String toString() {
        return  (" Description: " + getDescription() + " | " +
                " Date: " + getDate() + " | " +
                " Amount: " + getAmount() + " | " +
                " Category: " + getCategory()) ;
    }
}
