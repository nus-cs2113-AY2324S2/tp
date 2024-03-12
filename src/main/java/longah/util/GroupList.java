package longah.util;

import java.util.ArrayList;
import java.util.List;

import longah.node.Member;

/**
 * Represents a list of group members.
 */
public class GroupList {
    private List<Member> members;

    /**
     * Constructs a new GroupList instance.
     */
    public GroupList() {
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
     * Gets the list of members in the group.
     *
     * @return The list of members.
     */
    public List<Member> getMembers() {
        return members;
    }
}
