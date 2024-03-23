package seedu.stockpal.data;

import java.util.List;
import java.util.ArrayList;

public class TransactionList {
    public List<Transaction> transactions = new ArrayList<>();

    public void addTransaction(Transaction toAdd) {
        transactions.add(toAdd);
    }

}
