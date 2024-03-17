package longah.handler;

import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.IOException;

import longah.node.Member;
import longah.util.MemberList;
import longah.util.Subtransaction;
// import longah.node.Transaction;
import longah.util.TransactionList;
import longah.exception.LongAhException;
import longah.exception.ExceptionMessage;

/*
 * Storage Format
 * -----------
 * Members:
 * [Name]SEP[Balance]
 * 
 * Transactions:
 * [Lender]SEP[Description]SEP[Borrower1]SEP[Value]SEP...
 */
public class StorageHandler {
    // ASCII Defined Separator
    private static final String SEPARATOR = String.valueOf(Character.toChars(31));

    // Storage Directory Constants
    private File membersFile;
    private File transactionsFile;
    private String storageFolderPath = "./data";
    private String storageMembersFilePath;
    private String storageTransactionsFilePath;

    /**
     * Initializes a new StorageHandler instance.
     * Each instance handles the data storage requirements of each group of members.
     * 
     * @throws LongAhException If the data files are not created
     */
    public StorageHandler(MemberList members, TransactionList transactions)
            throws LongAhException {
        // Create file directory if it does not exist
        if(!new File(this.storageFolderPath).exists()) {
            new File(this.storageFolderPath).mkdir();
        }

        // Create data files if they do not exist
        this.storageMembersFilePath = this.storageFolderPath + "/members.txt";
        this.storageTransactionsFilePath = this.storageFolderPath + "/transactions.txt";
        this.membersFile = new File(this.storageMembersFilePath);
        this.transactionsFile = new File(this.storageTransactionsFilePath);
        try {
            membersFile.createNewFile();
            transactionsFile.createNewFile();
        } catch (IOException e) {
            throw new LongAhException(ExceptionMessage.STORAGE_FILE_NOT_CREATED);
        }

        // Load data from data files into MemberList and TransactionList objects
        loadAllData(members, transactions);
    }

    /**
     * Initializes the storage scanner to read data files.
     * 
     * @return An array of Scanners to read the data files
     * @throws LongAhException If the data files are not found
     */
    public Scanner[] initStorageScanners() throws LongAhException {
        Scanner[] scanners = new Scanner[2];
        try {
            scanners[0] = new Scanner(this.membersFile);
            scanners[1] = new Scanner(this.transactionsFile);
            return scanners;
        } catch (FileNotFoundException e) {
            throw new LongAhException(ExceptionMessage.STORAGE_FILE_NOT_FOUND);
        }
    }

    /**
     * Loads the members data from the data file into the MemberList object.
     * 
     * @param members The MemberList object to load the data into
     * @throws LongAhException If the data file is not read or the content is invalid
     */
    public void loadMembersData(Scanner sc, MemberList members)
            throws LongAhException {
        while (sc.hasNextLine()) {
            try {
                String data = sc.nextLine();
                if (data.equals("")) {
                    continue;
                }

                String[] memberData = data.split(SEPARATOR);
                String name = memberData[0];
                double balance = Double.parseDouble(memberData[1]);
                members.addMember(name, balance);
            } catch (LongAhException | NumberFormatException e) {
                throw new LongAhException(ExceptionMessage.INVALID_STORAGE_CONTENT);
            } 
        }
    }

    /**
     * Loads the transactions data from the data file into the TransactionList object.
     * 
     * @param transactions The TransactionList object to load the data into
     * @param members The MemberList object to reference the members in the transactions
     * @throws LongAhException If the data file is not read or the content is invalid
     */
    public void loadTransactionsData(Scanner sc, TransactionList transactions, MemberList members)
            throws LongAhException {
        while (sc.hasNextLine()) {
            try {
                String data = sc.nextLine();
                if (data.equals("")) {
                    continue;
                }

                String[] transactionData = data.split(SEPARATOR);
                String lenderName = transactionData[0];
                // String description = transactionData[1];
                Member lender = members.getMember(lenderName);
                ArrayList<Subtransaction> subtransactions = new ArrayList<>();

                for (int i = 2; i < transactionData.length; i += 2) {
                    Subtransaction subtransaction = parseSubtransaction(transactionData[i],
                            transactionData[i + 1], lender, members);
                    subtransactions.add(subtransaction);
                }
                // Transaction transaction = new Transaction(description, lender, subtransactions);
                // transactions.addTransaction(transaction);
            } catch (LongAhException | NumberFormatException e) {
                throw new LongAhException(ExceptionMessage.INVALID_STORAGE_CONTENT);
            }
        }
    }

    public Subtransaction parseSubtransaction(String borrowerName, String value,
            Member lender, MemberList members) throws LongAhException{
        try {
            Member borrower = members.getMember(borrowerName);
            double amount = Double.parseDouble(value);
            return new Subtransaction(lender, borrower, amount);
        } catch (NumberFormatException e) {
            throw new LongAhException(ExceptionMessage.INVALID_STORAGE_CONTENT);
        }
    }

    /**
     * Loads all data from the data files into the MemberList and TransactionList objects.
     * 
     * @param members The MemberList object to load the members data into
     * @param transactions The TransactionList object to load the transactions data into
     * @throws LongAhException If the data files are not read or the content is invalid
     */
    public void loadAllData(MemberList members, TransactionList transactions)
            throws LongAhException {
        Scanner[] scanners = initStorageScanners();
        Scanner membersScanner = scanners[0];
        Scanner transactionsScanner = scanners[1];
        loadMembersData(membersScanner, members);
        loadTransactionsData(transactionsScanner, transactions, members);
        membersScanner.close();
        transactionsScanner.close();
    }
}
