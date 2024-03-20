package budgetbuddy.transaction;

import budgetbuddy.account.Account;
import budgetbuddy.parser.Parser;
import budgetbuddy.storage.DataStorage;
import budgetbuddy.transaction.type.Transaction;
import budgetbuddy.ui.UserInterface;

import java.io.IOException;
import java.util.ArrayList;

public class TransactionList {

    public static final int DELETE_BEGIN_INDEX = 7;
    public static final int INDEX_OFFSET = 1;
    public static final int LOWER_BOUND = 0;

    private ArrayList<Transaction> transactions;
    private Parser parser;

    private final DataStorage dataStorage = new DataStorage();

    public TransactionList() throws IOException {
        // Initialise ArrayList in the constructor
        this.transactions = dataStorage.readFileContents();
        this.parser = new Parser();
    }

    public ArrayList<Transaction> getTransactions() {
        return transactions;
    }

    public void printTransactions(Account account){
        UserInterface.printAllTransactions(transactions, account.getBalance());
    }

    public void removeTransaction(String input, Account account){
        int id = Integer.parseInt(input.substring(DELETE_BEGIN_INDEX).trim()) - INDEX_OFFSET;
        int size = transactions.size();
        if (id >= LOWER_BOUND && id < size) {
            String itemRemoved = transactions.get(id).toString();
            account.setBalance(account.getBalance() - transactions.get(id).getAmount() );
            transactions.remove(id);
            UserInterface.printDeleteMessage(itemRemoved, account.getBalance());
        } else {
            throw new IndexOutOfBoundsException(size);
        }
    }
  
    void addTransaction(Transaction t) {
        transactions.add(t);
    }

    public void processTransaction(String input, Account account) {
        Transaction t = parser.parseTransaction(input, account);
        addTransaction(t);
        String fetchData = String.valueOf(transactions.get(transactions.size() - 1));
        UserInterface.printAddMessage(fetchData, account.getBalance());
    }

    public void saveTransactionList() throws IOException {
        dataStorage.saveTransactions(transactions);
    }

    public void updateBalance(Account account) {
        account.setBalance(dataStorage.getBalance());
    }
}
