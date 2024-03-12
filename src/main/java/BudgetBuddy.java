import financemanager.TransactionList;

import java.util.Scanner;

public class BudgetBuddy {

    /**
     * Main entry-point for the java.BudgetBuddy application.
     */

    public static void main(String[] args){
        String logo = "BUDGET BUDDY";
        System.out.println("Hello from\n" + logo);
        System.out.println("What is your name?");

        Scanner in = new Scanner(System.in);
        System.out.println("Hello " + in.nextLine());

        TransactionList transactions = new TransactionList();
        boolean isRunning = true;

        while (isRunning) {
            String input = in.nextLine();

            switch (input.split(" ")[0]) {
            case "bye":
                System.out.println("Goodbye!");
                isRunning = false;
                break;
            case "list":
                System.out.println("Displaying transactions:");
                System.out.println(transactions.getTransactions());
                break;
            default:
                System.out.println("Invalid command.");
            }
        }



    }
}
