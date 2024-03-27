package florizz.core;

import florizz.objects.Bouquet;
import florizz.objects.Flower;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Ui {
    Scanner inputScanner = new Scanner (System.in);

    /**
     * Prints the introductory message.
     */
    public void printIntroMessage(){
        String logo = "\n" +
                "   __ _            _\n" +
                "  / _| |          (_)\n" +
                " | |_| | ___  _ __ _ ________\n" +
                " |  _| |/ _ \\| '__| |_  /_  /\n" +
                " | | | | (_) | |  | |/ / / /\n" +
                " |_| |_|\\___/|_|  |_/___/___|\n" +
                "\n";
        System.out.println("Hello from\n" + logo);
    }

    /**
     * Prints a break line.
     */
    public void printBreakLine(){
        System.out.println(("____________________________________________________________"));
    }

    /**
     * Gets input from the user typing commands into the CLI.
     * @return Returns the user input as one String.
     */
    public String getInput(){
        System.out.println("What can I do for you?");
        return inputScanner.nextLine();
    }

    /**
     * Prints the message indicating a new bouquet has been added.
     * @param bouquetAdded The bouquet that has been added.
     */
    public void printBouquetAdded(Bouquet bouquetAdded){
        System.out.println("Added new bouquet to list: \n" + bouquetAdded);
        printBreakLine();

    }

    /**
     * Prints the message indicating a bouquet has been deleted.
     * @param bouquetDeleted The bouquet that has been deleted.
     */
    public void printBouquetDeleted(Bouquet bouquetDeleted){
        System.out.println("Deleted bouquet: \n" + bouquetDeleted);
        printBreakLine();

    }

    /**
     * Prints all saved bouquets with their flowers and total estimated price.
     * @param bouquetList The list of saved bouquets.
     */
    public void printAllBouquets(ArrayList<Bouquet> bouquetList){
        System.out.println("Here are the list of your saved bouquets:");
        int i = 1;
        int totalPrice = 0;
        for (Bouquet bouquet : bouquetList){
            System.out.println(i++ + ". " + bouquet + " :");
            HashMap<Flower, Integer> flowerHashMap = bouquet.getFlowerHashMap();
            if (!flowerHashMap.isEmpty()) {
                for (Flower j : flowerHashMap.keySet()) {
                    System.out.println("    - " + flowerHashMap.get(j) + " x " + j.getFlowerName());
                    totalPrice += flowerHashMap.get(j);
                }
                System.out.println("  Total estimated price = $" + totalPrice);
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
        System.out.println("1. new <bouquetName> - Add a bouquet");
        System.out.println("2. delete <bouquetName> - Delete a bouquets");
        System.out.println("3. mybouquets - List current saved bouquets");
        System.out.println("4. info <flowerName> - Provide information on chosen flower");
        System.out.println("5. add <flowerName> /q <quantity> /to <bouquetName> - add flower to a bouquet.");
        System.out.println("6. remove <flowerName> /q <quantity> /from <bouquetName> - remove flower from a bouquet.");
        System.out.println("7. flowers - Shows a list of flowers that can be added into mybouquets");
        System.out.println("8. flowers <occasion> - Shows a list of flowers associated with said occasion");
        System.out.println("9. occasion - Shows a list of occasions associated with available flowers.");
        System.out.println("10. bye - Exits the programme");
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
            System.out.println(" - " + FlowerDictionary.get(i).getNameAndColour());
        }
        printBreakLine();
    }

    /**
     * Prints filtered flowers based on a given filter.
     * @param flowers The list of flowers to filter.
     * @param filter The filter to apply to the flowers.
     */
    public void printFilteredFlowers(ArrayList<Flower> flowers, String filter){
        System.out.println("Here are all the flowers related to " + filter + ": ");
        for (Flower flower : flowers){
            System.out.println("- " + flower.getNameAndColour());
        }
    }

    /**
     * Prints name, colour, occasion and price information about a specific flower.
     * @param targetFlower The name of the flower to retrieve information for.
     * @return true if the flower information is found and printed successfully, otherwise false.
     */
    public boolean printFlowerInfo(String targetFlower) {
        for (int i = 0; i < FlowerDictionary.size(); i++) {
            if (FlowerDictionary.get(i).getFlowerName().equalsIgnoreCase(targetFlower)) {
                System.out.println(FlowerDictionary.get(i));
                printBreakLine();
                return true;
            }
        }
        return false;
    }
  
    /**
     * print all occasions available
     */
    public void printAllOccasions() {
        System.out.println("Here are all the occasions associated with the available flowers: ");
        for (Flower.Occasion occasion : Flower.Occasion.values()){
            System.out.println("- " + Flower.occasionToString(occasion));
        }

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
    public void printAddFlowerSuccess(ArrayList<Bouquet> bouquetList,
                                      String flowerName, Integer quantity, String bouquetName) {
        System.out.println("You have successfully added the following: " + System.lineSeparator() +
                           "    - " + quantity + " x " + flowerName + " -> Bouquet: " + bouquetName);
        printAllBouquets(bouquetList);
    }

    /**
     * print ui if flower removed successfully
     *
     * @param bouquetList
     * @param flowerName
     * @param quantity
     * @param bouquetName
     */
    public void printRemoveFlowerSuccess(ArrayList<Bouquet> bouquetList,
                                         String flowerName, Integer quantity, String bouquetName) {
        System.out.println("You have successfully removed the following: " + System.lineSeparator() +
                           "    - " + quantity + " x " + flowerName + " -> Bouquet: " + bouquetName);
        printAllBouquets(bouquetList);
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
    }

    /**
     * print if IOException is caught in a try catch block
     */
    public void printIOError() {
        System.out.println("ERROR: IO Error Encountered Xd");
    }
}
