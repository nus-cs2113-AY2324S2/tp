package seedu.budgetbuddy;

import java.util.ArrayList;
import java.util.Arrays;

public class SavingList {
    protected ArrayList <Saving> savings;
    protected ArrayList<String> categories;
    private double initialAmount;

    public SavingList() {
        this.savings = new ArrayList<>();
        this.categories = new ArrayList<>(Arrays.asList("Salary", 
        "Investments", "Gifts", "Others"));
        this.initialAmount = 0;
    }

    public void findTotalSavings() {
        double totalSavings = 0;
        for (int i = 0; i < savings.size(); i++) {
            Saving saving = savings.get(i);
            totalSavings = totalSavings + saving.getAmount();
        }

        this.initialAmount = totalSavings;
    }


    public void listSavings(String filterCategory, ExpenseList expenseList) {
        findTotalSavings();
        System.out.println("Savings:");
        for (int i = 0; i < savings.size(); i++) {
            Saving saving = savings.get(i);
            if (filterCategory == null || saving.getCategory().equalsIgnoreCase(filterCategory)) {
                System.out.print(i + 1 + " | ");
                System.out.print("Category: " + saving.getCategory() + " | ");
                System.out.print("Amount: $" + saving.getAmount() + " | ");
            }
        }
        System.out.println("------------------------------------------------------------------------");
        System.out.println("Initial Savings Amount: $" + initialAmount);
        System.out.println("Expenses Deducted: ");

        double totalExpenses = 0;
        for (Expense expense : expenseList.getExpenses()) {
            totalExpenses += expense.getAmount();
            System.out.println("$" + expense.getAmount() + " spent on " + expense.getDescription() +
                    " on " + expense.getDateAdded());
        }
        System.out.println("------------------------------------------------------------------------");

        double remainingAmount = calculateRemainingSavings(initialAmount, totalExpenses);
        System.out.println("Remaining Amount: $" + remainingAmount);

    }

    public double calculateRemainingSavings(double initialAmount, double totalExpenses) {
        double remainingAmount = initialAmount - totalExpenses;
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

    public void addSaving(String category, String amount) {
        int amountInt = Integer.parseInt(amount);
        Saving saving = new Saving(category, amountInt);
        savings.add(saving);

        if (!categories.contains(category)) {
            categories.add(category);
        }
    }

    public void editSaving(String category, int index, double amount) {
        int categoryIndex = categories.indexOf(category);
        if (categoryIndex != -1 && index > 0 && index <= savings.size()) {
            Saving savingToEdit = savings.get(index - 1);
            savingToEdit.setCategory(category);
            savingToEdit.setAmount(amount);
            System.out.println("Saving edited successfully.");
        } else {
            System.out.println("Invalid category or index.");
        }
    }

    public void reduceSavings(int index, double amount){
        if (index >= 0 && index < savings.size()){
            Saving saving = savings.get(index);
            if(saving.getAmount() >= amount){
                saving.setAmount(saving.getAmount() - amount);
            } else {
                System.out.println("Insufficient savings amount.");
            }
        } else {
            System.out.println("Invalid saving index.");
        }
    }
}
