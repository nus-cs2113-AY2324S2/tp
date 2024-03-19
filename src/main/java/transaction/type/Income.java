package transaction.type;

public class Income extends Transaction {

    public Income(String description, float amount, String category, String date) {
        super(description, amount, category, date);
    }
}
