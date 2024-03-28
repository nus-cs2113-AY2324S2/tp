package budgetbuddy.ui;

import budgetbuddy.categories.Category;
import budgetbuddy.transaction.type.Transaction;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class UserInterface {
  
    public static final int START_INDEX = 0;
    private static final String LINE = "-------------------------------------------" +
            "----------------------------------------------------------------------" +
            "-----------------------";
    private static final String TABLE_BORDER = "________________________________________" +
            "_________________________________________________________________________________";

    private static final String TAB_SPACE = "    ";

    public static Scanner in = new Scanner(System.in);

    public static void listCategories(){
        System.out.println(LINE);
        System.out.println(TAB_SPACE + "Here are the available categories:");
        for(Category category : Category.values()) {
            System.out.println(TAB_SPACE + TAB_SPACE + category.getCategoryName() + ": " + category.getCategoryNum());
        }
        System.out.println(LINE);
    }

    public static int getCategoryNum(){
        System.out.println("In which category do you want to list this transaction? [Enter number between 1 and 9]");
        String input = in.nextLine();
        return Integer.parseInt(input);
    }


    public static void printDeleteMessage(String transaction, double balance){
        String[] parts = transaction.split("\\|");
        System.out.println(LINE);
        System.out.println(TAB_SPACE + "Got it. I have removed the following transaction from the history \n");
        for (String part : parts) {
            System.out.println(TAB_SPACE + part.trim());
        }
        System.out.println("\n" + TAB_SPACE + "Your updated account balance is $" + balance);
        System.out.println(LINE);
    }

    public static void printAddMessage(String transaction, double balance){
        String[] parts = transaction.split("\\|");
        System.out.println(LINE);
        System.out.println(TAB_SPACE + "Got it. I have added the following transaction \n");
        for (String part : parts) {
            System.out.println(TAB_SPACE + part.trim());
        }
        System.out.println("\n" + TAB_SPACE + "Your updated account balance is $" + balance);
        System.out.println(LINE);
    }

    public static void printInvalidIndex(String message, int id){
        System.out.println(LINE);
        System.out.println(TAB_SPACE + message);
        System.out.println( TAB_SPACE + "Please use index within the range of: 1  to " + id);
        System.out.println(LINE);
    }

    public static void printUnknownError(String message){
        System.out.println(LINE);
        System.out.println(TAB_SPACE + "Unknown error occurred with message: " + message);
        System.out.println(LINE);
    }

    public static void printInvalidInput(String message){
        System.out.println(LINE);
        System.out.println(TAB_SPACE + "Error occurred with message: " + message);
        System.out.println(LINE);
    }

    public static void printInvalidAddSyntax(String message) {
        System.out.println(LINE);
        System.out.println(TAB_SPACE + message);
        System.out.println(TAB_SPACE + "Please ensure that you have entered all the arguments correctly.");
        System.out.println(LINE);
    }

    public static void printTransactionTypeError(String message) {
        System.out.println(LINE);
        System.out.println(TAB_SPACE + "Invalid transaction type: " + message);
        System.out.println(TAB_SPACE + "Please enter Expense or Income only.");
        System.out.println(LINE);
    }

    public static void printNumberFormatError(String message) {
        System.out.println(LINE);
        System.out.println(TAB_SPACE + "Error occurred with the input: " + message);
        System.out.println(TAB_SPACE + "Please enter a valid value.");
        System.out.println(LINE);
    }

    public static void printEmptyArgumentError(String message){
        System.out.println(LINE);
        System.out.println(TAB_SPACE + "Please include the " + message + "in the command.");
        System.out.println(LINE);
    }

    public static void printAllTransactions(ArrayList<Transaction> transactions, double balance) {
        int index = transactions.size();
        System.out.println(LINE);
        System.out.println(TAB_SPACE + "Your Transaction history:");
        System.out.println(TAB_SPACE+TABLE_BORDER);
        System.out.printf(TAB_SPACE+TAB_SPACE + "%-5s %-10s %-50s %-20s %-15s %-15s%n", "ID", "Type", "Transaction",
                "Date", "Amount", "Category");
        for (int i = START_INDEX; i < index; i++) {
            Transaction transaction = transactions.get(i);
            String type = transaction.getTransactionType();
            String description = transaction.getDescription();
            LocalDate date = transaction.getDate();
            double amount = transaction.getAmount();
            String category = transaction.getCategory().getCategoryName();

            System.out.printf(TAB_SPACE+TAB_SPACE + "%-5d %-10s %-50.45s %-20s %-15.2f  %-15s%n", i + 1, type,
                    description, date, amount, category);
        }
        System.out.println(TAB_SPACE+TABLE_BORDER);
        System.out.println("\n" + TAB_SPACE + "Your currents account balance is $" + balance);
        System.out.println(LINE);
    }


    public static void printGoodBye(){
        System.out.println(LINE);
        System.out.println( TAB_SPACE +
                "Bye... Don't forget to keep track of your future transactions" );
        System.out.println(LINE);
    }

    public static void printNoCommandExists(){
        System.out.println(LINE);
        System.out.println( TAB_SPACE + "No such command exists." );
        System.out.println(LINE);
    }

    public static String getEditInformation(String string){
        System.out.println(LINE);
        System.out.println( TAB_SPACE + "Please edit the following transaction" );
        System.out.println(string);
        System.out.println(LINE);
        System.out.print( TAB_SPACE + "Enter transaction type: " );
        String type = in.next();
        System.out.print( TAB_SPACE + "Enter description: " );
        String description = in.next();
        System.out.print( TAB_SPACE + "Enter transaction date: " );
        String date = in.next();
        System.out.print( TAB_SPACE + "Enter transaction amount: " );
        String amount = in.next();
        System.out.println(" ");
        for(Category category : Category.values()) {
            System.out.println(TAB_SPACE + TAB_SPACE + category.getCategoryName() + ": " + category.getCategoryNum());
        }
        System.out.println("In which category do you want to list this transaction? [Enter number between 1 and 9]");
        System.out.print( TAB_SPACE + "Enter Category: " );
        String category = in.next();
        in.nextLine();
        return type + " | " + description + " | " + date + " | " + amount + " | " + category;

    }

    public static void printUpdatedTransaction(){
        System.out.println("\n" + TAB_SPACE + "Updated Successfully");
        System.out.println(LINE);
    }

}
