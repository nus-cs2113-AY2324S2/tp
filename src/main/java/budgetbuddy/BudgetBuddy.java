package budgetbuddy;

import budgetbuddy.account.Account;
import budgetbuddy.exception.EmptyArgumentException;
import budgetbuddy.exception.InvalidAddTransactionSyntax;
import budgetbuddy.exception.InvalidTransactionTypeException;
import budgetbuddy.transaction.TransactionList;
import budgetbuddy.ui.UserInterface;

import java.io.IOException;
import java.util.Scanner;

public class BudgetBuddy {

    /**
     * Main entry-point for the java.BudgetBuddy application.
     */
    public static void main(String[] args) {
        String logo = "BUDGET BUDDY";
        System.out.println("Hello from\n" + logo);
        System.out.println("What can I do for you?");
        Scanner in = new Scanner(System.in);

        TransactionList transactions = null;
        try {
            transactions = new TransactionList();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Account account = new Account();
        transactions.updateBalance(account);

        boolean isRunning = true;
        try {
            while (isRunning) {
                String input = in.nextLine();

                switch (input.split(" ")[0]) {
                case "bye":
                    UserInterface.printGoodBye();
                    isRunning = false;
                    break;
                case "list":
                    transactions.printTransactions(account);
                    break;
                case "delete":
                    transactions.removeTransaction(input, account);
                    break;
                case "add":
                    transactions.processTransaction(input, account);
                    break;
                default:
                    UserInterface.printNoCommandExists();
                }
                transactions.saveTransactionList();
            }
        } catch (InvalidAddTransactionSyntax e) {
            UserInterface.printInvalidAddSyntax(e.getMessage());
        } catch (NumberFormatException e) {
            UserInterface.printNumberFormatError(e.getMessage());
        } catch (InvalidTransactionTypeException e) {
            UserInterface.printTransactionTypeError(e.getMessage());
        } catch(EmptyArgumentException e) {
            UserInterface.printEmptyArgumentError(e.getMessage());
        } catch(IndexOutOfBoundsException e){
            UserInterface.printIndexOutOfBounds("Given index id is out of bound",
                    Integer.parseInt(e.getMessage()));
        } catch (Exception e) {
            UserInterface.printUnknownError(e.getMessage());
        }
    }
}
