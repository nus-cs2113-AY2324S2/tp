package seedu.duke;

import java.util.ArrayList;

public class UserList {
    private int listLength;
    private ArrayList<User> allUsers;
    private User activeUser;
    public UserList() {
        listLength = 0;
        allUsers = new ArrayList<User>();
    }
    public int getListLength() {
        return listLength;
    }
    public void addUser(User user) {
        allUsers.add(user);
        listLength += 1;
        if (listLength == 1) {
            this.activeUser = allUsers.get(0);
        }
    }
    public void setActiveUser(int index) {
        this.activeUser = allUsers.get(index);
    }
    public User getActiveUser() {
        return activeUser;
    }
    public void listAll() {
        for (User user : allUsers) {
            System.out.println(user.getName());
        }
    }
    public ArrayList<User> getUsers() {
        return allUsers;
    }
}
