package financemanager;

import transactions.Transaction;

import java.util.ArrayList;

public class TransactionList {

    private ArrayList<Transaction> transactions;

    public TransactionList() {
        // Initialise ArrayList in the constructor
        this.transactions = new ArrayList<>();
    }

    public ArrayList<Transaction> getTransactions() {
        return transactions;
    }

}
