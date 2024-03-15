package longah.handler;

import java.io.File;

import longah.exception.LongAhException;
import longah.exception.ExceptionMessage;

public class StorageHandler {
    // Storage Directory Constants
    private File membersFile;
    private File transactionsFile;
    private String storageFolderPath = "./data";
    private String storageMembersFilePath;
    private String storageTransactionsFilePath;

    // ASCII Defined Separator
    private static final String SEPARATOR = String.valueOf(Character.toChars(31));

    /**
     * Constructs a new StorageHandler instance.
     * Each instance handles the data storage requirements of each group of members.
     * 
     * @throws LongAhException
     */
    public StorageHandler() throws LongAhException {
        // Create file directory if it does not exist
        if(!new File(storageFolderPath).exists()) {
            new File(storageFolderPath).mkdir();
        }

        // Create data files if they do not exist
        storageMembersFilePath = storageFolderPath + "/members.txt";
        storageTransactionsFilePath = storageFolderPath + "/transactions.txt";
        try {
            if(!new File(storageMembersFilePath).exists()) {
                new File(storageMembersFilePath).createNewFile();
            }
            if(!new File(storageTransactionsFilePath).exists()) {
                new File(storageTransactionsFilePath).createNewFile();
            }
        } catch (Exception e) {
            throw new LongAhException(ExceptionMessage.STORAGE_FILE_NOT_CREATED);
        }
    }
}
