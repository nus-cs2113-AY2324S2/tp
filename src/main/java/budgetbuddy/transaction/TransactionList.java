package budgetbuddy.transaction;

import budgetbuddy.account.Account;
import budgetbuddy.exceptions.EmptyArgumentException;
import budgetbuddy.exceptions.InvalidAddTransactionSyntax;
import budgetbuddy.exceptions.InvalidIndexException;
import budgetbuddy.exceptions.InvalidTransactionTypeException;
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
        assert transactions != null : "Transaction list is null after reading from file";
        this.parser = new Parser();
    }

    public ArrayList<Transaction> getTransactions() {
        return transactions;
    }

    public void printTransactions(Account account){
        UserInterface.printAllTransactions(transactions, account.getBalance());
    }

    public void removeTransaction(String input, Account account) throws EmptyArgumentException,
            NumberFormatException, InvalidIndexException {
        if (input.trim().length() < DELETE_BEGIN_INDEX) {
            throw new EmptyArgumentException("delete index");
        }
        String data = input.substring(DELETE_BEGIN_INDEX).trim();
        if (isNotInteger(data)) {
            throw new NumberFormatException(data);
        }
        int id = Integer.parseInt(data) - INDEX_OFFSET;
        int size = transactions.size();
        if (id >= LOWER_BOUND && id < size) {
            String itemRemoved = transactions.get(id).toString();
            assert itemRemoved != null : "String representation of item to remove is null";
            account.setBalance(account.getBalance() - transactions.get(id).getAmount() );
            transactions.remove(id);
            assert transactions.size() == size - 1 : "Transaction list size did not decrease after removal";
            UserInterface.printDeleteMessage(itemRemoved, account.getBalance());
        } else {
            throw new InvalidIndexException(String.valueOf(size));
        }
    }

    public static boolean isNotInteger(String data) {
        try {
            Integer.parseInt(data);
            return false;
        } catch (NumberFormatException e) {
            return true;
        }
    }

    public static boolean isNotDouble(String data) {
        try {
            Double.parseDouble(data);
            return false;
        } catch (NumberFormatException e) {
            return true;
        }
    }
  
    void addTransaction(Transaction t) {
        transactions.add(t);
    }

    public void processTransaction(String input, Account account)
            throws InvalidTransactionTypeException, InvalidAddTransactionSyntax, EmptyArgumentException {
        // Check for syntax for add transaction
        String[] arguments = {"/t/", "/n/", "/$/", "/d/", "/c/"};
        for (String argument : arguments) {
            if (!input.contains(argument)) {
                throw new InvalidAddTransactionSyntax("Invalid add syntax.");
            }
        }

        Transaction t = parser.parseTransaction(input, account);
        assert t != null : "Parsed transaction is null";
        addTransaction(t);
        assert transactions.get(transactions.size() - 1) != null : "Added transaction is null after adding to the list";
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
