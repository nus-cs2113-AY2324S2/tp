package budgetbuddy.transaction;

import budgetbuddy.account.Account;
import budgetbuddy.exception.EmptyArgumentException;
import budgetbuddy.parser.Parser;
import budgetbuddy.transaction.type.Transaction;
import budgetbuddy.ui.UserInterface;

import java.util.ArrayList;

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

    public void printTransactions(Account account){
        UserInterface.printAllTransactions(transactions, account.getBalance());
    }

    public void removeTransaction(String input, Account account) throws EmptyArgumentException {
        if (input.trim().length() < DELETE_BEGIN_INDEX) {
            throw new EmptyArgumentException("Index is not specified.");
        }
        String data = input.substring(DELETE_BEGIN_INDEX).trim();
        if (!isInteger(data)) {
            throw new NumberFormatException(data);
        }
        int id = Integer.parseInt(data) - INDEX_OFFSET;
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

    // Checks whether the input index is an Integer
    public static boolean isInteger(String data) {
        try {
            Integer.parseInt(data);
            return true;
        } catch (NumberFormatException e) {
            return false;
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
}
