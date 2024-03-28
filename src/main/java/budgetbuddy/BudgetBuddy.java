package budgetbuddy;

import budgetbuddy.account.Account;

import budgetbuddy.exceptions.EmptyArgumentException;
import budgetbuddy.exceptions.InvalidAddTransactionSyntax;
import budgetbuddy.exceptions.InvalidIndexException;
import budgetbuddy.exceptions.InvalidTransactionTypeException;
import budgetbuddy.exceptions.InvalidEditTransactionData;
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
        Scanner in = UserInterface.in;

        TransactionList transactions = null;
        try {
            transactions = new TransactionList();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Account account = new Account();
        transactions.updateBalance(account);

        boolean isRunning = true;

        while (isRunning) {
            String input = in.nextLine();
            try {
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
                case "edit":
                    transactions.processEditTransaction(input, account);
                    break;
                default:
                    UserInterface.printNoCommandExists();
                }
                transactions.saveTransactionList();
            } catch (InvalidAddTransactionSyntax e) {
                UserInterface.printInvalidAddSyntax(e.getMessage());
            } catch (NumberFormatException e) {
                UserInterface.printNumberFormatError(e.getMessage());
            } catch (InvalidTransactionTypeException e) {
                UserInterface.printTransactionTypeError(e.getMessage());
            } catch (EmptyArgumentException e) {
                UserInterface.printEmptyArgumentError(e.getMessage());
            } catch (InvalidIndexException e) {
                UserInterface.printInvalidIndex("Given index id is out of bound",
                        Integer.parseInt(e.getMessage()));
            } catch (IndexOutOfBoundsException ignored){
                UserInterface.printInvalidInput("Please check your command syntax");
            } catch (InvalidEditTransactionData e){
                UserInterface.printInvalidInput(e.getMessage());
            } catch (Exception e) {
                UserInterface.printUnknownError(e.getMessage());
            }
        }

    }
}
