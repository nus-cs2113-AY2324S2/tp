package seedu.duke;

import seedu.duke.ui.UI;

import java.util.ArrayList;

public class UserList {
    private static ArrayList<User> allUsers;
    private static User activeUser;

    public UserList() {
        allUsers = new ArrayList<>();
    }

    public static User GetActiveUser() {
        return activeUser;
    }

    public static void SetActiveUser(User user) {
        if (user != null) {
            UserList.activeUser = user;
        } else {
            System.out.println("User does not exist!");
        }
    }

    public static void AddUser(User user) {
        allUsers.add(user);
        if (allUsers.size() == 1) {
            UserList.activeUser = allUsers.get(0); //If this was the first user added, set them as the current user
            UI.printActiveUser(user.getName());
        }
    }

    public static void ListAll() {
        for (User user : allUsers) {
            System.out.println(user.getName());
        }
    }
    public static ArrayList<User> GetUsers() {
        return allUsers;
    }

    public static User FindUser(String name) {
        for (User u : allUsers) {
            if (name.toLowerCase().equals(u.getName().toLowerCase())) {
                return u;
            }
        }
        return null;
    }
}
