package longah;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TransactionList {
    private List<Transaction> transactions;

    public TransactionList() {
        this.transactions = new ArrayList<>();
    }

    public void add(Transaction transaction) {
        transactions.add(transaction);
    }

    public void remove(int index) {
        if (index >= 0 && index < transactions.size()) {
            transactions.remove(index);
        } else {
            System.out.println("Invalid index.");
        }
    }

    public Map<String, Double> calculateBalances() {
        Map<String, Double> balances = new HashMap<>();

        for (Transaction transaction : transactions) {
            String fromName = transaction.getFrom().getName();
            String toName = transaction.getTo().getName();
            double amount = transaction.getAmount();

            balances.put(fromName, balances.getOrDefault(fromName, 0.0) - amount);
            balances.put(toName, balances.getOrDefault(toName, 0.0) + amount);
        }

        return balances;
    }

    public String getOtherPerson(String name) {
        for (Transaction transaction : transactions) {
            if (transaction.getFrom().getName().equals(name)) {
                return transaction.getTo().getName();
            } else if (transaction.getTo().getName().equals(name)) {
                return transaction.getFrom().getName();
            }
        }
        return "";
    }

    public void clear() {
        transactions.clear();
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }
}
