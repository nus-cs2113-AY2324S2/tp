package seedu.duke;

import java.util.HashMap;


public class Group {
    static Optional<String> currentGroupName = Optional.empty();
    static final HashMap<String, Group> groups = new HashMap<>();
    protected String groupName;
    protected ArrayList<User> users;

    public Group(String groupName) {
        this.groupName = groupName;
        this.users = new ArrayList<>();
    }


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
        if (currentGroupName.isPresent() && currentGroupName.get().equals(groupName)) {
            System.out.println("You are in " + groupName);
            return Optional.ofNullable(groups.get(groupName));
        }

        // If the user is in a different group, prevent them from creating or joining a new group.
        if (isInGroup() && !getCurrentGroup().getGroupName().equals(groupName)) {
            throw new IllegalStateException("Please exit the current group '" + currentGroupName
                    + "' to create or join another group.");
        }

        Group group = groups.get(groupName);


        // If the user is in a different group, prevent them from creating or joining a new group.
        Group group = optionalGroup.orElseGet(() -> {
            if (currentGroupName.isPresent() && !currentGroupName.get().equals(groupName)) {
                throw new IllegalStateException("Please exit the current group '" + currentGroupName + " first.");
            }
            Group newGroup = new Group(groupName);
            groups.put(groupName, newGroup);
            System.out.println(groupName + " created.");
            isNewGroupCreated[0] = true;
            return newGroup;
        });

        // If a new group was created, update currentGroupName to reflect this.
        if (isNewGroupCreated[0]) {
            currentGroupName = Optional.of(groupName);
        }

        System.out.println("You are now in " + groupName);
        return Optional.ofNullable(groups.get(groupName));
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
        System.out.println("Current group: " + currentGroupName.orElse(null));
    }

    /**
     * Retrieves the current group.
     *
     * @return The current group, or null if the user is not in any group.
     */
    public static Group getCurrentGroup() {
        if (currentGroupName != null) {
            return groups.get(currentGroupName);
        }
        return null;
    }

    /**
     * Checks if the user is currently in a group.
     *
     * @return true if the user is in a group, false otherwise.
     */
    public static boolean isInGroup() {
        return currentGroupName != null;
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

