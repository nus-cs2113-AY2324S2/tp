package seedu.budgetbuddy.command;

import seedu.budgetbuddy.Budget;
import seedu.budgetbuddy.Expense;
import seedu.budgetbuddy.ExpenseList;

public class ListBudgetCommand extends Command{
    private ExpenseList expenseList;
    public ListBudgetCommand(ExpenseList expenseList){
        this.expenseList = expenseList;
    }

    @Override
    public void execute() {
        // Print all budgets
        System.out.println("All budgets:");
        if (expenseList.getBudgets().isEmpty()) {
            System.out.println("No budgets set.");
        } else {
            expenseList.getBudgets().forEach(budget ->
                    System.out.println(budget.getCategory() + " - $" + budget.getBudget())
            );
        }

        System.out.println("\nCategories above budget:");
        boolean found = false;

        for (String category : expenseList.getCategories()) {
            double totalSpent = expenseList.getExpenses().stream()
                    .filter(expense -> expense.getCategory().equalsIgnoreCase(category))
                    .mapToDouble(Expense::getAmount)
                    .sum();

            Budget budgetForCategory = expenseList.getBudgets().stream()
                    .filter(budget -> budget.getCategory().equalsIgnoreCase(category))
                    .findFirst()
                    .orElse(null);

            if (budgetForCategory != null && totalSpent > budgetForCategory.getBudget()) {
                double exceededBy = totalSpent - budgetForCategory.getBudget();
                System.out.println(category + " - Budget: $" + budgetForCategory.getBudget()
                        + ", Spent: $" + totalSpent + ", Exceeded by: $" + exceededBy);
                found = true;
            }
        }

        if (!found) {
            System.out.println("No categories are above budget.");
        }
    }
}
