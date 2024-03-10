package seedu.budgetBuddy;

public class Parser {

    public void parseInput(String input) {
        if (input.startsWith("add expense")) {
            String[] parts = input.split(" ", 2);
            parseExpense(parts);
        } else if (input.startsWith("add saving")) {
            String[] parts = input.split(" ", 2);
            parseSaving(parts);
        } else {
            System.out.println("Invalid input");
        }
    }

    private void parseExpense(String[] parts) {
        try {
            String[] categoryParts = parts[1].split(" /c ");
            String[] amountParts = categoryParts[1].split(" /a ");
            String[] descriptionParts = amountParts[1].split(" /d ");

            String category = categoryParts[1].trim();
            String amount = amountParts[1].trim();
            String description = descriptionParts[1].trim();
            TaskManager.addExpense(category, amount, description);
        } catch (Exception e) {
            System.out.println("Error parsing expense. Ensure the format is correct.");
        }
    }

    private void parseSaving(String[] parts) {
        try {
            String[] categoryParts = parts[1].split(" /c ");
            String[] amountParts = categoryParts[1].split(" /a ");

            String category = categoryParts[1].trim();
            String amount = amountParts[1].trim();
            TaskManager.addSaving(category, amount);
        } catch (Exception e) {
            System.out.println("Error parsing saving. Ensure the format is correct.");
        }
    }
}
