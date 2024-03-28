package seedu.duke;

import expenditure.ExpenditureList;
import storage.Storage;
import gpa.GPACommand;
import gc.GC;

public class ProcessCommand {

    public ProcessCommand() {
    }

    public void processUserCommand(String command) {
        if(command.startsWith("gpa")){
            GPACommand.processGPACommand();
        }

        if (!command.startsWith("e/")) {
            System.out.println("Invalid command. Commands related to expenditures should start with 'e/'.");
            System.out.println("Commands related to gpa calculation should start with 'gpa'.");
            return;
        }

        String[] commandParts = command.substring(2).trim().split("/", 2);
        if (commandParts.length < 2) {
            System.out.println("Incomplete command!");
            return;
        }

        String action = commandParts[0].trim();
        String actionDetails = commandParts[1].trim();

        switch (action) {
        case "add":
            ExpenditureList.addExpenditure(actionDetails,true);
            break;
        case "del":
            try {
                int index = Integer.parseInt(actionDetails);
                ExpenditureList.deleteExpenditure(index);
            } catch (NumberFormatException e) {
                System.out.println("Invalid index format for deletion.");
            }
            break;

        default:
            System.out.println("Unknown function: " + action);
            break;
        }
    }

    public boolean userCommand(String input, ExpenditureList expenses) {
        assert input != null;
        input = input.trim().toLowerCase();
        switch (input) {
        case "gpa":
            GPACommand.processGPACommand();
            UI.printHelpMessage();
            break;
        case "gc":
            GC.main();
            break;
        case "exit":
            System.out.println("Shutting down... Goodbye!!");
            Storage.writeToFile(expenses);
            return true;
        case "list":
            ExpenditureList.listExpenses();
            break;
        case "clearlist":
            ExpenditureList.clearlist();
            break;
        case "help":
            UI.printHelpMessage();
            break;
        default:
            if (input.startsWith("e/")) {
                processUserCommand(input);
            } else if (input.startsWith("view -m ")) {
                String monthYear = input.length() > "view -m ".length() ?
                        input.substring("view -m ".length()).trim() : "";
                if (!monthYear.isEmpty()) {
                    ExpenditureList.listExpensesByMonth(monthYear);
                } else {
                    System.out.println("Please provide a month and year in MM.YYYY format after 'view -m'.");
                }
            } else if (input.startsWith("view -y ")) {
                String year = input.length() > "view -y ".length() ?
                        input.substring("view -y ".length()).trim() : "";
                if (!year.isEmpty()) {
                    ExpenditureList.listExpensesByYear(year);
                } else {
                    System.out.println("Please provide a year in YYYY format after 'view -y'.");
                }
            } else {
                System.out.println("Unknown command. Please try again! Type 'help' for more information!");
            }
            break;
        }
        return false;
    }


}
