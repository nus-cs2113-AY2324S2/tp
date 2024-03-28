package budgetbuddy.storage;

import budgetbuddy.account.Account;
import budgetbuddy.categories.Category;
import budgetbuddy.transaction.type.Expense;
import budgetbuddy.transaction.type.Income;
import budgetbuddy.transaction.type.Transaction;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class DataStorage {
    public static final String STORAGE_FILE_PATH = "./data/data.txt";
    public static final String FOLDER_PATH = "./data";

    private double balance;

    public void saveTransactions(ArrayList<Transaction> transactionArrayList) throws IOException {
        File f = new File(STORAGE_FILE_PATH);

        assert f.exists() : "File does not exist";

        FileWriter fw = new FileWriter(STORAGE_FILE_PATH);
        for (Transaction transaction : transactionArrayList) {
            if (transaction == null) {
                break;
            }
            String stringToWrite = getStringToWrite(transaction);
            writeToFile(stringToWrite);
        }
    }

    private static void writeToFile(String stringToWrite) throws IOException {
        FileWriter fw = new FileWriter(STORAGE_FILE_PATH, true);
        fw.write(stringToWrite);
        fw.close();
    }

    private static String getStringToWrite(Transaction t) {
        LocalDate date = t.getDate();
        String stringDate = date.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        return t.getDescription() + " ," + t.getCategory().getCategoryNum() + " ,"
                + t.getTransactionType() + " ," + stringDate + " ," + t.getAmount() + "\n";
    }

    private Transaction processData(String s, Account account) {
        String[] transactionInfo = s.split(" ,");
        int categoryNum = Integer.parseInt(transactionInfo[1]);

        assert transactionInfo.length == 5 : "Invalid transaction information format";
        assert transactionInfo[2].equals("Income") || transactionInfo[2].equals("Expense") : "Invalid transaction type";

        switch (transactionInfo[2]) {
        case "Income":
            Income incomeObj = new Income(transactionInfo[0], Double.parseDouble(transactionInfo[4]),
                    transactionInfo[3], account);
            incomeObj.setCategory(Category.fromNumber(categoryNum));
            return incomeObj;
        case "Expense":
            Expense expenseObj = new Expense(transactionInfo[0], -Double.parseDouble(transactionInfo[4]),
                    transactionInfo[3], account);
            expenseObj.setCategory(Category.fromNumber(categoryNum));
            return expenseObj;
        default:
            return null;
        }
    }

    private static void createDataFolderIfNotExists() throws IOException {
        Path dataFolderPath = Paths.get(FOLDER_PATH);
        if (!Files.exists(dataFolderPath)) {
            Files.createDirectories(dataFolderPath);
        }
    }

    public ArrayList<Transaction> readFileContents() throws IOException {
        createDataFolderIfNotExists();
        File f = new File(STORAGE_FILE_PATH);
        if (!f.exists()) {
            f.createNewFile();
        }

        assert f.exists() : "File does not exist";

        Scanner s = new Scanner(f);
        ArrayList<Transaction> transactionList = new ArrayList<>();
        Account account = new Account();
        for (int i = 0; s.hasNext(); i++) {
            transactionList.add(processData(s.nextLine(), account));
        }
        balance = account.getBalance();
        return transactionList;
    }

    public double getBalance() {
        return balance;
    }
}
