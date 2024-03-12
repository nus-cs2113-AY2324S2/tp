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
     * Returns true if the member is in the group, false otherwise.
     * 
     * @param name The name of the member to check for.
     * @return True if the member is in the group, false otherwise.
     */
    public boolean isMember(String name) {
        for (Member member : members) {
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
        throw new LongAhException(ExceptionMessage.INVALID_MEMBER_NAME);
    }

    /**
     * Groups members into two lists: positive balances and negative balances.
     * 
     * @return An array of two lists: members with positive balances and members with negative balances.
     */
    public ArrayList<ArrayList<Member>> classifyMembers() {
        ArrayList<Member> positiveBalances = new ArrayList<>();
        ArrayList<Member> negativeBalances = new ArrayList<>();

        for (Member member : members) {
            if (member.getBalance() > 0) {
                positiveBalances.add(member);
            } else if (member.getBalance() < 0) {
                negativeBalances.add(member);
            }
        }

        ArrayList<ArrayList<Member>> classifiedMembers = new ArrayList<>();
        classifiedMembers.add(positiveBalances);
        classifiedMembers.add(negativeBalances);

        return classifiedMembers;
    }

    /**
     * Finds the least transaction needed to solve the balances of the group members.
     * This is done by 
     */
    public void solveTransactions() {
        ArrayList<ArrayList<Member>> classifiedMembers = classifyMembers();
        ArrayList<Member> positiveBalances = classifiedMembers.get(0);
        ArrayList<Member> negativeBalances = classifiedMembers.get(1);

        int positiveIndex = 0;
        int negativeIndex = 0;
        double positiveBalance = 0;
        double negativeBalance = 0;

        while (positiveIndex < positiveBalances.size() && negativeIndex < negativeBalances.size()) {
            if (positiveBalance == 0) {
                Member positiveMember = positiveBalances.get(positiveIndex);
                positiveBalance = positiveMember.getBalance();
            }
            if (negativeBalance == 0) {
                Member negativeMember = negativeBalances.get(negativeIndex);
                negativeBalance = negativeMember.getBalance();
            }

            if (positiveBalance > Math.abs(negativeBalance)) {
                positiveBalance += negativeBalance;
                negativeBalance -= negativeBalance;
                negativeIndex++;
            } else if (positiveBalance < Math.abs(negativeBalance)) {
                negativeBalance += positiveBalance;
                positiveBalance -= positiveBalance;
                positiveIndex++;
            } else {
                positiveBalance -= positiveBalance;
                negativeBalance += positiveBalance;
                positiveIndex++;
                negativeIndex++;
            }
        }
    }
}
