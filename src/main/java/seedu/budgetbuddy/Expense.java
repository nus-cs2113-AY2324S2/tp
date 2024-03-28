package seedu.budgetbuddy;

import java.time.LocalDate;

public class Expense extends Transaction{
    protected String description;
    private LocalDate dateAdded;
    
    public Expense(String category, double amount, String description) {
        super(category, amount);
        this.description = description;
        this.dateAdded = LocalDate.now();
    }
    public LocalDate getDateAdded() {
        return dateAdded;
    }

    public String getDescription(){
        return description;
    }

    public void setDescription(String description){
        this.description = description;
    }

    @Override
    public String toString() {
        return "Category: " + category + " Amount: " + amount + " Description: " + description;
    }
}
