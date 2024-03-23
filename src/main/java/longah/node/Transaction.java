package longah.node;

import java.util.ArrayList;

import longah.util.MemberList;
import longah.exception.LongAhException;
import longah.exception.ExceptionMessage;
import longah.util.Subtransaction;

/**
 * Represents a transaction between two members.
 */
public class Transaction {
    private Member lender;
    private ArrayList<Subtransaction> subtransactions = new ArrayList<>();

    /**
     * Constructs a new Transaction instance with the given user input and member list.
     * 
     * @param userInput The user input for the transaction.
     * @param memberList The list of members in the group.
     * @throws LongAhException If the user input is in an invalid format or value.
     */
    public Transaction(String userInput, MemberList memberList) throws LongAhException {
        parseTransaction(userInput, memberList);
    }

    /**
     * Constructs a new Transaction instance with the given lender, subtransactions and member list.
     * Used for storage methods only.
     * 
     * @param lender The member who lent the money in the transaction.
     * @param subtransactions The list of subtransactions in the transaction.
     * @param members The list of members in the group.
     * @throws LongAhException If the lender does not exist in the group.
     */
    public Transaction(Member lender, ArrayList<Subtransaction> subtransactions,
            MemberList members) throws LongAhException {
        // Exception is thrown if any of the members do not exist in the group
        if (!members.isMember(lender)) {
            throw new LongAhException(ExceptionMessage.INVALID_STORAGE_CONTENT);
        }
        for (Subtransaction subtransaction : subtransactions) {
            if (!members.isMember(subtransaction.getBorrower())) {
                throw new LongAhException(ExceptionMessage.INVALID_STORAGE_CONTENT);
            }
        }

        this.lender = lender;
        this.subtransactions = subtransactions;
    }

    public void parseTransaction(String expression, MemberList members) throws LongAhException {
        // User input format: [Lender] p/[Borrower1] a/[amount1] p/[Borrower2] a/[amount2] ...
        String[] splitInput = expression.split("p/");
        if (splitInput.length < 2 || splitInput[0].isEmpty()) {
            // Minimum of 2 people as part of a transaction
            throw new LongAhException(ExceptionMessage.INVALID_TRANSACTION_FORMAT);
        }
        assert splitInput.length >= 2 : "Invalid transaction.";

        // Check for existence of all parties involved in the transaction in the group.
        String lenderName = splitInput[0].trim();
        this.lender = members.getMember(lenderName);
        for (int i = 1; i < splitInput.length; i++) {
            String borrowNameAmount = splitInput[i].trim();
            addBorrower(borrowNameAmount, members);
        }
    }

    /**
     * Adds a borrower to the subtransaction list.
     * 
     * @param expression The expression containing the borrower and amount borrowed.
     * @param memberList The list of members in the group.
     * @throws LongAhException If the expression is in an invalid format or value.
     */
    public void addBorrower(String expression, MemberList memberList) throws LongAhException {
        String[] splitBorrower = expression.split("a/");
        if (splitBorrower.length != 2) {
            // Each person owing should have an amount specified
            // Feature may be changed in the future
            throw new LongAhException(ExceptionMessage.INVALID_TRANSACTION_FORMAT);
        }

        String borrowerName = splitBorrower[0].trim();
        // Exception is thrown if the borrower does not exist in the group
        Member borrower = memberList.getMember(borrowerName);
        Double amountBorrowed;
        try {
            amountBorrowed = Double.parseDouble(splitBorrower[1].trim());
        } catch (NumberFormatException e) {
            throw new LongAhException(ExceptionMessage.INVALID_VALUE_FORMAT);
        }

        if (amountBorrowed <= 0) {
            throw new LongAhException(ExceptionMessage.INVALID_TRANSACTION_VALUE);
        }
        assert amountBorrowed > 0 : "Amount owed should be positive.";
        Subtransaction subtransaction = new Subtransaction(this.lender, borrower, amountBorrowed);
        this.subtransactions.add(subtransaction);
    }

    /**
     * Gets the member who is the lender in the transaction.
     *
     * @return The member who is the lender in the transaction
     */
    public Member getLender() {
        return this.lender;
    }

    /**
     * Checks whether the input member name is the lender of a transaction.
     *
     * @param memberName String representation of member name to check
     * @return a boolean value determining whether the input name is the owner of the transaction
     */
    public boolean isLender(String memberName) {
        return lender.isName(memberName);
    }

    /**
     * Checks whether the input member name is a borrower within the transaction
     *
     * @param memberName String representation of member name to check
     * @return a boolean value determining whether the input name is a borrower in the transaction
     */
    public boolean isBorrower(String memberName) {
        for (Subtransaction subtransaction : this.subtransactions) {
            if (subtransaction.getBorrower().isName(memberName)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks whether the input member name is involved in the transaction.
     *
     * @param memberName String representation of member name to check
     * @return a boolean value determining whether the input name is involved in the transaction
     */
    public boolean isInvolved(String memberName) {
        return isLender(memberName) || isBorrower(memberName);
    }

    /**
     * Returns a string representation of the transaction for printouts.
     * 
     * @return a string representation of the transaction
     */
    @Override
    public String toString() {
        String lender = "Lender: " + this.lender.getName() + "\n";
        String borrower = "";
        int borrowerNo = 1;
        for (Subtransaction subtransaction : subtransactions) {
            Member member = subtransaction.getBorrower();
            double amount = subtransaction.getAmount();
            borrower += String.format("Borrower %d: %s Owed amount: %,.2f\n",
                    borrowerNo, member.getName(), amount);
            borrowerNo++;
        }
        return lender + borrower;
    }

    /**
     * Returns a string representation of the transaction for storage.
     * 
     * @param delimiter The delimiter to separate the lender and borrowers.
     * @return a string representation of the transaction for storage
     */
    public String toStorageString(String delimiter) {
        String lender = this.lender.getName();
        String borrower = "";
        for (Subtransaction subtransaction : this.subtransactions) {
            String borrowerName = subtransaction.getBorrower().getName();
            double amount = subtransaction.getAmount();
            borrower += delimiter + borrowerName + delimiter + amount;
        }
        return lender + borrower;
    }

    /**
     * Returns the list of subtransactions in the transaction.
     * 
     * @return The list of subtransactions in the transaction.
     */
    public ArrayList<Subtransaction> getSubtransactions() {
        return this.subtransactions;
    }

    /**
     * Edits the specified transaction based on user input.
     *
     * @param expression   The user input for editing the transaction.
     * @param memberList   The list of members in the group.
     * @throws LongAhException If the transaction index is invalid or if the edit input is in an invalid format.
     */
    public void editTransaction(String expression, MemberList memberList) throws LongAhException {
        subtransactions.clear();
        parseTransaction(expression, memberList);
    }
}
