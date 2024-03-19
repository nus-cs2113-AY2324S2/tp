package seedu.duke;

import java.util.ArrayList;
import java.util.List;

public class ExpenditureList {
    public static int expenditureCount;
    private static ArrayList<Expenditure> expenditureList;

    public ExpenditureList() {
        expenditureList = new ArrayList<>();
        expenditureCount = 0;
    }

    public static void listExpensesByMonth(String monthYear) {
        if (!monthYear.matches("\\d{2}\\.\\d{4}")) {
            System.out.println("Month and year format incorrect! Please use MM.yyyy format.");
            return;
        }

        String[] monthYearParts = monthYear.split("\\.");
        String targetMonth = monthYearParts[0];
        String targetYear = monthYearParts[1];
        List<Expenditure> filteredExpenses = new ArrayList<>();

        for (Expenditure exp : expenditureList) {
            String[] dateParts = exp.getDate().split("\\.");
            String expenseMonth = dateParts[1];
            String expenseYear = dateParts[2];

            if (expenseMonth.equals(targetMonth) && expenseYear.equals(targetYear)) {
                filteredExpenses.add(exp);
            }
        }

        if (filteredExpenses.isEmpty()) {
            System.out.println("No expenses found for " + monthYear);
        } else {
            System.out.println("Expenses for the month & year " + monthYear + ":");
            int count = 1;
            for (Expenditure exp : filteredExpenses) {
                System.out.println(count + ". " + exp);
            }
        }
    }

    public static void listExpensesByYear(String year) {
        List<Expenditure> filteredExpenses = new ArrayList<>();

        if (!year.matches("\\d{4}")) {
            System.out.println("Year format incorrect. Please use yyyy format.");
            return;
        }

        for (Expenditure exp : expenditureList) {
            String[] dateParts = exp.getDate().split("\\.");
            String expenseYear = dateParts[2];

            if (expenseYear.equals(year)) {
                filteredExpenses.add(exp);
            }
        }

        if (filteredExpenses.isEmpty()) {
            System.out.println("No expenses found for year " + year);
        } else {
            System.out.println("Expenses for the year " + year + ":");
            int count = 1;
            for (Expenditure exp : filteredExpenses) {
                System.out.println(count + ". " + exp);
            }
        }
    }

    public static void addExpenditure(String expenditure) {
        String[] parts = expenditure.split("d/", 2);
        if (parts.length < 2) {
            System.out.println("Invalid input format for description.");
            return;
        }
        // Description part directly after "d/"
        String descriptionPart = parts[1].trim();

        parts = descriptionPart.split(" amt/", 2);
        if (parts.length < 2) {
            System.out.println("Invalid input format for amount.");
            return;
        }
        String description = parts[0].trim();
        String amountAndDate = parts[1].trim();

        parts = amountAndDate.split(" date/", 2);
        if (parts.length < 2) {
            System.out.println("Invalid input format for date.");
            return;
        }
        String amount = parts[0].trim();
        String date = parts[1].trim();

        try {
            float amountValue = Float.parseFloat(amount);
            // Ensure that the expenditureList is initialized somewhere before this
            expenditureList.add(new Expenditure(description, amountValue, date));
            expenditureCount += 1;
            System.out.println("Expenditure added successfully.");
        } catch (NumberFormatException e) {
            System.out.println("Invalid amount format!");
        }
    }

    public static void deleteExpenditure(int index) {
        Expenditure expenditure = expenditureList.get(index - 1);
        System.out.println("deleted:" + expenditure.getDescription() +
                " | Cost: $" + expenditure.getAmount() +
                " | date: " + expenditure.getDate());
        expenditureList.remove(index - 1);
        expenditureCount--;
    }

    public static void clearlist(){
        for (int num = 0 ;num<expenditureList.size();num++) {
            System.out.println("I have removed everything in the list");
            expenditureList.remove(num);
            expenditureCount --;
        }
    }

    public static void listExpenses() {
        if (expenditureList.isEmpty()) {
            System.out.println("No expenses to display.");
            return;
        }

        System.out.println("Current Expenses:");
        for (int i = 0; i < expenditureList.size(); i++) {
            Expenditure expenditure = expenditureList.get(i);
            System.out.println((i + 1) + ". " + expenditure.getDescription() +
                    " | Cost: $" + expenditure.getAmount() +
                    " | date: " + expenditure.getDate());
        }
    }

    public Expenditure getExpenditure(int index) {
        return expenditureList.get(index);
    }

}

