package seedu.duke;

import seedu.duke.ui.UI;

import java.util.ArrayList;

public class UserList {
    private ArrayList<User> allUsers;
    private User activeUser;

    public UserList() {
        allUsers = new ArrayList<>();
    }

    public User getActiveUser() {
        return activeUser;
    }

    public void setActiveUser(User user) {
        if (user != null) {
            activeUser = user;
        } else {
            System.out.println("User does not exist!");
        }
    }

    public void addUser(User user) {
        allUsers.add(user);
        if (allUsers.size() == 1) {
            activeUser = allUsers.get(0); //If this was the first user added, set them as the current user
            UI.printActiveUser(user.getName());
        }
    }

    public void listAll() {
        for (User user : allUsers) {
            System.out.println(user.getName());
        }
    }

    public ArrayList<User> getUsers() {
        return allUsers;
    }

    public int getListLength() {
        return allUsers.size();
    }

    public User findUser(String name) {
        for (User u : allUsers) {
            if (name.equalsIgnoreCase(u.getName())) {
                return u;
            }
        }
        return null;
    }
}
