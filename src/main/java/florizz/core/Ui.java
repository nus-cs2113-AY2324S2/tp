package florizz.core;

import florizz.objects.Bouquet;
import florizz.objects.Flower;

import java.util.ArrayList;
import java.util.HashMap;
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
            System.out.println(i++ + ". " + bouquet + " :");
            HashMap<Flower, Integer> flowerHashMap = bouquet.getFlowerHashMap();
            if (!flowerHashMap.isEmpty()) {
                for (Flower j : flowerHashMap.keySet()) {
                    System.out.println("    - " + flowerHashMap.get(j) + " x " + j.getFlowerName());
                }
            } else {
                System.out.println("      No flowers added so far");
            }
        }
        printBreakLine();
    }

    /**
     * print all available command
     */
    public void printHelpMessage() {
        System.out.println("Here are the list of commands you can use:");
        System.out.println("1. new <bouquet_name> - Add a bouquet");
        System.out.println("2. delete <bouquet_name> - Delete a bouquets");
        System.out.println("3. mybouquets - List current saved bouquets");
        System.out.println("4. bye - Exits the programme");
        System.out.println("5. flower - Shows a list of flowers that can be added into mybouquets");
        System.out.println("6. flower <occasion> - Shows a list of flowers associated with said occasion");
        printBreakLine();
    }

    /**
     * print error message thrown by Florizz Exception
     *
     * @param error
     */
    public void printError(FlorizzException error){
        System.out.println(error.errorMessage);
        printBreakLine();
    }

    /**
     * print exit message
     */
    public void printExitMessage() {
        System.out.println("Enjoy your bouquet! Thank you for using Florizz");
        printBreakLine();
    }

    /**
     * print flowers in the dictionary
     */
    public void printAllDictFlowerName() {
        System.out.println("Here are all the flowers you can add: ");
        for (int i = 0; i < FlowerDictionary.size(); i++) {
            System.out.println(FlowerDictionary.get(i).getFlowerName());
        }
        printBreakLine();
    }

    /**
     * print specific flowers for specific occasion inputted
     *
     * @param targetOccasion
     */
    public void printOccasionFlower(String targetOccasion) {
        System.out.println("Here are all the flowers related to " + targetOccasion.toLowerCase() + ": ");
        for (int i = 0; i < FlowerDictionary.size(); i++) {
            if (FlowerDictionary.get(i).getOccasion().equalsIgnoreCase(targetOccasion)) {
                System.out.println(FlowerDictionary.get(i).getFlowerName());
            }
        }
        printBreakLine();
    }

    /**
     * print all occasions available
     */
    public void printAllOccasion() {
        System.out.println("Here are all the occasions associated with the available flowers: ");
        OccasionDictionary.print();
        printBreakLine();
    }

    /**
     * print ui if flower added successfully
     *
     * @param bouquetList
     * @param flowerName
     * @param quantity
     * @param bouquetName
     */
    public void printAddFlowerSuccess(ArrayList<Bouquet> bouquetList, String flowerName, Integer quantity, String bouquetName) {
        System.out.println("You have successfully added the following: " + System.lineSeparator() +
                           "    - " + quantity + " x " + flowerName + " -> Bouquet: " + bouquetName);
        printAllBouquets(bouquetList);
        printBreakLine();
    }

    /**
     * print ui if flower removed successfully
     *
     * @param bouquetList
     * @param flowerName
     * @param quantity
     * @param bouquetName
     */
    public void printRemoveFlowerSuccess(ArrayList<Bouquet> bouquetList, String flowerName, Integer quantity, String bouquetName) {
        System.out.println("You have successfully added the following: " + System.lineSeparator() +
                           "    - " + quantity + " x " + flowerName + " -> Bouquet: " + bouquetName);
        printAllBouquets(bouquetList);
        printBreakLine();
    }

    /**
     * print ui if flower removed can't be found
     *
     * @param bouquetList
     * @param flowerName
     * @param bouquetName
     */
    public void printRemoveFlowerUnsuccessful(ArrayList<Bouquet> bouquetList, String flowerName, String bouquetName) {
        System.out.println(flowerName + " cannot be found in bouquet: " + bouquetName);
        printAllBouquets(bouquetList);
        printBreakLine();
    }
}
