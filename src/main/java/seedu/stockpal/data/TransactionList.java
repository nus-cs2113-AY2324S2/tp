package seedu.stockpal.data;

import seedu.stockpal.common.Messages;
import seedu.stockpal.ui.Ui;

import java.util.List;
import java.util.ArrayList;

import static seedu.stockpal.ui.Ui.printToScreen;

//@@author EdmundTangg
public class TransactionList {
    public List<Transaction> transactions = new ArrayList<>();

    public void addTransaction(Transaction toAdd) {
        transactions.add(toAdd);
    }


    /**
     * @param transactionList TransactionList object.
     * @param inputPid Matching Pid to search for.
     */
    public static void findTransactions(TransactionList transactionList, Integer inputPid) {
        TransactionList findList = new TransactionList();
        for (int i = 0; i < transactionList.getSize(); i ++) {
            List<Transaction> transactions = transactionList.getTransactions();
            Transaction transaction = transactions.get(i);
            Integer pid = transaction.getPid().getPid();

            if (pid.equals(inputPid)) {
                findList.addTransaction(transaction);
            }
        }

        if (findList.isEmpty()) {
            printToScreen(Messages.MESSAGE_EMPTY_TRANSACTION_LIST);
            return;
        }

        Ui.printTransactionTasks(findList);
    }

    /**
     * Obtain the size of the transaction list
     */
    public int getSize() {
        return transactions.size();
    }

    /**
     * Obtain the list of transactions from a Transaction object.
     */
    public List<Transaction> getTransactions() {
        return transactions;
    }

    /**
     * Check if the transaction list is empty.
     */
    public boolean isEmpty() {
        return transactions.isEmpty();
    }


    /**
     * Obtain each transaction object from the list.
     */
    public Transaction get(int i) {
        return transactions.get(i);
    }

}
