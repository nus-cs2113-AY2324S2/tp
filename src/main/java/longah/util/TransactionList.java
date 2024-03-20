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
    public void addTransaction(Transaction transaction) {
        this.transactions.add(transaction);
    }

    /**
     * Adds a transaction to the list with the specified expression and member list.
     *
     * @param expression The expression of the transaction to add.
     * @param memberList The member list of the transaction to add.
     * @throws LongAhException If the expression is invalid.
     */
    public void addTransaction(String expression, MemberList memberList)
             throws LongAhException {
        this.transactions.add(new Transaction(expression, memberList));
    }

    /**
     * Returns the size of the transaction list.
     *
     * @return The size of the transaction list.
     */
    public int getTransactionListSize() {
        return this.transactions.size();
    }

    /**
     * Removes a transaction from the list by index.
     *
     * @param indexString The index of the transaction to remove.
     * @throws LongAhException If the index is invalid.
     */
    public void remove(String indexString) throws LongAhException {
        int index = Integer.parseInt(indexString) - 1;
        if (index < 0 || index >= this.transactions.size()) {
            throw new LongAhException(ExceptionMessage.INVALID_INDEX);
        }
        Transaction removedTransaction = this.transactions.remove(index);
        // recalculate the balances of the members
        removedTransaction.recalculateBalances();
    }

    /**
     * Clears all transactions from the list.
     * @param memberList The member list to clear balances from.
     */
    public void clear(MemberList memberList) {
        this.transactions.clear();
        memberList.clearBalances();
    }

    /**
     * Gets the list of transactions.
     *
     * @return The list of transactions.
     */
    public ArrayList<Transaction> getTransactions() {
        return this.transactions;
    }

    /**
     * Returns a String printout the list of transactions stored in the system.
     */
    public String listTransactions() throws LongAhException {
        int transactionListSize = getTransactionListSize();
        if (transactionListSize == 0) {
            throw new LongAhException(ExceptionMessage.NO_TRANSACTION_FOUND);
        }
        int index = 1;
        String outString = "";
        for (Transaction transaction : this.transactions) {
            outString = outString + String.format("%d.\n%s", index, transaction) + "\n";
            index++;
        }
        return outString;
    }

    /**
     * Printout the list of transactions which the member name is involved as the
     * transaction lender
     *
     * @param lenderName User input containing the name of person to search for
     * @return Returns a String printout of the required list of transactions
     */
    public String findLender(String lenderName) throws LongAhException {
        int index = 1;
        String outString = String.format("%s owns the following list of transactions.", lenderName) + "\n";
        for (Transaction transaction : this.transactions) {
            if (transaction.isLender(lenderName)) {
                outString = outString + String.format("%d.\n%s", index, transaction) + "\n";
                index++;
            }
        }
        if (index == 1) {
            throw new LongAhException(ExceptionMessage.TRANSACTIONS_SUMMED_UP);
        }
        return outString;
    }

    /**
     * Printout the list of transactions which the member name is involved as the
     * transaction lender
     *
     * @param lenderName User input containing the name of person to search for
     * @return Returns a String printout of the required list of transactions
     */
    public String findBorrower(String borrowerName) throws LongAhException {
        int index = 1;
        String outString = String.format("%s owns the following list of transactions.", borrowerName) + "\n";
        for (Transaction transaction : this.transactions) {
            if (transaction.isBorrower(borrowerName)) {
                outString = outString + String.format("%d.\n%s", index, transaction) + "\n";
                index++;
            }
        }
        if (index == 1) {
            throw new LongAhException(ExceptionMessage.TRANSACTIONS_SUMMED_UP);
        }
        return outString;
    }

    /**
     * Printout the list of transactions which the member name is involved as the
     * transaction lender
     *
     * @param lenderName User input containing the name of person to search for
     * @return Returns a String printout of the required list of transactions
     */
    public String findTransactions(String name) throws LongAhException {
        int index = 1;
        String outString = String.format("%s owns the following list of transactions.", name) + "\n";
        for (Transaction transaction : this.transactions) {
            if (transaction.isInvolved(name)) {
                outString = outString + String.format("%d.\n%s", index, transaction) + "\n";
                index++;
            }
        }
        if (index == 1) {
            throw new LongAhException(ExceptionMessage.TRANSACTIONS_SUMMED_UP);
        }
        return outString;
    }

    /**
     * Printout the list of transactions which a person is involved as a borrower
     *
     * @param borrowerName containing the String representation of the name of person to search for
     * @return Returns a String printout of the required list of transactions
     */
    public String findDebts(String borrowerName) throws LongAhException {
        String outString = String.format("%s is involved as the payee in the following list of transactions."
                , borrowerName) + "\n";
        int index = 1;
        for (Transaction transaction : this.transactions) {
            if (transaction.isBorrower(borrowerName)) {
                outString = outString + String.format("%d.\n%s", index, transaction) + "\n";
                index++;
            }
        }
        if (index == 1) {
            throw new LongAhException(ExceptionMessage.TRANSACTIONS_SUMMED_UP);
        }
        return outString;
    }
}
