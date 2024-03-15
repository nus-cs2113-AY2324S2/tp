package longah.node;

import java.util.HashMap;

import longah.util.MemberList;
import longah.exception.LongAhException;
import longah.exception.ExceptionMessage;

/**
 * Represents a transaction between two members.
 */
public class Transaction {
    private Member personOwed;
    private HashMap<Member, Double> subtransactions = new HashMap<>();

    /**
     * Constructs a new Transaction instance with the given user input and member list.
     * 
     * @param userInput The user input for the transaction.
     * @param memberList The list of members in the group.
     * @throws LongAhException If the user input is in an invalid format or value.
     */
    public Transaction(String userInput, MemberList memberList) throws LongAhException {
        // User input format: p/[person owed] p/[person1 owing] a/[amount1] p/[person2 owing] a/[amount2] ...
        String[] splitInput = userInput.split("p/");

        if (splitInput.length < 3) {
            // Minimum of 2 people as part of a transaction
            throw new LongAhException(ExceptionMessage.INVALID_TRANSACTION_FORMAT);
        }

        String personOwedName = splitInput[1].trim();
        // Exception is thrown if the person owed does not exist in the group
        this.personOwed = memberList.getMember(personOwedName);
        double totalSumOwed = 0.0;

        for (int i = 2; i < splitInput.length; i++) {
            String nameValue = splitInput[i].trim();
            totalSumOwed += addPayee(nameValue, memberList);
        }

        this.personOwed.addToBalance(totalSumOwed);
        updateBalances();
    }

    /**
     * Adds a payee to the transaction.
     * 
     * @param expression The expression containing the payee and amount owed.
     * @param memberList The list of members in the group.
     * @return The amount owed by the payee.
     * @throws LongAhException If the expression is in an invalid format or value.
     */
    public Double addPayee(String expression, MemberList memberList) throws LongAhException {
        String[] splitPersonOwing = expression.split("a/");
        if (splitPersonOwing.length != 2) {
            // Each person owing should have an amount specified
            // Feature may be changed in the future
            throw new LongAhException(ExceptionMessage.INVALID_TRANSACTION_FORMAT);
        }

        String personOwingName = splitPersonOwing[0].trim();
        // Exception is thrown if the person owing does not exist in the group
        Member personOwing = memberList.getMember(personOwingName);
        Double amountOwed;
        try {
            amountOwed = Double.parseDouble(splitPersonOwing[1].trim());
        } catch (NumberFormatException e) {
            throw new LongAhException(ExceptionMessage.INVALID_VALUE_FORMAT);
        }

        if (amountOwed <= 0) {
            throw new LongAhException(ExceptionMessage.INVALID_TRANSACTION_VALUE);
        }

        this.subtransactions.put(personOwing, amountOwed);
        return amountOwed;
    }

    /**
     * Updates the balances of the members involved in the transaction.
     */
    public void updateBalances() throws LongAhException {
        for (HashMap.Entry<Member, Double> entry : this.subtransactions.entrySet()) {
            Member member = entry.getKey();
            double amount = entry.getValue();
            member.subtractFromBalance(amount);
        }
    }

    /**
     * Checks whether the input member name is the owner of a transaction.
     *
     * @param memberName String representation of member name to check
     * @return a boolean value determining whether the input name is the owner of the transaction
     */
    public boolean isOwned(String memberName) {
        return personOwed.isName(memberName);
    }

    /**
     * Checks whether the input member name is a payee within the transaction
     *
     * @param memberName String representation of member name to check
     * @return a boolean value determining whether the input name is a payee in the transaction
     */
    public boolean isPayee(String memberName) {
        for (Member member : subtransactions.keySet()) {
            if (member.isName(memberName)) {
                return true;
            }
        }
        return false;
    }

    /**
     * @return a string representation of the transaction for printouts
     */
    public String toString() {
        String owner = "Owner: " + personOwed.getName() + "\n";
        String payee = "";
        int payeeNo = 1;
        for (HashMap.Entry<Member, Double> entry : subtransactions.entrySet()) {
            Member member = entry.getKey();
            double amount = entry.getValue();
            payee += String.format("Payee %d: %s Owed amount: %,.2f\n", payeeNo, member.getName(), amount);
            payeeNo++;
        }
        return owner + payee;
    }

}
