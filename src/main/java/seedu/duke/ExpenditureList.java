package seedu.duke;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class ExpenditureList {
    public static int expenditureCount;
    private static ArrayList<Expenditure> expenditureList;

    public ExpenditureList() {
        expenditureList = new ArrayList<>();
        expenditureCount = 0;
    }

    protected static void listExpensesByMonth(String monthYear) {
        assert monthYear.length() == 7;
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

    protected static void listExpensesByYear(String year) {
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


    public static void addExpenditure(String expenditure, Boolean userAdded) {
        try {
            String[] parts = expenditure.split("d/", 2);
            if (parts.length < 2) {
                throw new InvalidInputFormatException("Invalid input format for description.");
            }
            // Description part directly after "d/"
            String descriptionPart = parts[1].trim();

            parts = descriptionPart.split(" amt/", 2);
            if (parts.length < 2) {
                throw new InvalidInputFormatException("Invalid input format for amount.");
            }
            String description = parts[0].trim();
            String amountAndDate = parts[1].trim();

            parts = amountAndDate.split(" date/", 2);
            if (parts.length < 2) {
                throw new InvalidInputFormatException("Invalid input format for date.");
            }
            String amount = parts[0].trim();
            String date = parts[1].trim();

            float amountValue = Float.parseFloat(amount);
            // Ensure that the expenditureList is initialized somewhere before thi
            if ( isValidDate(date) && isValidAmount(amountValue) ) {
                expenditureList.add(new Expenditure(description, amountValue, date));
                expenditureCount += 1;
                userAddedMessage(userAdded);
            }
        } catch (InvalidInputFormatException e) {
            System.out.println(e.getMessage());

        } catch (NumberFormatException e) {
            System.out.println("Invalid amount format!");
        }
    }

    private static void userAddedMessage(Boolean userAdded) {
        if (userAdded){
            System.out.println("Expenditure added successfully.");
        }
    }

    public static void deleteExpenditure(int index) {
        Expenditure expenditure = expenditureList.get(index - 1);
        System.out.println("deleted: " + expenditure.getDescription() +
                " | Cost: $" + expenditure.getAmount() +
                " | date: " + expenditure.getDate());
        expenditureList.remove(index - 1);
        expenditureCount--;
    }

    public static void clearlist() {
        if (expenditureList.isEmpty()) {
            System.out.println("The list is already empty!");
        } else {

            while (!expenditureList.isEmpty()) {
                expenditureList.remove(expenditureList.size() - 1); // Remove the last element
                expenditureCount--; // Decrement the count of expenditures
            }
            assert expenditureList.isEmpty();
            System.out.println("I have cleared the whole list!");
        }
    }

    public static void listExpenses() {
        if (expenditureList.isEmpty()) {
            System.out.println("No expenses to display.");
            return;
        }

        System.out.println("Current Expenses:");
        assert !expenditureList.isEmpty();
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

    protected static boolean isValidDate(String date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        dateFormat.setLenient(false);
        try {

            dateFormat.parse(date);
            return true;
        } catch (ParseException e) {
            System.out.println("Invalid date.");
            return false;
        }
    }

    protected static boolean isValidAmount(float amt) {
        if (amt >= 0) {
            float fractionalPart = amt - (int) amt;

            // Check if the fractional part has more than two decimal places
            if (Math.round(fractionalPart * 100) / 100f != fractionalPart) {
                System.out.println("Invalid amount format! Please ensure the amount has at most two decimal places.");
                return false;
            }
            return true;
        }
        System.out.println("Please enter a positive amount");
        return false;
    }


}

