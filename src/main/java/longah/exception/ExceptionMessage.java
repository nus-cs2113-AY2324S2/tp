package longah.exception;

public enum ExceptionMessage {
    // [Cause of Exception]([Message to be printed])
    // General Exceptions
    INVALID_INDEX ("Invalid index."),

    // Member Exceptions
    DUPLICATE_MEMBER ("Duplicate member."),
    INVALID_MEMBER_NAME ("Invalid member name."),
    MEMBER_NOT_FOUND ("Member not found."),
    NO_MEMBERS_FOUND ("Member list is empty."),

    // Transaction Exceptions
    INVALID_TRANSACTION_FORMAT ("Invalid transaction format."),
    INVALID_TRANSACTION_VALUE ("Invalid transaction value."),
    INVALID_VALUE_FORMAT ("Invalid value format."),
    NO_TRANSACTION_FOUND ("Transaction list is empty."),
    NO_DEBTS_FOUND ("No debts found."),
    TRANSACTIONS_SUMMED_UP ("No pending payments."),

    // Data Storage Exceptions
    STORAGE_FILE_NOT_FOUND ("File not found."),
    STORAGE_FILE_NOT_CREATED ("File not created."),
    STORAGE_FILE_NOT_READ ("File not read."),
    STORAGE_FILE_NOT_WRITTEN ("File not written."),
    INVALID_STORAGE_CONTENT ("Invalid content in storage file, line ignored."),
    STORAGE_FILE_CORRUPTED ("Storage file is corrupted."),

    // Ui exceptions
    INVALID_COMMAND ("Invalid command. Use 'help' to see the list of commands."),
    COMMAND_NOT_IMPLEMENTED ("This feature has yet to be implemented."),
    INVALID_ADD_COMMAND ("Invalid command format." +
            " Use 'add member NAME' or 'add transaction LENDER p/BORRWER1 a/AMOUNT1 ..."),
    INVALID_LIST_COMMAND ("Invalid command format." +
            " Use 'list members', 'list transactions', or 'list debts'"),
    INVALID_FIND_COMMAND ("Invalid command format." +
            " Use 'find transactions NAME' or 'find debts NAME'"),
    INVALID_SETTLEUP_COMMAND ("Invalid command format." +
            " Use 'settleUp PERSON'"),
    INVALID_DELETE_COMMAND ("Invalid command format." +
            " Use 'delete transaction INDEX'"),
    INVALID_CLEAR_COMMAND ("Invalid command format." +
            " Use 'clear'"),
    INVALID_EXIT_COMMAND ("Invalid command format." +
            " Use 'exit'");

    private final String message;

    /**
     * Constructor for ExceptionMessage.
     * 
     * @param message The message to be printed when the exception is called.
     */
    ExceptionMessage(String message) {
        this.message = message;
    }

    /**
     * Returns the message of the exception.
     * 
     * @return The message of the exception.
     */
    public String getMessage() {
        return message;
    }
}
