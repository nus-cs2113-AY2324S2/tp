package longah.util;

import longah.node.Member;

/**
 * Represents a subtransaction within a transaction.
 */
public class Subtransaction {
    private Member personOwed;
    private Member personOwing;
    private double amount;

    /**
     * Constructs a new Subtransaction instance with the given person owed, person owing, and amount.
     * 
     * @param personOwed The person owed in the subtransaction.
     * @param personOwing The person owing in the subtransaction.
     * @param amount The amount owed in the subtransaction.
     */
    public Subtransaction(Member personOwed, Member personOwing, double amount) {
        this.personOwed = personOwed;
        this.personOwing = personOwing;
        this.amount = amount;
    }

    /**
     * Returns the person owed in the subtransaction.
     * 
     * @return The person owed in the subtransaction.
     */
    public Member getPersonOwed() {
        return personOwed;
    }

    /**
     * Returns the person owing in the subtransaction.
     * 
     * @return The person owing in the subtransaction.
     */
    public Member getPersonOwing() {
        return personOwing;
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
        return personOwed.getName() + " owes " + personOwing.getName() + " $" + amount;
    }
}
