package longah.util;

import longah.node.Member;

/**
 * Represents a subtransaction within a transaction.
 */
public class Subtransaction {
    private Member lender;
    private Member borrower;
    private double amount;

    /**
     * Constructs a new Subtransaction instance with the given lender, person borrower, and amount.
     * 
     * @param lender The lender who lends out money in the subtransaction.
     * @param borrower The borrower who borrows money in the subtransaction.
     * @param amount The amount borrowed in the subtransaction.
     */
    public Subtransaction(Member lender, Member borrower, double amount) {
        this.lender = lender;
        this.borrower = borrower;
        this.amount = amount;
    }

    /**
     * Returns the lender in the subtransaction.
     * 
     * @return The lender in the subtransaction.
     */
    public Member getLender() {
        return lender;
    }

    /**
     * Returns the borrower in the subtransaction.
     * 
     * @return The borrower in the subtransaction.
     */
    public Member getBorrower() {
        return borrower;
    }

    /**
     * Returns the amount owed in the subtransaction.
     * 
     * @return The amount owed in the subtransaction.
     */
    public double getAmount() {
        return amount;
    }

    /**
     * Returns a string representation of the subtransaction.
     * 
     * @return A string representation of the subtransaction.
     */
    @Override
    public String toString() {
        return borrower.getName() + " owes " + lender.getName() + " $" + amount;
    }
}
