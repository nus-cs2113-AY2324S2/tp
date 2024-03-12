package seedu.duke;
import java.util.ArrayList;
class ExpenseList {
    protected ArrayList<Expense> expenses;
    protected ArrayList<String> categories;

    public ExpenseList() {
        this.expenses = new ArrayList<>();
        this.categories = new ArrayList<>();
        // Hardcode expense categories
        categories.add("Housing");
        categories.add("Groceries");
        categories.add("Utility");
        categories.add("Transport");
        categories.add("Entertainment");
        categories.add("Others");
    }

    // Method to edit expense
    public void editExpense(String category, int index, double amount, String description) {
        int categoryIndex = categories.indexOf(category);
        if (categoryIndex != -1 && index > 0 && index <= expenses.size()) {
            Expense expenseToEdit = expenses.get(index - 1);
            expenseToEdit.setCategory(category);
            expenseToEdit.setAmount(amount);
            expenseToEdit.setDescription(description);
            System.out.println("Expense edited successfully.");
        } else {
            System.out.println("Invalid category or index.");
        }
    }
}