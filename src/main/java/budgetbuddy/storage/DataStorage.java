package budgetbuddy.storage;

import budgetbuddy.account.Account;
import budgetbuddy.transaction.type.Expense;
import budgetbuddy.transaction.type.Income;
import budgetbuddy.transaction.type.Transaction;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class DataStorage {
    public static final String STORAGE_FILE_PATH = "./data/data.txt";

    public void saveTransactions(ArrayList<Transaction> transactionArrayList) throws IOException {
        File f = new File(STORAGE_FILE_PATH);
        FileWriter fw = new FileWriter(STORAGE_FILE_PATH);
        for (Transaction transaction : transactionArrayList) {
            if (transaction == null) break;
            String stringToWrite = getStringToWrite(transaction);
            writeToFile(stringToWrite);
        }
    }

    private void writeToFile(String stringToWrite) throws IOException {
        FileWriter fw = new FileWriter(STORAGE_FILE_PATH, true);
        fw.write(stringToWrite);
        fw.close();
    }

    private static String getStringToWrite(Transaction t) {
        return t.getDescription() + " ," + t.getCategory() + " ," + t.getTransactionType() + " ," + t.getDate()
                + " ." + t.getAmount() + "\n";
    }

    private Transaction processData(String s, Account account) {
        String[] transactionInfo = s.split(" ,");

        switch (transactionInfo[2]) {
        case "Income":
            return new Income(transactionInfo[0], Double.parseDouble(transactionInfo[4]), transactionInfo[1], transactionInfo[3], account);
        case "Expense":
            return new Expense(transactionInfo[0], Double.parseDouble(transactionInfo[4]), transactionInfo[1], transactionInfo[3], account);
        }
        return null;
    }
}
