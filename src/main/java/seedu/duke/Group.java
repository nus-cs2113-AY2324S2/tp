package seedu.duke;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class Group {
    private final String groupName;
    private final List<User> members;

    private Group(String groupName) {
        this.groupName = groupName;
        this.members = new ArrayList<>();
    }

    /**
     * Factory method to create a new Group
     *
     * @param groupName The name of the group to get or create.
     * @return Newly created group.
     */
    public static Group createGroup(String groupName) {
        return new Group(groupName);
    }

    /**
     * Checks if a user with the given name is a member of the group.
     *
     * @param memberName The name of the member to check.
     * @return true if the user is a member of the group, false otherwise.
     */
    public boolean isMember(String memberName) {
        for (User member : members) {
            if (member.getName().equals(memberName)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Adds a new member to the group.
     *
     * @param memberName The name of the member to add.
     */
    public void addMember(String memberName) {
        if (isMember(memberName)) {
            System.out.println(
                    memberName + " is already a member of " + groupName + ".");
            return;
        }
        User newMember = new User(memberName);
        members.add(newMember);
        System.out.println(
                memberName + " has been added to " + groupName + ".");
    }

    /**
     * Retrieves the name of the group.
     *
     * @return The name of the group.
     */
    public String getGroupName() {
        return groupName;
    }

    /**
     * Retrieves the list of members in the group.
     *
     * @return The list of members in the group.
     */
    public List<User> getMembers() {
        return new ArrayList<>(members);
    }

    @Override
    public String toString() {
        return String.format("%s", this.groupName);
    }
}
