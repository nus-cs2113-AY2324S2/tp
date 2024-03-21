package seedu.budgetbuddy;

import java.util.ArrayList;
import java.util.List;

import seedu.budgetbuddy.exception.BudgetBuddyException;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ExpenseList {
    private static final Logger LOGGER = Logger.getLogger(ExpenseList.class.getName());
    protected ArrayList <Expense> expenses;
    protected ArrayList<String> categories;
    public ExpenseList(ArrayList<Expense> expenses) {
        this.expenses = expenses;
        this.categories = new ArrayList<>(Arrays.asList("Housing",
                "Groceries", "Utility", "Transport", "Entertainment", "Others"));
    }

    public ExpenseList() {
        this.expenses = new ArrayList<>();
        this.categories = new ArrayList<>(Arrays.asList("Housing",
                "Groceries", "Utility", "Transport", "Entertainment", "Others"));
    }

    public int size() {
        return expenses.size();
    }

    public List<Expense> getExpenses() {
        return expenses;
    }

    public ArrayList<Expense> filterExpenses(String description, Double minAmount, Double maxAmount) {
        assert minAmount <= maxAmount : "Minimum Amount must be smaller than or equals to Max Amount";

        LOGGER.log(Level.INFO, "Start Filtering expenses based on description : " + " minAmount : "
                + minAmount + "maxAmount : " + maxAmount);

        String descriptionInLowerCase = description.toLowerCase();
        ArrayList<Expense> filteredExpenses = new ArrayList<>(this.expenses.stream()
                .filter(expense -> (expense.getDescription().toLowerCase().contains(descriptionInLowerCase)))
                .filter(expense -> (minAmount == null || expense.getAmount() > minAmount))
                .filter(expense -> (maxAmount == null || expense.getAmount() < maxAmount))
                .collect(Collectors.toList()));

        LOGGER.log(Level.INFO, "Ending filtering and returning filtered expenses");
        return filteredExpenses;

    }

    public void listExpenses(String filterCategory) {
        LOGGER.info("Listing expenses...");

        try {
            System.out.println("Expenses:");
            for (int i = 0; i < expenses.size(); i++) {
                Expense expense = expenses.get(i);

                // Checks for null expenses
                if (expense == null) {
                    LOGGER.warning("Expense object at index " + i + " is null");
                    continue;
                }

                if (filterCategory == null || expense.getCategory().equalsIgnoreCase(filterCategory)) {
                    System.out.print(i+1 + " | ");
                    System.out.print("Date: " + expense.getDateAdded() + " | ");
                    System.out.print("Category: " + expense.getCategory() + " | ");
                    System.out.print("Amount: $" + expense.getAmount() + " | ");
                    System.out.println("Description: " + expense.getDescription() + " | ");
                }
            }
            System.out.println("-----------------------------------------------------------------------------");
            System.out.println("Total Expenses: $" + calculateTotalExpenses());

            // Assertion: Check if total expenses calculation is correct
            double totalExpenses = calculateTotalExpenses();
            assert totalExpenses >= 0 : "Total expenses should be non-negative";
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "An error occurred while listing expenses.", e);
        }
    }

    public double calculateTotalExpenses() {
        double totalExpenses = 0;
        try {
            for (Expense expense: expenses) {
                if (expense.getAmount() < 0) {
                    throw new IllegalArgumentException("Expenses should not be negative");
                }
                totalExpenses += expense.getAmount();
            }
        } catch (IllegalArgumentException e) {
            LOGGER.log(Level.WARNING, "Negative expense amount detected", e);
        }

        // Assertion: Check if total expenses is non-negative
        assert totalExpenses >= 0 : "Total expenses should be non-negative";

        return totalExpenses;
    }

    public void addExpense(String category, String amount, String description) throws BudgetBuddyException {
        assert category != null : "Category should not be null";
        assert amount != null : "Amount should not be null";
        assert description != null : "Description should not be null";
        
        if (!categories.contains(category)) {
            throw new BudgetBuddyException("The category '" + category + "' is not listed.");
        }
        int amountInt;
        try {
            amountInt = Integer.parseInt(amount);
        } catch (NumberFormatException e) {
            throw new BudgetBuddyException("Invalid amount format. Amount should be a number.");
        }

        if (amountInt < 0) {
            throw new BudgetBuddyException("Expenses should not be negative.");
        }

        Expense expense = new Expense(category, amountInt, description);
        expenses.add(expense);
    }

    public void editExpense(String category, int index, double amount, String description) {
        // Assert that the provided category is not null or empty
        assert category != null && !category.isEmpty() : "Category cannot be null or empty";

        // Assert that the index is within the valid bounds of the expenses list
        assert index > 0 && index <= expenses.size() : "Index is out of bounds";

        // Assert that the amount is non-negative
        assert amount >= 0 : "Amount cannot be negative";
        
        // Assert that the description is not null.
        //(An empty description might be valid in some cases)
        assert description != null : "Description cannot be null";

        // Check if the category exists in the list of categories
        int categoryIndex = categories.indexOf(category);
        if (categoryIndex == -1) {
            System.out.println("Invalid category.");
            return;
        }

        // Check if the index is within valid bounds
        if (index <= 0 || index > expenses.size()) {
            System.out.println("Invalid index.");
            return;
        }

        // Retrieve the expense to edit
        Expense expenseToEdit = expenses.get(index - 1);

        // Update the expense details
        expenseToEdit.setCategory(category);
        expenseToEdit.setAmount(amount);
        expenseToEdit.setDescription(description);

        System.out.println("Expense edited successfully.");
    }


    public void deleteExpense(int index){
        if (index >= 0 && index < expenses.size()){
            expenses.remove(index);
            System.out.println("Expense deleted successfully!");
        } else {
            System.out.println("Invalid expense index.");
        }
    }
}
