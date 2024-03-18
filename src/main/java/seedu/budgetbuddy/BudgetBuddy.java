package seedu.budgetbuddy;

import seedu.budgetbuddy.command.Command;

import java.util.Scanner;

import seedu.budgetbuddy.exception.BudgetBuddyException;

public class BudgetBuddy {

    private Ui ui;
    private Parser parser;
    private ExpenseList expenses;
    private SavingList savings;

    public BudgetBuddy() {
        ui = new Ui();
        parser = new Parser();
        expenses = new ExpenseList();
        savings = new SavingList();
    }

    public void handleCommands(String input) throws BudgetBuddyException {
        Command command = parser.parseCommand(expenses, savings, input);

        if (command != null) {
            command.execute();
        }
        else {
            throw new BudgetBuddyException("Invalid command");
        }
    }

    public void run() throws BudgetBuddyException {
        Scanner scanner = new Scanner(System.in);

        ui.showWelcome();

        boolean isExit = false;
        while (!isExit) {
            String input = scanner.nextLine();

            if (input.equals("bye")) {
                isExit = true;
            }
            else {
                handleCommands(input);
            }

        }

        ui.showGoodbye();
        scanner.close();
    }

    public static void main(String[] args) throws BudgetBuddyException {
        new BudgetBuddy().run();
    }
}
