package brokeculator.event;

import java.util.ArrayList;

import brokeculator.expense.Expense;

public class Event {

    private String eventName;
    private String eventDescription;
    private ArrayList<Expense> expenses;

    public Event(String eventName, String eventDescription) {
        this.eventName = eventName;
        this.eventDescription = eventDescription;
        this.expenses = new ArrayList<>();
    }

    public boolean containsExpense(Expense expense) {
        return expenses.contains(expense);
    }
    public void addExpense(Expense expense) {
        if (containsExpense(expense)) {
            return;
        }
        expenses.add(expense);
    }
    public boolean hasExpenses() {
        return expenses.size() > 0;
    }
    public void removeExpense(Expense expense) {
        expenses.remove(expense);
    }

    @Override
    public String toString() {
        return "Event Name: " + eventName
                + " Event Description: " + eventDescription 
                + System.lineSeparator();
    }

    public String listExpenses() {
        StringBuilder sb = new StringBuilder();
        for (Expense expense : expenses) {
            sb.append(expense).append(System.lineSeparator());
        }
        return sb.toString();
    }

}
