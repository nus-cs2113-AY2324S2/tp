package seedu.duke;

import java.util.Scanner;

public class Duke {
    /**
     * Main entry-point for the java.duke.Duke application.
     */
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("What is your name?");

        Scanner in = new Scanner(System.in);
        System.out.println("Hello " + in.nextLine());
        String userInput;
        while(true){
            userInput = in.nextLine();
            if(userInput.equals("bye")){
                break;
            }
            if(userInput.startsWith("expense")){
                String[] removeKeyWord = userInput.split(" ",2);
                String[] extractExpense = removeKeyWord[1].split(" ", 2);
                String expense = extractExpense[0];
                int amount = Integer.parseInt(extractExpense[1]);
                ExpenseAdder newExpense = new ExpenseAdder(expense,amount);
            }
            System.out.println();
        }
    }
}
