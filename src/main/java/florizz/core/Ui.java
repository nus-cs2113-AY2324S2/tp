package florizz.core;

import florizz.objects.Bouquet;

import java.util.ArrayList;
import java.util.Scanner;

public class Ui {
    public void printIntroMessage(){
        String logo = "\n" +
                "   __ _            _         \n" +
                "  / _| |          (_)        \n" +
                " | |_| | ___  _ __ _ ________\n" +
                " |  _| |/ _ \\| '__| |_  /_  /\n" +
                " | | | | (_) | |  | |/ / / / \n" +
                " |_| |_|\\___/|_|  |_/___/___|\n" +
                "                             \n" +
                "                             \n";
        System.out.println("Hello from\n" + logo);
    }

    public void printBreakLine(){
        System.out.println(("____________________________________________________________"));
    }

    /**
     * Gets input from the user typing commands into the CLI.
     * @return Returns the user input as one String.
     */
    public String getInput(){
        System.out.println("What can I do for you?");
        Scanner inputScanner = new Scanner(System.in);
        return inputScanner.nextLine();
    }

    public void printBouquetAdded(Bouquet bouquetAdded){
        System.out.println("Added new bouquet to list: \n" + bouquetAdded);
        printBreakLine();

    }
    public void printBouquetDeleted(Bouquet bouquetDeleted){
        System.out.println("Deleted bouquet: \n" + bouquetDeleted);
        printBreakLine();

    }

    public void printAllBouquets(ArrayList<Bouquet> bouquetList){
        System.out.println("Here are the list of your saved bouquets:");
        int i = 1;
        for (Bouquet bouquet : bouquetList){
            System.out.println(i++ + ". " + bouquet);
        }
        printBreakLine();
    }

    public void printHelpMessage() {
        System.out.println("Here are the list of commands you can use:");
        System.out.println("1. new <bouquet_name> - Add a bouquet");
        System.out.println("2. delete <bouquet_name> - Delete a bouquets");
        System.out.println("3. mybouquets - List current saved bouquets");
        System.out.println("4. bye - Exits the programme");
        printBreakLine();
    }

    public void printError(FlorizzException error){
        System.out.println(error.errorMessage);
        printBreakLine();
    }

    public void printExitMessage() {
        System.out.println("Enjoy your bouquet! Thank you for using Florizz");
        printBreakLine();
    }


}
