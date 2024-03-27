package brokeculator.enumerators;

import brokeculator.dashboard.Dashboard;
import brokeculator.exceptions.BrokeculatorException;
import brokeculator.expense.Expense;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Category {
    private static final int LIST_ALL_EXPENSES = -1;
    private static Dashboard dashboard;
    private static boolean isDashboardSet = false;
    private static Set<String> categories = new HashSet<>();
    public static void setDashboard(Dashboard dashboard) throws BrokeculatorException {
        if(!Category.isDashboardSet) {
            throw new BrokeculatorException("Dashboard has already been set");
        }
        Category.isDashboardSet = true;
        Category.dashboard = dashboard;
    }
    public static String addCategory(String category) {
        if (categories.contains(category)) {
            return "Category already exists";
        }
        categories.add(category);
        return "Category added: " + category;
    }
    public static ArrayList<String> getCategoryList() {
        return new ArrayList<>(categories);
    }
    public static boolean isValidCategory(String category) {
        return categories.contains(category);
    }
    public static String removeCategory(String category) {
        if (isCategoryUsed(category)) {
            return "Cannot remove category that is in use";
        }
        categories.remove(category);
        return "Category removed: " + category;
    }
    private static boolean isCategoryUsed(String category) {
        assert dashboard != null : "Dashboard should not be null";
        ArrayList<Expense> expenseList = dashboard.getExpenseManager().listExpenses(LIST_ALL_EXPENSES);
        for (Expense expense : expenseList) {
            if (expense.getCategory().equals(category)) {
                return true;
            }
        }
        return false;
    }
}
