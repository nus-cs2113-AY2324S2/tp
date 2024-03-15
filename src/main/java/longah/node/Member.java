package longah.node;

import java.util.regex.Pattern;

import longah.exception.LongAhException;
import longah.exception.ExceptionMessage;

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
     * @throws LongAhException If the name is invalid.
     */
    public Member(String name) throws LongAhException {
        // Check if name is fully alphanumeric
        if (!Pattern.matches("[A-Za-z0-9]+", name)) {
            throw new LongAhException(ExceptionMessage.INVALID_MEMBER_NAME);
        }

        this.name = name;
        this.balance = 0.0;
    }

    /**
     * Adds the specified amount to the member's balance.
     *
     * @param amount The amount to add to the balance.
     */
    public synchronized void addToBalance(double amount) throws LongAhException {
        if (amount <= 0) {
            throw new LongAhException(ExceptionMessage.INVALID_TRANSACTION_VALUE);
        }
        this.balance += amount;
    }

    /**
     * Subtracts the specified amount from the member's balance.
     *
     * @param amount The amount to subtract from the balance.
     */
    public synchronized void subtractFromBalance(double amount) throws LongAhException {
        if (amount <= 0) {
            throw new LongAhException(ExceptionMessage.INVALID_TRANSACTION_VALUE);
        }
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

    /**
     * Used to check whether the input String matches the name of a member.
     * @param memberName String representation of a member name
     * @return A boolean value checking whether the input matches with name.
     */
    public boolean isName(String memberName) {
        return name.equals(memberName);
    }
}
