package seedu.voyagers;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import seedu.voyagers.Parser;

public class Voyagers {

    public static void main(String[] args) {
        System.out.println("Hello from Voyagers!");

        //Initialise
        ArrayList<Trip> tripArrayList = new ArrayList<Trip>();
        Parser parser = new Parser(tripArrayList);
        Scanner scanner = new Scanner(System.in);

        //Start managing tripList
        System.out.println("Please enter your command:");
        scanner.nextLine(); // Consume the newline character
        while (scanner.hasNextLine()) {
            String command = scanner.nextLine();
            if (command.equalsIgnoreCase("exit")) {
                break;
            }
            parser.parseInput(command);
        }
    }
}
