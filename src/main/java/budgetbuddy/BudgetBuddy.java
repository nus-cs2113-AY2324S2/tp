package budgetbuddy;

import budgetbuddy.transaction.TransactionList;
import budgetbuddy.ui.UserInterface;

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
        try{
            while (isRunning) {
                String input = in.nextLine();

                switch (input.split(" ")[0]) {
                case "bye":
                    UserInterface.printGoodBye();
                    isRunning = false;
                    break;
                case "list":
                    transactions.printTransactions();
                    break;
                case "delete":
                    transactions.removeTransaction(input);
                    break;
                case "add":
                    transactions.processTransaction(input);
                    break;
                default:
                    UserInterface.printNoCommandExists();
                }
            }
        } catch(IndexOutOfBoundsException e){
            UserInterface.printIndexOutOfBounds("Given index id is out of bound",
                    Integer.parseInt(e.getMessage()));
        } catch(Exception e){
            UserInterface.printUnknownError(e.getMessage());
        }
    }
}
