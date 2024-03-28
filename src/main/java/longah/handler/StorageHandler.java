package longah.handler;

import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;

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
 * [Lender]SEP[Borrower1]SEP[Value]SEP...
 */
public class StorageHandler {
    // ASCII Defined Separator
    private static final String SEPARATOR = String.valueOf(Character.toChars(31));

    // Storage Directory Constants
    private String storageFolderPath = "./data";
    private String storageMembersFilePath;
    private String storageTransactionsFilePath;
    private File membersFile;
    private File transactionsFile;

    // Objects for Storate
    private MemberList members;
    private TransactionList transactions;
    private Scanner[] scanners = new Scanner[2];

    /**
     * Initializes a new StorageHandler instance.
     * Each instance handles the data storage requirements of each group of members.
     * 
     * @throws LongAhException If the data files are not created
     */
    public StorageHandler(MemberList members, TransactionList transactions, String groupName)
            throws LongAhException {
        // Create data directory if it does not exist
        initDir();
        this.storageFolderPath += "/" + groupName;
        // Create group directory if it does not exist
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

        this.members = members;
        this.transactions = transactions;
        initStorageScanners();

        // Load data from data files into MemberList and TransactionList objects
        loadAllData();
        Logging.logInfo("Data loaded from storage.");
    }

    /**
     * Initializes the storage scanner to read data files.
     * 
     * @throws LongAhException If the data files are not found
     */
    public void initStorageScanners() throws LongAhException {
        try {
            this.scanners[0] = new Scanner(this.membersFile);
            this.scanners[1] = new Scanner(this.transactionsFile);
        } catch (FileNotFoundException e) {
            throw new LongAhException(ExceptionMessage.STORAGE_FILE_NOT_FOUND);
        }
    }

    public static void initDir() {
        File f = new File("./data");
        if (!f.exists()) {
            f.mkdir();
        }
    }

    /**
     * Loads the members data from the data file into the MemberList object.
     * 
     * @throws LongAhException If the data file is not read or the content is invalid
     */
    public void loadMembersData() throws LongAhException {
        Scanner sc = this.scanners[0];
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
                this.members.addMember(name, balance);
            } catch (LongAhException | NumberFormatException e) {
                throw new LongAhException(ExceptionMessage.INVALID_STORAGE_CONTENT);
            } 
        }
    }

    /**
     * Loads the transactions data from the data file into the TransactionList object.
     * 
     * @throws LongAhException If the data file is not read or the content is invalid
     */
    public void loadTransactionsData() throws LongAhException {
        Scanner sc = this.scanners[1];
        while (sc.hasNextLine()) {
            try {
                String data = sc.nextLine();
                if (data.equals("")) {
                    continue;
                }

                String[] transactionData = data.split(SEPARATOR);
                String lenderName = transactionData[0];
                Member lender = members.getMember(lenderName);
                ArrayList<Subtransaction> subtransactions = new ArrayList<>();

                for (int i = 1; i < transactionData.length; i += 2) {
                    Subtransaction subtransaction = parseSubtransaction(transactionData[i],
                            transactionData[i + 1], lender, members);
                    subtransactions.add(subtransaction);
                }

                Transaction transaction = new Transaction(lender, subtransactions, members);
                this.transactions.addTransaction(transaction);

            } catch (LongAhException | NumberFormatException e) {
                throw new LongAhException(ExceptionMessage.INVALID_STORAGE_CONTENT);
            }
        }
        boolean checksum = checkTransactions(members);
        if (!checksum) {
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
    public static Subtransaction parseSubtransaction(String borrowerName, String value,
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
     * Returns if the total balance of all members in the MemberList object is 0.
     * 
     * @param members The MemberList object to check the total balance from
     * @return If the total balance is 0, return true. Otherwise, return false.
     */
    public boolean checkTransactions(MemberList members) {
        if (members.getMemberListSize() == 0) {
            return true;
        }
        double total = 0;
        for (Member member : members.getMembers()) {
            total += member.getBalance();
        }
        if (total == 0) {
            return true;
        }
        return false;
    }

    /**
     * Loads all data from the data files into the MemberList and TransactionList objects.
     * 
     * @throws LongAhException If the data files are not read or the content is invalid
     */
    public void loadAllData() throws LongAhException {
        loadMembersData();
        loadTransactionsData();

        // Close the scanners after reading the data
        this.scanners[0].close();
        this.scanners[1].close();
    }

    /**
     * Saves the members data from the MemberList object into the data file.
     * 
     * @throws LongAhException If the data file is not written
     */
    public void saveMembersData() throws LongAhException {
        try {
            FileWriter fw = new FileWriter(this.membersFile);
            for (Member member : this.members.getMembers()) {
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
     * @throws LongAhException If the data file is not written
     */
    public void saveTransactionsData() throws LongAhException {
        try {
            FileWriter fw = new FileWriter(this.transactionsFile);
            for (Transaction transaction : this.transactions.getTransactions()) {
                String data = transaction.toStorageString(SEPARATOR);
                fw.write(data + "\n");
            }
            fw.close();
        } catch (IOException e) {
            throw new LongAhException(ExceptionMessage.STORAGE_FILE_NOT_WRITTEN);
        }
    }

    /**
     * Saves all data from the MemberList and TransactionList objects into the data files.
     * 
     * @throws LongAhException If the data files are not written
     */
    public void saveAllData() throws LongAhException {
        saveMembersData();
        saveTransactionsData();
    }
}
