package seedu.duke;

import java.util.Optional;

/**
 * Represents a command handler for group-related operations.
 * Provides static methods to create groups, add members to groups, and exit groups.
 */
public class GroupCommand {
    /**
     * Creates a new group or retrieves an existing group with the specified name.
     *
     * @param groupName the name of the group to create or retrieve
     */
    public static void createGroup(String groupName) {
        try {
            Group.getOrCreateGroup(groupName);
        } catch (IllegalStateException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Adds a member with the specified name to the current group.
     * If the user is not currently in a group, prints a message asking them to create or join a group first.
     *
     * @param memberName the name of the member to add
     */
    public static void addMember(String memberName) {
        Optional<Group> currentGroup = Group.getCurrentGroup();
        if (currentGroup.isEmpty()) {
            System.out.println("Please create or join a group first.");
            return;
        }

        currentGroup.get().addMember(memberName);
    }

    /**
     * Exits the current group.
     * If the user is not currently in a group, prints a message indicating so.
     */
    public static void exitGroup() {
        Group.exitGroup();
    }
}
