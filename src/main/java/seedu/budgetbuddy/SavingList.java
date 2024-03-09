package seedu.budgetbuddy;

import java.util.ArrayList;
import java.util.List;

public class SavingList {

    protected List<Saving> savings;
    protected List<String> categories;
    private int initialAmount;

    public SavingList() {
        this.savings = new ArrayList<>();
        this.categories = new ArrayList<>();
        this.initialAmount = 0;

        // Initialize categories
        categories.add("Salary");
        categories.add("Investments");
        categories.add("Gifts");
        categories.add("Others");
    }

    // Methods to add, edit, delete savings

    public void listSavings(String filterCategory, ExpenseList expenseList) {
        System.out.println("Savings:");
        for (int i = 0; i < savings.size(); i++) {
            Saving saving = savings.get(i);
            if (filterCategory == null || saving.getCategory().equalsIgnoreCase(filterCategory)) {
                System.out.print(i+1 + " | ");
                System.out.print("Category: " + saving.getCategory() + " | ");
                System.out.print("Amount: $" + saving.getAmount() + " | ");
                System.out.println("Description: " + saving.getDescription() + " | ");
            }
        }
        System.out.println("------------------------------------------------------------------------");
        System.out.println("Initial Savings Amount: $" + initialAmount);
        System.out.println("Expenses Deducted: ");

        int totalExpenses = 0;
        for (Expense expense : expenseList.getExpenses()) {
            totalExpenses += expense.getAmount();
            System.out.println("$" + expense.getAmount() + " spent on " + expense.getDescription() +
                    " on " + expense.getDateAdded());
        }
        System.out.println("------------------------------------------------------------------------");

        int remainingAmount = calculateRemainingSavings(initialAmount, totalExpenses);
        System.out.println("Remaining Amount: $" + remainingAmount);

    }

    public int calculateRemainingSavings(int initialAmount, int totalExpenses) {
        int remainingAmount = initialAmount - totalExpenses;
        if (remainingAmount < 0) {
            try {
                throw new Exception("Insufficient Funds");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } else {
            return remainingAmount;
        }
    }

    public void addSaving(Saving saving) {
        savings.add(saving);
        initialAmount += saving.getAmount();
    }

}
