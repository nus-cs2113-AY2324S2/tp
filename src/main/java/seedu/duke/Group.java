package seedu.duke;
import java.util.HashMap;
import java.util.ArrayList;

public class Group {
    public static final HashMap<String, Group> groups = new HashMap<>();
    protected String groupName;
    protected ArrayList<User> users;
    protected ArrayList<Expense> expenses;

    public Group(String groupName) {
        this.groupName = groupName;
        this.users = new ArrayList<>();
        this.expenses = new ArrayList<>();
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public String getGroupName() {
        return groupName;
    }

    public ArrayList<Expense> getExpenses() { return expenses; }

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
                    throw new Exception("User already exists in group");
                }
            }
            users.add(user);
            System.out.println("Added " + user.getName() + " to " + groupName);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void addExpense(Expense expense){
        expenses.add(expense);
    }

    public void printExpenses() {
        for(Expense expense : expenses){
            System.out.println(expense);
        }
    }
}
