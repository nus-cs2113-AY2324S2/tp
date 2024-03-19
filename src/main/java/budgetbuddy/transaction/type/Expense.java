package budgetbuddy.transaction.type;

public class Expense extends Transaction {

    public Expense(String description, float amount, String category, String date) {
        super(description, amount, category, date);
    }
}
