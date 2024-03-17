package longah.util;

import java.util.ArrayList;

import longah.node.Member;
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
     */
    public void addMember(Member member) {
        members.add(member);
    }

    /**
     * Adds a member to the group with the specified name.
     *
     * @param name The name of the member to add.
     */
    public void addMember(String name) throws LongAhException {
        if (isMember(name)) {
            throw new LongAhException(ExceptionMessage.DUPLICATE_MEMBER);
        }
        members.add(new Member(name));
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
     * Prints the list of members in the group.
     */
    public void listMembers() {
        if (members.isEmpty()) {
            System.out.println("No members in the group.");
            return;
        }

        for (Member member : members) {
            System.out.println(member);
        }
    }

    /**
     * Groups members into two lists: positive balances and negative balances.
     * 
     * @return An array of two lists: members with positive balances and members with negative balances.
     */
    public ArrayList<ArrayList<Member>> classifyMembers() {
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

        return classifiedMembers;
    }

    /**
     * Finds the least transaction needed to solve the balances of the group members.
     * This is done by pairing up members with positive balances and negative balances.
     * The members are then iterated through and the balances are solved by subtracting the
     * negative balance from the positive balance until the transaction has been solved.
     */
    public ArrayList<Subtransaction> solveTransactions() {
        ArrayList<ArrayList<Member>> classifiedMembers = classifyMembers();
        ArrayList<Member> positiveMembers = classifiedMembers.get(0);
        ArrayList<Member> negativeMembers = classifiedMembers.get(1);

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
//                        new Subtransaction(negativeMember, positiveMember,
//                        negativeBalance);
                        new Subtransaction(positiveMember, negativeMember,
                                negativeBalance);
                positiveBalance -= negativeBalance;
                negativeBalance = 0;
                subtransactions.add(subtransaction);
                negativeIndex++;

            } else if (positiveBalance < negativeBalance) {
                Subtransaction subtransaction = 
//                        new Subtransaction(negativeMember, positiveMember,
//                        positiveBalance);
                        new Subtransaction(positiveMember, negativeMember,
                                positiveBalance);
                negativeBalance -= positiveBalance;
                positiveBalance = 0;
                subtransactions.add(subtransaction);
                positiveIndex++;

            } else {
                Subtransaction subtransaction = 
//                        new Subtransaction(negativeMember, positiveMember,
//                        positiveBalance);
                        new Subtransaction(positiveMember, negativeMember,
                                positiveBalance);
                positiveBalance = 0;
                negativeBalance = 0;
                subtransactions.add(subtransaction);
                positiveIndex++;
                negativeIndex++;
            }
        }

        return subtransactions;
    }
}
