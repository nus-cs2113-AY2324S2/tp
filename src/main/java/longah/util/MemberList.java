package longah.util;

import java.util.ArrayList;

import longah.node.Member;
import longah.node.Transaction;
import longah.exception.LongAhException;
import longah.exception.ExceptionMessage;

/**
 * Represents a list of group members.
 */
public class MemberList {
    private ArrayList<Member> members;

    /**
     * Constructs a new GroupList instance.
     */
    public MemberList() {
        this.members = new ArrayList<>();
    }

    /**
     * Adds a member to the group.
     *
     * @param member The member to add.
     * @throws LongAhException If the member already exists in the group.
     */
    public void addMember(Member member) throws LongAhException {
        if (isMember(member)) {
            throw new LongAhException(ExceptionMessage.DUPLICATE_MEMBER);
        }
        this.members.add(member);
    }

    /**
     * Adds a member to the group with the specified name.
     *
     * @param name The name of the member to add.
     * @throws LongAhException If the member already exists in the group.
     */
    public void addMember(String name) throws LongAhException {
        if (isMember(name)) {
            throw new LongAhException(ExceptionMessage.DUPLICATE_MEMBER);
        }
        this.members.add(new Member(name));
    }

    /**
     * Adds a member to the group with the specified name and balance.
     * For use in storage only.
     * 
     * @param name The name of the member to add.
     * @param balance The balance of the member to add.
     * @throws LongAhException If the member already exists in the group.
     */
    public void addMember(String name, double balance) throws LongAhException {
        if (isMember(name)) {
            throw new LongAhException(ExceptionMessage.DUPLICATE_MEMBER);
        }
        this.members.add(new Member(name, balance));
    }

