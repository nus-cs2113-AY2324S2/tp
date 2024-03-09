package seedu.budgetbuddy;

import java.util.Scanner;

public class BudgetBuddy {
    private Parser parser;

    private ExpenseList expenseList;
    private SavingList savingList;


    public BudgetBuddy() {
        parser = new Parser();

        expenseList = new ExpenseList();
        savingList = new SavingList();

        expenseList.addExpense(new Expense("Transport", 50, "Bus Fare"));

        expenseList.addExpense(new Expense("Food", 30, "Lunch"));

        savingList.addSaving(new Saving("Salary", 2000, "Monthly Salary"));

        savingList.addSaving(new Saving("Investments", 200, "Stocks"));
    }

    public void handleCommands(String input) {
        Command command = parser.parseCommand(input, expenseList, savingList);

        if (command != null) {
            command.execute();
        } else {
            System.out.println("Invalid Command");
        }
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);

        while(true) {
            System.out.println("Enter your command: ");
            String input = scanner.nextLine();

            if (input.equalsIgnoreCase("exit")) {
                break;
            }
            handleCommands(input);

        }
        scanner.close();
    }

    public static void main(String[] args) {
        new BudgetBuddy().run();
    }
}
