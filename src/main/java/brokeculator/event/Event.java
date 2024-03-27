package brokeculator.event;

import java.util.Set;

import brokeculator.expense.Expense;

public class Event {

    private String eventName;
    private String eventDescription;
    private Set<Expense> expenses;

    public Event(String eventName, String eventDescription) {
        this.eventName = eventName;
        this.eventDescription = eventDescription;
        this.expenses = Set.of();
    }

    public void addExpense(Expense expense) {
        expenses.add(expense);
    }

    public void removeExpense(Expense expense) {
        expenses.remove(expense);
    }

    @Override
    public String toString() {
        return "Event Name: " + eventName + System.lineSeparator()
                + "Event Description: " + eventDescription + System.lineSeparator() 
                + listExpenses();
    }

    public String listExpenses() {
        int index = 1;
        StringBuilder sb = new StringBuilder();
        for (Expense expense : expenses) {
            sb.append(index).append(". ").append(expense.toString()).append(System.lineSeparator());
            index++;
        }
        return sb.toString();
    }
}
