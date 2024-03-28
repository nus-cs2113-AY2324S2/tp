package seedu.budgetbuddy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;


import seedu.budgetbuddy.exception.BudgetBuddyException;

public class SavingList {
    private static final Logger LOGGER = Logger.getLogger(SavingList.class.getName());

    protected ArrayList <Saving> savings;
    protected ArrayList<String> categories;
    protected double initialAmount;
    protected Storage storage;
    Ui ui = new Ui();



    public SavingList() {
        this.savings = new ArrayList<>();
        this.categories = new ArrayList<>(Arrays.asList("Salary",
                "Investments", "Gifts", "Others"));
        this.initialAmount = 0;
        this.storage = new Storage("src/main/java/seedu/budgetbuddy/data/SavingsFile.txt");
    }

    public int size() {
        return savings.size();
    }

    public ArrayList<Saving> getSavings() {
        return savings;
    }

    public void findTotalSavings() {
        try {
            assert savings != null : "Savings list should not be null";

            double totalSavings = 0;
            for (int i = 0; i < savings.size(); i++) {
                Saving saving = savings.get(i);
                assert saving != null : "Saving object at index " + i + " is null";
                totalSavings += saving.getAmount();
            }

            this.initialAmount = totalSavings;
        } catch (AssertionError e) {
            LOGGER.log(Level.SEVERE, "Error occurred while calculating total savings", e);
        }
    }

    public void listSavings(String filterCategory, ExpenseList expenseList) {
        try {
            LOGGER.info("Listing savings...");

            findTotalSavings();
            System.out.println("Savings:");
            for (int i = 0; i < savings.size(); i++) {
                Saving saving = savings.get(i);
                if (filterCategory == null || saving.getCategory().equalsIgnoreCase(filterCategory)) {
                    System.out.print(i + 1 + " | ");
                    System.out.print("Category: " + saving.getCategory() + " | ");
                    System.out.println("Amount: $" + String.format("%.2f", saving.getAmount()) + " | ");
                }
            }
            ui.printDivider();
            System.out.println("Initial Savings Amount: $" + String.format("%.2f", initialAmount));
            System.out.println("Expenses Deducted: ");

            double totalExpenses = 0;
            for (Expense expense : expenseList.getExpenses()) {
                totalExpenses += expense.getAmount();
                System.out.println("$" + String.format("%.2f", expense.getAmount()) +
                        " spent on " + expense.getDescription() +
                        " on " + expense.getDateAdded());
            }
            ui.printDivider();

            double remainingAmount = calculateRemainingSavings(initialAmount, totalExpenses);
            if (remainingAmount < 0) {
                System.out.println("You are currently short on savings by: $" + String.format("%.2f", remainingAmount));
            } else {
                System.out.println("Remaining Amount: $" + String.format("%.2f", remainingAmount));

            }
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "An error occurred while listing savings", e);
        }
    }

    public double calculateRemainingSavings(double initialAmount, double totalExpenses) {
        try {
            assert initialAmount >= 0 : "Initial amount should not be negative";
            assert totalExpenses >= 0 : "Total expenses should not be negative";

            return (initialAmount - totalExpenses);
        } catch (AssertionError e) {
            LOGGER.log(Level.SEVERE, "Assertion failed while calculating remaining savings", e);
        }
        return -1;
    }

    public void addSaving(String category, String amount) throws BudgetBuddyException{
        if (!categories.contains(category)) {
            throw new BudgetBuddyException("The category '" + category + "' is not listed.");
        }
        int amountInt = Integer.parseInt(amount);
        if (amountInt < 0) {
            try {
                throw new Exception("Savings should not be negative");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        Saving saving = new Saving(category, amountInt);
        savings.add(saving);

        if (!categories.contains(category)) {
            categories.add(category);
        }
    }

    public void editSaving(String category, int index, double amount) {
        LOGGER.info(String.format("Attempting to edit saving at index %d with category '%s' " +
                "and amount %.2f", index, category, amount));

        // Assert that the provided category is not null or empty
        assert category != null && !category.isEmpty() : "Category cannot be null or empty";

        // Assert that the index is within the valid bounds of the savings list
        assert index > 0 && index <= savings.size() : "Index is out of bounds";

        // Assert that the amount is non-negative
        assert amount >= 0 : "Amount cannot be negative";

        // Check if the category exists in the list of categories
        int categoryIndex = categories.indexOf(category);
        if (categoryIndex == -1) {
            LOGGER.warning("Invalid category: " + category);
            System.out.println("Invalid category.");
            return;
        }

        // Check if the index is within valid bounds
        if (index <= 0 || index > savings.size()) {
            LOGGER.warning(String.format("Invalid index: %d. Valid index range " +
                    "is 1 to %d.", index, savings.size()));
            System.out.println("Invalid index.");
            return;
        }

        Saving savingToEdit = null;
        try {
            // Retrieve the saving to edit
            savingToEdit = savings.get(index - 1);

            // Update the saving details
            savingToEdit.setCategory(category);
            savingToEdit.setAmount(amount);

            System.out.println("Saving edited successfully.");
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error occurred while editing saving at index " + index, e);
            System.out.println("An error occurred during saving edition. Please try again.");

        }
    }

    public void reduceSavings(int index, double amount){
        if (index >= 0 && index < savings.size()){
            Saving saving = savings.get(index);
            if(saving.getAmount() >= amount){
                saving.setAmount(saving.getAmount() - amount);
                System.out.println("Savings reduced successfully!");
            } else {
                System.out.println("Insufficient savings amount.");
            }
        } else {
            System.out.println("Invalid saving index.");
        }
    }
}
