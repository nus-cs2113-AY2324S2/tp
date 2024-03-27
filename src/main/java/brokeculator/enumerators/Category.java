package brokeculator.enumerators;

import brokeculator.dashboard.Dashboard;
import brokeculator.expense.Expense;
import brokeculator.storage.parsing.FileKeyword;
import brokeculator.storage.parsing.SaveableType;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Category {
    private static final int LIST_ALL_EXPENSES = -1;
    private static Dashboard dashboard;
    private static boolean isDashboardSet = false;
    private static Set<String> categories = new HashSet<>();
    public static void setDashboard(Dashboard dashboard) {
        assert !isDashboardSet : "Dashboard should not be set twice";
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
        if (!categories.contains(category)) {
            return "Category does not exist";
        }
        categories.remove(category);
        return "Category removed: " + category;
    }
    private static boolean isCategoryUsed(String category) {
        assert dashboard != null : "Dashboard should not be null";
        ArrayList<Expense> expenseList = dashboard.getExpenseManager().listExpenses(LIST_ALL_EXPENSES);
        for (Expense expense : expenseList) {
            if (expense.getCategory() == null) {
                continue;
            }
            if (expense.getCategory().equals(category)) {
                return true;
            }
        }
        return false;
    }
    public static String getCategoryListString() {
        StringBuilder sb = new StringBuilder();
        for (String category : categories) {
            sb.append("- ").append(category).append(System.lineSeparator());
        }
        return sb.toString();
    }
    public static String getCategoriesStringRepresentation() {
        StringBuilder sb = new StringBuilder();
        for (String category : categories) {
            String finalExpenseString = FileKeyword.formatWithKeyword(SaveableType.CATEGORY, category);
            sb.append(finalExpenseString);
            sb.append(System.lineSeparator());
        }
        return sb.toString();
    }
}
