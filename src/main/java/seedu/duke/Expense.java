package seedu.duke;

public class Expense {
    protected String name;
    protected float amount;
    protected Member payingMember;

    Expense(String name, int amount){
        this.name = name;
        this.amount = amount;
        System.out.printf("Added new expense %d owed by %s",amount,name);
    }

}
