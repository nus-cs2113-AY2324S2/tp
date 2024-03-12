package longah.util;

import java.util.ArrayList;
import java.util.HashMap;

import longah.node.Transaction;
import longah.exception.LongAhException;
import longah.exception.ExceptionMessage;

/**
 * Represents a list of transactions.
 */
public class TransactionList {
    private ArrayList<Transaction> transactions = new ArrayList<>();

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
     * @throws LongAhException If the index is invalid.
     */
    public void remove(int index) throws LongAhException {
        try {
            transactions.remove(index);
        } catch (IndexOutOfBoundsException e) {
            throw new LongAhException(ExceptionMessage.INVALID_INDEX);
        }
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
    public ArrayList<Transaction> getTransactions() {
        return transactions;
    }
}
