package longah.node;

/**
 * Represents a member in the LongAh application.
 */
public class Member {
    private String name;
    private double balance;

    /**
     * Constructs a new Member instance with the given name and zero balance.
     *
     * @param name The name of the member.
     */
    public Member(String name) {
        this.name = name;
        this.balance = 0.0;
    }

    /**
     * Adds the specified amount to the member's balance.
     *
     * @param amount The amount to add to the balance.
     */
    public void addToBalance(double amount) {
        this.balance += amount;
    }

    /**
     * Subtracts the specified amount from the member's balance.
     *
     * @param amount The amount to subtract from the balance.
     */
    public void subtractFromBalance(double amount) {
        this.balance -= amount;
    }

    /**
     * Gets the current balance of the member.
     *
     * @return The balance of the member.
     */
    public double getBalance() {
        return this.balance;
    }

    /**
     * Returns a string representation of the member, including name and balance.
     *
     * @return A string representation of the member.
     */
    @Override
    public String toString() {
        if (this.balance >= 0) {
            return this.name + ": $" + this.balance;
        }
        return this.name + ": -$" + Math.abs(this.balance);
    }

    /**
     * Gets the name of the member.
     *
     * @return The name of the member.
     */
    public String getName() {
        return this.name;
    }
}
