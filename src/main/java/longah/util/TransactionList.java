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
     * Prints out the list of transactions stored in the system.
     */
    public void listTransactions() {
        int index = 1;
        for (Transaction transaction : transactions) {
            System.out.println(String.format("%d.%s", index, transaction));
            index ++;
        }
    }


    public void findPayments(String memberName) {
        System.out.println("%s owns the following list of transactions.");
        int index = 1;
        for (Transaction transaction : transactions) {
            if (transaction.isOwner(memberName)) {
                System.out.println(String.format("%d.\n%s", index, transaction));
                index ++;
            }
        }
    }

    public void findDebts(String memberName) {
        System.out.println("%s is involved as the payee in the following list of transactions.");
        int index = 1;
        for (Transaction transaction : transactions) {
            if (transaction.isPayee(memberName)) {
                System.out.println(String.format("%d.\n%s", index, transaction));
                index ++;
            }
        }
    }

}
