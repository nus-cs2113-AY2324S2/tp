package budgetbuddy.transaction.type;

public class Income extends Transaction {

    public Income(String description, double amount, String category, String date) {
        super(description, amount, category, date);
    }
}