    /**
     * Returns true if the member is in the group, false otherwise.
     * 
     * @param name The name of the member to check for.
     * @return True if the member is in the group, false otherwise.
     */
    public boolean isMember(String name) {
        for (Member member : this.members) {
            if (member.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns true if the member is in the group, false otherwise.
     * 
     * @param member The member to check for.
     * @return True if the member is in the group, false otherwise.
     */
    public boolean isMember(Member member) {
        return this.members.contains(member);
    }

    /**
     * Returns the member object with the specified name.
     * 
     * @param name The name of the member to get.
     * @return The member with the specified name.
     * @throws LongAhException If the member does not exist in the group.
     */
    public Member getMember(String name) throws LongAhException {
        for (Member member : members) {
            if (member.getName().equals(name)) {
                return member;
            }
        }
        throw new LongAhException(ExceptionMessage.MEMBER_NOT_FOUND);
    }

    /**
     * Changes the name of the member at the specified index.
     * 
     * @param expression The expression containing the index and new name.
     * @throws LongAhException If the index is invalid.
     */
    public void editMemberName(String expression) throws LongAhException {
        try {
            String[] indexNameSplice = expression.split(" ", 2);
            int index = Integer.parseInt(indexNameSplice[0]) - 1;
            String name = indexNameSplice[1];
            members.get(index).setName(name);
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            throw new LongAhException(ExceptionMessage.INVALID_INDEX);
        }
    }

    /**
     * Prints the list of members in the group.
     * @throws LongAhException If there are no members in the group.
     */
    public String listMembers() throws LongAhException {
        if (members.isEmpty()) {
            throw new LongAhException(ExceptionMessage.NO_MEMBERS_FOUND);
        }
        String output = "";
        for (Member member : members) {
            output += member + "\n";
        }
        return output;
    }

    /**
     * Updates the balances of the members in the group based on the transactions.
     * 
     * @param transactions The list of transactions to update the balances with.
     * @throws LongAhException If there are no members in the group.
     */
    public void updateMembersBalance(TransactionList transactions) throws LongAhException {
        clearBalances();
        if (transactions.getTransactions().isEmpty()) {
            return;
        }
        for (Transaction transaction : transactions.getTransactions()) {
            for (Subtransaction subtransaction : transaction.getSubtransactions()) {
                Member lender = subtransaction.getLender();
                Member borrower = subtransaction.getBorrower();
                double amount = subtransaction.getAmount();
                lender.addToBalance(amount);
                borrower.subtractFromBalance(amount);
            }
        }
    } 

    /**
     * Groups members into two lists: positive balances and negative balances.
     * 
     * @return An array of two lists: members with positive balances and members with negative balances.
     * @throws LongAhException If there are no members in the group.
     */
    public ArrayList<ArrayList<Member>> classifyMembers() throws LongAhException {
        if (members.isEmpty()) {
            throw new LongAhException(ExceptionMessage.NO_MEMBERS_FOUND);
        }

        ArrayList<Member> positiveMembers = new ArrayList<>();
        ArrayList<Member> negativeMembers = new ArrayList<>();

        for (Member member : members) {
            if (member.getBalance() > 0) {
                positiveMembers.add(member);
            } else if (member.getBalance() < 0) {
                negativeMembers.add(member);
            }
        }

        ArrayList<ArrayList<Member>> classifiedMembers = new ArrayList<>();
        classifiedMembers.add(positiveMembers);
        classifiedMembers.add(negativeMembers);

        if (positiveMembers.isEmpty() || negativeMembers.isEmpty()) {
            throw new LongAhException(ExceptionMessage.TRANSACTIONS_SUMMED_UP);
        }

        return classifiedMembers;
    }

    /**
     * Finds the least transaction needed to solve the balances of the group members.
     * This is done by pairing up members with positive balances and negative balances.
     * The members are then iterated through and the balances are solved by subtracting the
     * negative balance from the positive balance until the transaction has been solved.
     * 
     * @return The list of subtransactions needed to solve the balances of the group members.
     */
    public ArrayList<Subtransaction> solveTransactions() throws LongAhException {
        ArrayList<ArrayList<Member>> classifiedMembers;
        try {
            classifiedMembers = classifyMembers();
        } catch (LongAhException e) {
            return new ArrayList<>();
        }
        
        ArrayList<Member> positiveMembers = classifiedMembers.get(0);
        ArrayList<Member> negativeMembers = classifiedMembers.get(1);
        assert !positiveMembers.isEmpty() && !negativeMembers.isEmpty() : "Members should be classified.";

        ArrayList<Subtransaction> subtransactions = new ArrayList<>();
        int positiveIndex = 0;
        int negativeIndex = 0;
        double positiveBalance = 0;
        double negativeBalance = 0;
        Member positiveMember = positiveMembers.get(positiveIndex);
        Member negativeMember = negativeMembers.get(negativeIndex);

        while (positiveIndex < positiveMembers.size() &&
                negativeIndex < negativeMembers.size()) {

            // If either balance is 0, move to their respective next member
            if (positiveBalance == 0) {
                positiveMember = positiveMembers.get(positiveIndex);
                positiveBalance = positiveMember.getBalance();
            }
            if (negativeBalance == 0) {
                negativeMember = negativeMembers.get(negativeIndex);
                negativeBalance = Math.abs(negativeMember.getBalance());
            }

            // Check the current pair for which balance is greater or if equal
            if (positiveBalance > negativeBalance) {
                Subtransaction subtransaction =
                        new Subtransaction(positiveMember, negativeMember, negativeBalance);
                positiveBalance -= negativeBalance;
                negativeBalance = 0;
                subtransactions.add(subtransaction);
                negativeIndex++;

            } else if (positiveBalance < negativeBalance) {
                Subtransaction subtransaction =
                        new Subtransaction(positiveMember, negativeMember, positiveBalance);
                negativeBalance -= positiveBalance;
                positiveBalance = 0;
                subtransactions.add(subtransaction);
                positiveIndex++;

            } else {
                Subtransaction subtransaction =
                        new Subtransaction(positiveMember, negativeMember, positiveBalance);
                positiveBalance = 0;
                negativeBalance = 0;
                subtransactions.add(subtransaction);
                positiveIndex++;
                negativeIndex++;
            }
        }

        return subtransactions;
    }

    /**
     * Get the number of members in the group.
     * @return The number of members in the group.
     */
    public int getMemberListSize() {
        return members.size();
    }

    /**
     * Returns the list of members, of type in the group.
     * For use in storage only.
     * 
     * @return The list of members in the group.
     */
    public ArrayList<Member> getMembers() {
        return members;
    }

    /**
     * Returns the balance of the member with the specified name.
     * 
     * @param name The name of the member to get the balance of.
     * @return The balance of the member with the specified name.
     * @throws LongAhException If the member does not exist in the group.
     */
    public double getMemberBalance(String name) throws LongAhException {
        return getMember(name).getBalance();
    }

    /**
     * Iterates through the members list and clears their balances.
     */
    public void clearBalances() {
        for (Member member : members) {
            member.clearBalance();
        }
    }

    /**
     * Deletes a member from the group.
     * 
     * @param name The name of the member to delete.
     * @throws LongAhException If the member does not exist in the group.
     */
    public void deleteMember(String name) throws LongAhException {
        Member member = getMember(name);
        members.remove(member);
    }
}
