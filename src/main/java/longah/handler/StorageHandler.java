package longah.handler;

import java.io.File;
import java.util.Scanner;

import longah.util.MemberList;
import longah.util.TransactionList;
import longah.exception.LongAhException;
import longah.exception.ExceptionMessage;

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
     * Constructs a new StorageHandler instance.
     * Each instance handles the data storage requirements of each group of members.
     * 
     * @param members The MemberList object to store the members' data
     * @param transactions The TransactionList object to store the transactions' data
     * @throws LongAhException If the data files are not created
     */
    public StorageHandler(MemberList members, TransactionList transactions)
            throws LongAhException {
        // Create file directory if it does not exist
        if(!new File(storageFolderPath).exists()) {
            new File(storageFolderPath).mkdir();
        }

        // Create data files if they do not exist
        storageMembersFilePath = storageFolderPath + "/members.txt";
        storageTransactionsFilePath = storageFolderPath + "/transactions.txt";
        membersFile = new File(storageMembersFilePath);
        transactionsFile = new File(storageTransactionsFilePath);
        try {
            membersFile.createNewFile();
            transactionsFile.createNewFile();
        } catch (Exception e) {
            throw new LongAhException(ExceptionMessage.STORAGE_FILE_NOT_CREATED);
        }

        loadData(members, transactions);
    }

    /**
     * Initializes the storage scanner for the data files.
     * 
     * @return Scanner[] Array of Scanners for the data files
     * @throws LongAhException If the data files are not found
     */
    public Scanner[] initStorageScanner() throws LongAhException {
        Scanner[] scanners = new Scanner[2];
        try {
            scanners[0] = new Scanner(membersFile);
            scanners[1] = new Scanner(transactionsFile);
        } catch (Exception e) {
            throw new LongAhException(ExceptionMessage.STORAGE_FILE_NOT_FOUND);
        }
        return scanners;
    }

    /**
     * Loads the data from the data files into the MemberList and TransactionList objects.
     * 
     * @param members The MemberList object to store the members' data
     * @param transactions The TransactionList object to store the transactions' data
     * @throws LongAhException If the data files are not found
     */
    public void loadData(MemberList members, TransactionList transactions)
            throws LongAhException {
        Scanner[] scanners = initStorageScanner();
        Scanner membersScanner = scanners[0];
        Scanner transactionsScanner = scanners[1];
        while (membersScanner.hasNextLine()) {
            try {
                String data = membersScanner.nextLine();
                if (data.equals("")) {
                    continue;
                }

                String[] memberData = data.split(SEPARATOR);
            } catch (LongAhException e) {
                throw new LongAhException(ExceptionMessage.INVALID_STORAGE_CONTENT);
            }
        }
        while (transactionsScanner.hasNextLine()) {
            try {
                String data = transactionsScanner.nextLine();
                if (data.equals("")) {
                    continue;
                }
                
                String[] transactionData = data.split(SEPARATOR);
            } catch (LongAhException e) {
                throw new LongAhException(ExceptionMessage.INVALID_STORAGE_CONTENT);
            }
        }
    }
}
