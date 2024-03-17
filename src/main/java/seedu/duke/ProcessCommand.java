package seedu.duke;

import cantvasui.UI;

public class ProcessCommand {
    private final ExpenditureList expenselist;

    protected ProcessCommand(ExpenditureList expenselist) {
        this.expenselist = expenselist;
    }

    private void processUserCommand(String command) {
        String[] commandParts = command.split(" ", 2);
        String commandType = commandParts[0];

        switch(commandType) {
        case "list":
            ExpenditureList.listExpenses();
            break;
        case "e/":
            ExpenditureList.handleCommand(commandParts[1]);
            break;
        default:
            System.out.println("Invalid input...");
        }
    }

    /**
     * Handles basic commands inputted by user
     * if command is more complex or related to expenditure,
     * passes command to the method processUserCommand
     */
    public boolean userCommand(String input) {
        String command = input.toLowerCase();
        String[] commandParts = command.split(" ", 2);

        switch (commandParts[0]) {
        case "exit":
            System.out.println("Shutting down...\n Goodbye.");
            return true;
        case "help":
            UI.printHelpMessage();
            break;
        default:
            processUserCommand(command);
            break;
        }
        return false;
    }
}
