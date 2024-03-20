package longah.handler;

import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;

import java.util.logging.*;

import longah.node.Member;
import longah.util.MemberList;
import longah.util.Subtransaction;
import longah.node.Transaction;
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

    private static Logger logger = Logger.getLogger("Storage Logger");

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
        logger.log(Level.INFO, "Data loaded from storage.");
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
                assert memberData.length == 2 : "Member data should have 2 parts.";

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

                for (int i = 1; i < transactionData.length; i += 2) {
                    Subtransaction subtransaction = parseSubtransaction(transactionData[i],
                            transactionData[i + 1], lender, members);
                    subtransactions.add(subtransaction);
                }

                Transaction transaction = new Transaction(lender, subtransactions, members);
                transactions.addTransaction(transaction);

            } catch (LongAhException | NumberFormatException e) {
                throw new LongAhException(ExceptionMessage.INVALID_STORAGE_CONTENT);
            }
        }
        double total = checkTransactions(members);
        if (total != 0) {
            throw new LongAhException(ExceptionMessage.STORAGE_FILE_CORRUPTED);
        }
    }

    /**
     * Parses the subtransaction data from the data file into a Subtransaction object.
     * 
     * @param borrowerName The name of the borrower in the subtransaction
     * @param value The amount borrowed in the subtransaction
     * @param lender The lender in the subtransaction
     * @param members The MemberList object to reference the members in the subtransaction
     * @return The Subtransaction object parsed from the data file
     * @throws LongAhException If the data file is not read or the content is invalid
     */
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
     * Checks the total balance of all members in the MemberList object.
     * 
     * @param members The MemberList object to check the total balance from
     * @return The total balance of all members
     */
    public double checkTransactions(MemberList members) {
        if (members.getMemberListSize() == 0) {
            return 0;
        }
        double total = 0;
        for (Member member : members.getMembers()) {
            total += member.getBalance();
        }
        return total;
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

    /**
     * Saves the members data from the MemberList object into the data file.
     * 
     * @param members The MemberList object to save the data from
     * @throws LongAhException If the data file is not written
     */
    public void saveMembersData(MemberList members) throws LongAhException {
        try {
            FileWriter fw = new FileWriter(this.membersFile);
            for (Member member : members.getMembers()) {
                String data = member.toStorageString(SEPARATOR);
                fw.write(data + "\n");
            }
            fw.close();
        } catch (IOException e) {
            throw new LongAhException(ExceptionMessage.STORAGE_FILE_NOT_WRITTEN);
        }
    }

    /**
     * Saves the transactions data from the TransactionList object into the data file.
     * 
     * @param transactions The TransactionList object to save the data from
     * @throws LongAhException If the data file is not written
     */
    public void saveTransactionsData(TransactionList transactions) throws LongAhException {
        try {
            FileWriter fw = new FileWriter(this.transactionsFile);
            for (Transaction transaction : transactions.getTransactions()) {
                String data = transaction.toStorageString(SEPARATOR);
                fw.write(data + "\n");
            }
            fw.close();
        } catch (IOException e) {
            throw new LongAhException(ExceptionMessage.STORAGE_FILE_NOT_WRITTEN);
        }
    }
}
