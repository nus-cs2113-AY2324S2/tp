package seedu.budgetbuddy;

import java.time.LocalDate;

public class Expense extends Transaction {

    private LocalDate dateAdded;
    public Expense(String category, int amount, String description) {
        super(category, amount, description);
        this.dateAdded = LocalDate.now();
    }

    public LocalDate getDateAdded() {
        return dateAdded;
    }
}
