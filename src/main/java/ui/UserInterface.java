package ui;

import transaction.type.Transaction;

import java.time.LocalDate;
import java.util.ArrayList;

public class UserInterface {
  
    public static final int START_INDEX = 0;
    private static final String LINE = "-------------------------------------------" +
            "----------------------------------------------------------------------" +
            "-----------------------";
    private static final String TABLE_BORDER = "________________________________________" +
            "_____________________________________________________________________";

    private static final String TAB_SPACE = "    ";


    public static void printDeleteMessage(String transaction){
        System.out.println(LINE);
        System.out.println(TAB_SPACE + "Got it. I have removed the following transaction from the history");
        System.out.println( TAB_SPACE + transaction);
        System.out.println(LINE);
    }

    public static void printAddMessage(String transaction){
        String[] parts = transaction.split("\\|");
        System.out.println(LINE);
        System.out.println(TAB_SPACE + "Got it. I have added the following transaction \n");
        for (String part : parts) {
            System.out.println(TAB_SPACE + part.trim());
        }
        System.out.println(LINE);
    }

    public static void printIndexOutOfBounds(String message,int id){
        System.out.println(LINE);
        System.out.println(TAB_SPACE + message);
        System.out.println( TAB_SPACE + "Please use index within the range of: 0  to " + id);
        System.out.println(LINE);
    }

    public static void printUnknownError(String message){
        System.out.println(LINE);
        System.out.println(TAB_SPACE + "Unknown error occurred with message: " + message);
        System.out.println(LINE);
    }

    public static void printAllTransactions(ArrayList<Transaction> transactions) {
        int index = transactions.size();
        System.out.println(LINE);
        System.out.println(TAB_SPACE + "Your Transaction history:");
        System.out.println(TABLE_BORDER);
        System.out.printf(TAB_SPACE + "%-5s %-50s %-20s %-15s %-15s%n", "ID", "Transaction", "Date",
                "Amount", "Category");
        for (int i = START_INDEX; i < index; i++) {
            Transaction transaction = transactions.get(i);
            LocalDate date = transaction.getDate();
            System.out.printf(TAB_SPACE + "%-5d %-50.45s %-20s %-15f  %-15s%n", i + 1,
                    transaction.getDescription(), date,
                    transaction.getAmount(), transaction.getCategory());
        }
        System.out.println(TABLE_BORDER);
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
}
