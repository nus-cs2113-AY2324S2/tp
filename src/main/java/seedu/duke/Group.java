package seedu.duke;

import java.util.ArrayList;

public class Group {
    protected String name;
    protected ArrayList<Member> members;
    protected ArrayList<Expense> expenses;

    public Group(String name, ArrayList<Member> members, ArrayList<Expense> expenses) {
        this.name = name;
        this.members = members;
        this.expenses = expenses;
    }
}
