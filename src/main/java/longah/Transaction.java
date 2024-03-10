package longah;

/**
 * Represents a transaction between two members.
 */
public class Transaction {
    private Member from;
    private Member to;
    private double amount;

    /**
     * Constructs a new Transaction instance.
     *
     * @param from   The member who owes the amount.
     * @param to     The member who is owed the amount.
     * @param amount The amount of the transaction.
     */
    public Transaction(Member from, Member to, double amount) {
        this.from = from;
        this.to = to;
        this.amount = amount;
        from.subtractFromBalance(amount);
        to.addToBalance(amount);
    }

    /**
     * Checks if the transaction involves a specific person.
     *
     * @param person The name of the person to check.
     * @return True if the person is involved in the transaction, false otherwise.
     */
    public boolean getInvolves(String person) {
        return from.toString().equals(person) || to.toString().equals(person);
    }

    /**
     * Returns a string representation of the transaction.
     *
     * @return A string describing the transaction.
     */
    @Override
    public String toString() {
        return from + " owes " + to + " $" + amount;
    }

    /**
     * Gets the member who owes the amount.
     *
     * @return The member who owes the amount.
     */
    public Member getFrom() {
        return from;
    }

    /**
     * Gets the member who is owed the amount.
     *
     * @return The member who is owed the amount.
     */
    public Member getTo() {
        return to;
    }

    /**
     * Gets the amount of the transaction.
     *
     * @return The amount of the transaction.
     */
    public double getAmount() {
        return amount;
    }
}
