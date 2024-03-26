package seedu.duke;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class Group {
    private static final Map<String, Group> groups = new HashMap<>();
    private static Optional<String> currentGroupName = Optional.empty();

    private final String groupName;
    private final List<User> members;

    private Group(String groupName) {
        this.groupName = groupName;
        this.members = new ArrayList<>();
    }

    /**
     * Retrieves an existing group by its name or creates a new one if it does not exist.
     * It ensures that a user cannot create or join a new group without exiting their current group.
     *
     * @param groupName The name of the group to get or create.
     * @return The existing or newly created group.
     * @throws IllegalStateException If trying to create or join a new group while already in another group.
     */
    public static Optional<Group> getOrCreateGroup(String groupName) {
        // Check if user is accessing a group they are already in
        getCurrentGroup().ifPresent(currentGroup -> {
            if (currentGroup.getGroupName().equals(groupName)) {
                System.out.println("You are in " + groupName);
            }
        });

        // If the user is in a different group, prevent them from creating or joining a new group.
        if (isInGroup()) {
            getCurrentGroup().ifPresent(currentGroup -> {
                if (!currentGroup.getGroupName().equals(groupName)) {
                    throw new IllegalStateException("Please exit the current group '" + currentGroup.getGroupName()
                            + "' to create or join another group.");
                }
            });
        }

        Optional<Group> group = Optional.ofNullable(groups.get(groupName));

        if (group.isEmpty()) {
            Group newGroup = new Group(groupName);
            groups.put(groupName, newGroup);
            System.out.println(groupName + " created.");
            currentGroupName = Optional.of(groupName);
            group = Optional.of(newGroup);
        }

        System.out.println("You are now in " + groupName);
        return group;
    }

    /**
     * Exits the current group.
     * If the user is not in any group, it displays a message asking the user to try again.
     */
    public static void exitGroup() {
        if (currentGroupName.isPresent()) {
            System.out.println("You have exited " + currentGroupName.get() + ".");
            currentGroupName = Optional.empty();
        } else {
            System.out.println("You are not currently in any group. Please try again.");
        }
    }

    /**
     * Retrieves the current group.
     *
     * @return The current group, or null if the user is not in any group.
     */
    public static Optional<Group> getCurrentGroup() {
        return currentGroupName.map(groups::get);
    }

    /**
     * Checks if the user is currently in a group.
     *
     * @return true if the user is in a group, false otherwise.
     */
    public static boolean isInGroup() {
        return currentGroupName.isPresent();
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
     * @return The newly added user, or null if the user is already a member of the group.
     */
    public User addMember(String memberName) {
        if (isMember(memberName)) {
            System.out.println(memberName + " is already a member of " + groupName + ".");
            return null;
        }

        User newMember = new User(memberName);
        members.add(newMember);
        System.out.println(memberName + " has been added to " + groupName + ".");
        return newMember;
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
}
