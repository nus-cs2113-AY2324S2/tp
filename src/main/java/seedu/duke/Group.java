package seedu.duke;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Optional;

public class Group {
    public static final HashMap<String, Group> groups = new HashMap<>();
    public static String currentGroupName = null;
    protected String groupName;
    protected ArrayList<User> users;

    public Group(String groupName) {
        this.groupName = groupName;
        this.users = new ArrayList<>();
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public String getGroupName() {
        return groupName;
    }

    /**
     * Retrieves an existing group by its name or creates a new one if it does not exist.
     * It ensures that a user cannot create or join a new group without exiting their current group.
     *
     * @param groupName The name of the group to get or create.
     * @return The existing or newly created group.
     * @throws IllegalStateException If trying to create or join a new group while already in another group.
     */
    public static Group getOrCreateGroup(String groupName) {

        // Check if user is accessing a group they are already in
        if (currentGroupName != null && currentGroupName.equals(groupName)) {
            System.out.println("You are in " + groupName);
            return groups.get(groupName);
        }

        // Use of Optional to handle non-existing groups in hashmap
        Optional<Group> optionalGroup = Optional.ofNullable(groups.get(groupName));

        // Tracker for existing group
        final boolean[] isNewGroupCreated = {false};

        // If the user is in a different group, prevent them from creating or joining a new group.
        Group group = optionalGroup.orElseGet(() -> {
            if (currentGroupName != null && !currentGroupName.equals(groupName)) {
                throw new IllegalStateException("Please exit the current group '" + currentGroupName
                            + "' to create or join another group.");
            }
            Group newGroup = new Group(groupName);
            groups.put(groupName, newGroup);
            System.out.println(groupName + " created.");
            isNewGroupCreated[0] = true;
            return newGroup;
        });

        // If a new group was created, update currentGroupName to reflect this.
        if (isNewGroupCreated[0]) {
            currentGroupName = groupName;
        }

        System.out.println("You are now in " + groupName);
        return group;
    }

    public Group parseAddMember(String argument) {
        if (currentGroupName == null) {
            throw new IllegalStateException("Please create or join a group first.");
        }
        String[] tokens = argument.split(" ");
        if (tokens.length == 0) {
            throw new IllegalArgumentException("Please enter a name for the member.");
        }
        String memberName = tokens[0];
        User newMember = new User(memberName);
        users.add(newMember);
        System.out.println(memberName + " has been added to " + currentGroupName + ".");
        return this;
    }

    public static void exitGroup() {
        if (currentGroupName != null) {
            System.out.println("You have exited " + currentGroupName + ".");
            currentGroupName = null;
        } else {
            System.out.println("Please try again.");
        }
    }
}
