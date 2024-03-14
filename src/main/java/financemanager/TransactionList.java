package financemanager;

import parser.Parser;
import transactions.Transaction;
import ui.UserInterface;

import java.util.ArrayList;
import ui.UserInterface;

public class TransactionList {

    public static final int DELETE_BEGIN_INDEX = 7;
    public static final int INDEX_OFFSET = 1;
    public static final int LOWER_BOUND = 0;

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

    public void printTransactions(){
        UserInterface.printAllTransactions(transactions);
    }

    public void removeTransaction(String input){
        int id = Integer.parseInt(input.substring(DELETE_BEGIN_INDEX).trim()) - INDEX_OFFSET;
        int size = transactions.size();
        if (id >= LOWER_BOUND && id < size) {
            String itemRemoved = transactions.get(id).toString();
            transactions.remove(id);
            UserInterface.printDeleteMessage(itemRemoved);
        } else {
            throw new IndexOutOfBoundsException(size);
        }
    }
  
    private void addTransaction(Transaction t) {
        transactions.add(t);
    }

    public void processTransaction(String input) {
        addTransaction(parser.parseTransaction(input));
    }
}
