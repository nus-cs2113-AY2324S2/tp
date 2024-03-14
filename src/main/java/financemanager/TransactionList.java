package financemanager;

import parser.Parser;
import transactions.Transaction;

import java.util.ArrayList;

public class TransactionList {

    private ArrayList<Transaction> transactions;
    private Parser parser;

    public TransactionList() {
        // Initialise ArrayList in the constructor
        this.transactions = new ArrayList<>();
        this.parser = new Parser();
    }

    public ArrayList<Transaction> getTransactions() {
        return transactions;
    }

    private void addTransaction(Transaction t) {
        transactions.add(t);
    }

    public void processTransaction(String input) {
        addTransaction(parser.parseTransaction(input));
    }
}
