package seedu.duke;
import java.util.HashMap;
import java.util.ArrayList;

public class Group {
    protected String groupName;
    protected ArrayList<User> users;
    public static final HashMap<String, Group> groups = new HashMap<>();

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

    public static Group getOrCreateGroup(String groupName) {
        Group group = groups.get(groupName);
        if (group == null) {
            group = new Group(groupName);
            groups.put(groupName, group);
            System.out.println("Created New Group: " + groupName);
        } else {
            System.out.println("Entering group: " + groupName);
        }
        return group;
    }

    public void addUsers(User user) {
        try {
            for (User u : users) {
                if (u.getName().equals(user.getName())) {
                    throw new Exception("User already exists in group.");
                }
            }
            users.add(user);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
