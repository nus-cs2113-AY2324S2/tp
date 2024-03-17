package seedu.duke;

import java.util.ArrayList;

class ExpenditureList {
    public static int expenditureCount;
    private static ArrayList<Expenditure> expenditureList;

    public ExpenditureList() {
        expenditureList = new ArrayList<>();
        expenditureCount = 0;
    }

    public static void addExpenditure(String expenditure) { // n/3.22 d/31.01.2024
        String description;
        String amount;
        String date;

        String[] descriptionParts = expenditure.split("amt/", 2);
        if (descriptionParts.length != 2) {
            System.out.println("Invalid input format");
            return;
        }

        description = descriptionParts[0].substring(2); // Removing the "d/" prefix
        String remainingPart = descriptionParts[1];

        String[] amountAndDateParts = remainingPart.split("date/", 2);
        if (amountAndDateParts.length != 2) {
            System.out.println("Invalid input format");
            return;
        }

        amount = amountAndDateParts[0].trim();
        date = amountAndDateParts[1].trim();

        try {
            float amountValue = Float.parseFloat(amount);
            expenditureList.add(new Expenditure(description, amountValue, date));
            expenditureCount += 1;
            System.out.println("Expenditure added successfully.");
        } catch (NumberFormatException e) {
            System.out.println("Invalid amount format");
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


    public static void handleCommand(String command) {
        String[] commandParts = command.split(" ", 2);
        String actionType = commandParts[0];

        switch (actionType) {
        case "add/":
            addExpenditure(commandParts[1]);
            break;
        default:
            System.out.println("Error, invalid input");
            break;
        }
    }
}

