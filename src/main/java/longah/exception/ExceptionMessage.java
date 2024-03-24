package longah.exception;

public enum ExceptionMessage {
    // [Cause of Exception]([Message to be printed])
    // General Exceptions
    INVALID_INDEX ("Invalid index.", ExceptionType.WARNING),

    // Member Exceptions
    DUPLICATE_MEMBER ("Duplicate member.", ExceptionType.INFO),
    INVALID_MEMBER_NAME ("Invalid member name.", ExceptionType.INFO),
    MEMBER_NOT_FOUND ("Member not found.", ExceptionType.INFO),
    NO_MEMBERS_FOUND ("Member list is empty.", ExceptionType.INFO),

    // Transaction Exceptions
    INVALID_TRANSACTION_FORMAT ("Invalid transaction format.", ExceptionType.WARNING),
    INVALID_TRANSACTION_VALUE ("Invalid transaction value.", ExceptionType.WARNING),
    INVALID_VALUE_FORMAT ("Invalid value format.", ExceptionType.WARNING),
    NO_TRANSACTION_FOUND ("Transaction list is empty.", ExceptionType.INFO),
    NO_DEBTS_FOUND ("No debts found.", ExceptionType.INFO),
    TRANSACTIONS_SUMMED_UP ("No pending payments.", ExceptionType.INFO),

    // Data Storage Exceptions
    STORAGE_FILE_NOT_FOUND ("File not found.", ExceptionType.WARNING),
    STORAGE_FILE_NOT_CREATED ("File not created.", ExceptionType.WARNING),
    STORAGE_FILE_NOT_READ ("File not read.", ExceptionType.WARNING),
    STORAGE_FILE_NOT_WRITTEN ("File not written.", ExceptionType.WARNING),
    INVALID_STORAGE_CONTENT ("Invalid content in storage file, line ignored.", ExceptionType.WARNING),
    STORAGE_FILE_CORRUPTED ("Storage file is corrupted.", ExceptionType.WARNING),

    // Ui exceptions
    INVALID_COMMAND ("Invalid command. Use 'help' to see the list of commands.",
            ExceptionType.INFO),
    COMMAND_NOT_IMPLEMENTED ("This feature has yet to be implemented.",
            ExceptionType.INFO),
    INVALID_ADD_COMMAND ("Invalid command format." +
            " Use 'add member NAME' or 'add transaction LENDER p/BORRWER1 a/AMOUNT1 ...",
            ExceptionType.INFO),
    INVALID_LIST_COMMAND ("Invalid command format." +
            " Use 'list members', 'list transactions', or 'list debts'",
            ExceptionType.INFO),
    INVALID_FIND_COMMAND ("Invalid command format." +
            " Use 'find transactions NAME' or 'find debts NAME'",
            ExceptionType.INFO),
    INVALID_SETTLEUP_COMMAND ("Invalid command format." +
            " Use 'settleUp PERSON'",
            ExceptionType.INFO),
    INVALID_DELETE_COMMAND ("Invalid command format." +
            " Use 'delete transaction INDEX'",
            ExceptionType.INFO),
    INVALID_CLEAR_COMMAND ("Invalid command format." +
            " Use 'clear'",
            ExceptionType.INFO),
    INVALID_RESET_COMMAND ("Invalid command format." +
            " Use 'reset password'",
            ExceptionType.INFO),
    INVALID_EDIT_COMMAND("Invalid command format." +
            " Use 'edit transaction INDEX NEW_TRANSACTION' or 'edit member INDEX NEW_NAME'",
            ExceptionType.INFO),
    INVALID_EXIT_COMMAND ("Invalid command format." +
            " Use 'exit'",
            ExceptionType.INFO);

    private final String message;
    private final ExceptionType type;

    /**
     * Constructor for ExceptionMessage.
     * 
     * @param message The message to be printed when the exception is called.
     */
    ExceptionMessage(String message, ExceptionType type) {
        this.message = message;
        this.type = type;
    }

    /**
     * Returns the message of the exception.
     * 
     * @return The message of the exception.
     */
    public String getMessage() {
        return this.message;
    }

    /**
     * Returns the type of the exception.
     * 
     * @return The type of the exception.
     */
    public ExceptionType getType() {
        return this.type;
    }
}
