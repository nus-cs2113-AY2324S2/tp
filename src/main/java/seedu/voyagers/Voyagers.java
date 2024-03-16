package seedu.voyagers;
import java.util.ArrayList;
import java.util.Scanner;
import static seedu.voyagers.Storage.readTripFile;
import static seedu.voyagers.Storage.writeTripFile;

public class Voyagers {
    
    public static void main(String[] args) {
        welcomeMessage();
        new Voyagers().runTrip();
    }

    void runTrip () {

        //Initialise
        ArrayList<Trip> tripArrayList = new ArrayList<>();
        Parser parser = new Parser(tripArrayList);
        Scanner scanner = new Scanner(System.in);
        String currentDir = System.getProperty("user.dir");
        readTripFile(tripArrayList, currentDir);
        parser.listAll();

        //Start managing tripList
        System.out.println("Please enter your command:");
        while (scanner.hasNextLine()) {
            String command = scanner.nextLine();
            if (command.equalsIgnoreCase("exit")) {
                break;
            }
            parser.parseInput(command);
        }
        scanner.close();
        writeTripFile(tripArrayList, tripArrayList.size(), currentDir);
    }

    static void welcomeMessage() {
        System.out.println("Welcome to Voyagers!");
        System.out.println("What would you like to do today?");
    }
}
