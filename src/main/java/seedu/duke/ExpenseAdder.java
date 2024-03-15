package seedu.duke;

public class ExpenseAdder {
    private String name;
    private int amount;
    ExpenseAdder(String name, int amount){
        this.name = name;
        this.amount = amount;
        System.out.printf("Added new expense %d owed by %s",amount,name);
    }

}
