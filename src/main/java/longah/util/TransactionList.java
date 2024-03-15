package longah.util;

import java.util.ArrayList;

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
     * Adds a transaction to the list with the specified expression and member list.
     *
     * @param expression The expression of the transaction to add.
     * @param memberList The member list of the transaction to add.
     * @throws LongAhException If the expression is invalid.
     */
    public void add(String expression, MemberList memberList)
             throws LongAhException {
        transactions.add(new Transaction(expression, memberList));
    }

    /**
     * Returns the size of the transaction list.
     * 
     * @return The size of the transaction list.
     */
    public int getTransactionListSize() {
        return transactions.size();
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

    /**
     * Returns a String printout the list of transactions stored in the system.
     */
    public String listTransactions() {
        int index = 1;
        String outString = "";
        for (Transaction transaction : transactions) {
            outString = outString + String.format("%d.\n%s", index, transaction) + "\n";
            index ++;
        }
        return outString;
    }

    /**
     * Returns a String printout of the list of transactions which the member name is involved as the transaction owner
     * @param memberName String representation of the name of person to search for
     */
    public String findPayments(String memberName) {
        int index = 1;
        String outString = String.format("%s owns the following list of transactions.", memberName) + "\n";
        for (Transaction transaction : transactions) {
            if (transaction.isOwner(memberName)) {
                outString = outString + String.format("%d.\n%s", index, transaction) + "\n";
                index ++;
            }
        }
        return outString;
    }

    /**
     * Return a String printout the list of transactions which a person is involved as a payee
     * @param memberName String representation of the name of person to search for
     */
    public String findDebts(String memberName) {
        String outString = String.format("%s is involved as the payee in the following list of transactions."
                , memberName) + "\n";
        int index = 1;
        for (Transaction transaction : transactions) {
            if (transaction.isPayee(memberName)) {
                outString = outString + String.format("%d.\n%s", index, transaction) + "\n";
                index ++;
            }
        }
        return outString;
    }

}
