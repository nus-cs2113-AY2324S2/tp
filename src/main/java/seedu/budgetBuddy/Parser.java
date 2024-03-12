package seedu.budgetbuddy;

public class Parser {

    public void parseInput(String input) {
        if (input.startsWith("add expense")) {
            String[] parts = input.split(" ", 2);
            parseExpense(parts[1]);
        } else if (input.startsWith("add saving")) {
            String[] parts = input.split(" ", 2);
            parseSaving(parts[1]);
        } else {
            System.out.println("Invalid input");
        }
    }

    private void parseExpense(String details) {
        try {
            String category = extractDetail(details, "/c");
            String amount = extractDetail(details, "/a");
            String description = extractDetail(details, "/d");
            TaskManager.addExpense(category, amount, description);
        } catch (Exception e) {
            System.out.println("Error parsing expense. Ensure the format is correct.");
        }
    }

    private void parseSaving(String details) {
        try {
            String category = extractDetail(details, "/c");
            String amount = extractDetail(details, "/a");
            TaskManager.addSaving(category, amount);
        } catch (Exception e) {
            System.out.println("Error parsing saving. Ensure the format is correct.");
        }
    }

    private String extractDetail(String details, String prefix) {
        int startIndex = details.indexOf(prefix) + prefix.length();
        int endIndex = details.length();

        String[] nextPrefixes = { "/c", "/a", "/d" };
        for (String nextPrefix : nextPrefixes) {
            if (details.indexOf(nextPrefix, startIndex) != -1 && details.indexOf(nextPrefix, startIndex) < endIndex) {
                endIndex = details.indexOf(nextPrefix, startIndex);
            }
        }
        return details.substring(startIndex, endIndex).trim();
    }
}
