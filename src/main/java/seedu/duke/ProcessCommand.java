package seedu.duke;

import java.util.Scanner;

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
    public void userCommand() {
        while(true) {
            String command;
            Scanner in = new Scanner(System.in);
            command = in.nextLine().toLowerCase();
            String[] commandParts = command.split(" ", 2);

            switch (commandParts[0]) {
            case "exit":
                System.out.println("Shutting down...\n Goodbye.");
                return;
            case "help":
                System.out.println("CantVas Help \n"
                        + "To input expenses, use format:"
                        + "\n << e/ add/  d/ <description> amt/ <cost> date/ <dd.mm.yyyy> >>\n"
                        + "To List saved expenses, use format:\n << list >>  ");
                break;
            default:
                processUserCommand(command);
                break;
            }
        }
    }
}
