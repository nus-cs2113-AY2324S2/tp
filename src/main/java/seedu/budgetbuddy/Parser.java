package seedu.budgetbuddy;


import seedu.budgetbuddy.command.*;

public class Parser {

    private String extractDetailsForAdd(String details, String prefix) {
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

    public Boolean isExitCommand(String input) {
        return input.startsWith("bye");
    }

    public Boolean isAddExpenseCommand(String input) {
        return input.startsWith("add expense");
    }

    public Boolean isAddSavingCommand(String input) {
        return input.startsWith("add savings");
    }

    public Boolean isEditExpenseCommand(String input) {
        return input.startsWith("edit expense");
    }

    public Boolean isEditSavingCommand(String input) {
        return input.startsWith("edit savings");
    }

    public Boolean isDeleteExpenseCommand(String input){
        return input.startsWith("delete");
    }

    public Boolean isReduceSavingCommand(String input) {
        return input.startsWith("reduce");
    }


    public Command handleAddExpenseCommand(ExpenseList expenses, String input) {
        String[] parts = input.split(" ", 2);
        String details = parts[1];
        try {
            String category = extractDetailsForAdd(details, "/c");
            System.out.println(category);
            String amount = extractDetailsForAdd(details, "/a");
            System.out.println(amount);
            String description = extractDetailsForAdd(details, "/d");
            System.out.println(description);
            return new AddExpenseCommand(expenses,category, amount, description);
        } catch (Exception e) {
            System.out.println("Error parsing expense. Ensure the format is correct.");
        }

        return null;

    }

    public Command handleAddSavingCommand(SavingList savings, String input) {
        String[] parts = input.split(" ", 2);
        String details = parts[1];

        try {
            String category = extractDetailsForAdd(details, "/c");
            String amount = extractDetailsForAdd(details, "/a");
            return new AddSavingCommand(savings, category, amount);
        } catch (Exception e) {
            System.out.println("Error parsing saving. Ensure the format is correct.");
        }

        return null;
    }

    public Command handleEditExpenseCommand(ExpenseList expenses, String input) {
        String[] parts = input.split(" ");
        String category = null;
        int index = -1;
        double amount = -1;
        String description = null;

        for (String part : parts) {
            if (part.startsWith("c/")) {
                category = part.substring(2);
            } else if (part.startsWith("i/")) {
                try {
                    index = Integer.parseInt(part.substring(2));
                } catch (NumberFormatException e) {
                    // Handle invalid index format
                    return null;
                }
            } else if (part.startsWith("a/")) {
                try {
                    amount = Double.parseDouble(part.substring(2));
                } catch (NumberFormatException e) {
                    // Handle invalid amount format
                    return null;
                }
            } else if (part.startsWith("d/")) {
                description = part.substring(2);
            }
        }

        // Validate required fields
        if (category != null && index != -1 && amount != -1 && description != null) {
            System.out.println(category);
            System.out.println(index);
            System.out.println(amount);
            System.out.println(description);
            return new EditExpenseCommand(expenses, category, index, amount, description);
        } else {
            // Handle incomplete command
            return null;
        }
    }

    public Command handleEditSavingCommand(SavingList savings, String input) {
        String[] parts = input.split(" ");
        String category = null;
        int index = -1;
        double amount = -1;

        for (String part : parts) {
            if (part.startsWith("c/")) {
                category = part.substring(2);
            } else if (part.startsWith("i/")) {
                try {
                    index = Integer.parseInt(part.substring(2));
                } catch (NumberFormatException e) {
                    // Handle invalid index format
                    return null;
                }
            } else if (part.startsWith("a/")) {
                try {
                    amount = Double.parseDouble(part.substring(2));
                } catch (NumberFormatException e) {
                    // Handle invalid amount format
                    return null;
                }
            }
        }

        // Validate required fields
        if (category != null && index != -1 && amount != -1) {
            return new EditSavingCommand(savings, category, index, amount);
        } else {
            // Handle incomplete command
            return null;
        }
    }

    public Command handleDeleteExpenseCommand(ExpenseList expenses, String input) {
        String[] parts = input.split("i/", 2);
        try {
            String indexAsString = parts[1].trim();
            System.out.println(indexAsString);
            int index = Integer.parseInt(indexAsString) - 1;
            return new DeleteExpenseCommand(expenses, index);
        } catch (NumberFormatException e) {
            return null;
        }

    }

    public Command handleReduceSavingCommand(SavingList savings, String input) {
        String description = input.replace("reduce", "").trim();

        if(description.contains("i/") && description.contains("a/")) {
            String[] parts  = description.split("i/|a/", 3);

            String indexToReduceAsString = parts[1].trim();
            String amountToReduceAsString = parts[2].trim();
            System.out.println(indexToReduceAsString);
            System.out.println(amountToReduceAsString);
            int indexToReduce = Integer.parseInt(indexToReduceAsString) - 1;

            double amountToReduce = Double.parseDouble(amountToReduceAsString);

            return new ReduceSavingCommand(savings, indexToReduce, amountToReduce);
        }

        return null;

    }
    public Command parseCommand(ExpenseList expenses, SavingList savings, String input) {

        if (isAddExpenseCommand(input)) {
            return handleAddExpenseCommand(expenses, input);
        }

        if (isAddSavingCommand(input)) {
            return handleAddSavingCommand(savings, input);
        }

        if (isEditExpenseCommand(input)) {
            return handleEditExpenseCommand(expenses, input);
        }

        if (isEditSavingCommand(input)) {
            return handleEditSavingCommand(savings, input);
        }


        if (isDeleteExpenseCommand(input)) {
            return handleDeleteExpenseCommand(expenses, input);
        }

        if (isReduceSavingCommand(input)) {
            return handleReduceSavingCommand(savings, input);
        }

        return null;
    }
}
