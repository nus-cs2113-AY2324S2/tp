package seedu.duke;

import java.util.ArrayList;
import java.util.Scanner;

public class Ui {
    private static final String BOT_NAME = "Flirt and Fork";
    private static final String HORIZONTAL = "____________________________________________________________";
    private Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public void greetingMessage() {
        System.out.println(HORIZONTAL);
        System.out.println("Hungry for love? You've come to the right place.\n" +
                "Welcome to " + BOT_NAME + " - where Cupid meets the chef! \n\n" +
                "Send me 'help' if you're new!");
        System.out.println(HORIZONTAL);
    }

    public void exitMessage() {
        System.out.println("Love, like a good meal, is all about timing.\n" +
                "Keep your love simmering and your fork ready, " +
                "see you at the next course!");
    }

    public void helpMessage() {
        System.out.println("To generate a randomised date itinerary, simply send me the following: \n" +
        "'itinerary'\n\n" +
        "To add food to your favourites, simply send me the following: \n" +
        "'food' [space] '(name of eatery)' [space] '(location)' [space] '(price)'\n\n" +
        "To add an activity to your favourites, simply send me the following: \n" +
        "'activity' [space] '(name of activity)'[space] '(location)' [space] '(price)'\n\n" +
        "To find an entry from your favourites, simply send me the following: \n" +
        "'find' [space] '(keyword)'\n\n" +
        "To list out all your favourites, simply send me the following: \n" +
        "'favourites'\n\n" +
        "To exit the program, simply send me the following: \n" +
        "'exit'\n\n" +
        "LEGEND (prices):\n" +
        "C: Cheap\n" +
        "B: Budget\n" +
        "A: Affordable\n" +
        "P: Pricey\n" +
        "S: Special Ocassions Only\n\n" +
        "LEGEND (areas):\n" +
        "E: East\n" +
        "W: West\n" +
        "C: Central\n" +
        "S: South\n" +
        "NE: NorthEast\n" +
        "ACC: Accessible (found in multiple places around SG)"
        );
    }

    public void errorMessage(String message) {
        System.out.println(message);
    }

    public String readCommand() {
        return scanner.nextLine();
    }

    public void showFavourite(String favourite) {
        System.out.println(favourite);
    }

    public void listFavourites(FavouritesList favouritesList) {
        System.out.println("Drumroll, please! Presenting the stars of your romantic sky: ");
        System.out.println(favouritesList.getFormattedFavourites());
    }

    public void showMatchingFavourites(ArrayList<Favourites> matchingFavourites) {
        if (matchingFavourites.isEmpty()) {
            System.out.println("No treasures found this time. Let's try another clue?");
        } else {
            System.out.println("Eureka! Your cupid's arrow hit the target! We found these matches for you:");
            for (int i = 0; i < matchingFavourites.size(); i++) {
                System.out.println((i + 1) + ". " + matchingFavourites.get(i));
            }
        }
    }

}
