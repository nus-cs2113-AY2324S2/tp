package longah;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Represents a list of transactions.
 */
public class TransactionList {
    private List<Transaction> transactions;

    /**
     * Constructs a new TransactionList instance.
     */
    public TransactionList() {
        this.transactions = new ArrayList<>();
    }

    /**
     * Adds a transaction to the list.
     *
     * @param transaction The transaction to add.
     */
    public void add(Transaction transaction) {
        transactions.add(transaction);
    }

    /**
     * Removes a transaction from the list by index.
     *
     * @param index The index of the transaction to remove.
     */
    public void remove(int index) {
        if (index >= 0 && index < transactions.size()) {
            transactions.remove(index);
        } else {
            System.out.println("Invalid index.");
        }
    }

    /**
     * Calculates the balances between members.
     *
     * @return A map containing the balances between members.
     */
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

    /**
     * Gets the name of the other person involved in a transaction with the given name.
     *
     * @param name The name of the person.
     * @return The name of the other person in the transaction.
     */
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

    /**
     * Clears all transactions from the list.
     */
    public void clear() {
        transactions.clear();
    }

    /**
     * Gets the list of transactions.
     *
     * @return The list of transactions.
     */
    public List<Transaction> getTransactions() {
        return transactions;
    }
}
