package seedu.duke;

import java.util.ArrayList;

public class Group {
    String name;
    protected ArrayList<User> users;

    public Group(String name) {
        this.name = name;
        this.users = new ArrayList<>();
    }

    public void addUsers(User user) {
        users.add(user);
        System.out.println("User " + user.getName() + " added to group " + this.name);
    }
}
