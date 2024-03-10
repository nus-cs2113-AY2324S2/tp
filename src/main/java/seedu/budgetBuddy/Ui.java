package seedu.budgetBuddy;

import java.util.Scanner;

public class Ui {
    private Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public String readCommand() {
        return scanner.nextLine().trim();
    }

    public void closeScanner() {
        scanner.close();
    }

    public void greet() {
        String Logo = 
            "    ____  ____  \n" +
            "   | __ )| __ ) \n" +
            "   |  _ \\|  _ \\ \n" +
            "   | |_) | |_) |\n" +
            "   |____/|____/ \n";
        System.out.println("Welcome to BudgetBuddy!\n" + Logo);
        System.out.println("What can I do for you?");
        showLine();
    }

    public void showLine() {
        System.out.println("____________________________________________________________");
    }

    public void showError(String message){
        System.out.println("an error occurred: " + message);
    }

    public void showGoodBye(){
        System.out.println("Bye. Hope to see you again soon!");
    }

    public void showList(String list) {
        showLine();
        System.out.println(list);
        showLine();
    }

    public void showAdd(String addedItem) {
        showLine();
        System.out.println("Got it. I've added this transaction:");
        System.out.println(addedItem);
        showLine();
    }

    public void showDelete(String deletedItem) {
        showLine();
        System.out.println("Noted. I've removed this transaction:");
        System.out.println(deletedItem);
        showLine();
    }
}