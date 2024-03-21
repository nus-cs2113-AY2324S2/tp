package seedu.voyagers.utils;

import seedu.voyagers.classes.Trip;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Storage {

    private static Logger LOGGER = Logger.getLogger("Storage");


    /**
     * Reads the trip file and adds the trips to the list of trips.
     * @param trips The list of trips to add the trips to.
     * @param currentDir The current directory of the file.
     * @param fileName The name of the file to read from.
     */
    public static void readTripFile(ArrayList<Trip> trips, String currentDir, String fileName) {

        Logger logger = Logger.getLogger("Storage");
        //local path of data file
        File f = new File(currentDir + "/" + fileName);

        try {
            Scanner s = new Scanner(f);
            if (s.hasNext()) {
                System.out.println("Here are the trips in your list:");
            }
            while (s.hasNext()) {
                String[] inputs = s.nextLine().split("\\|", 5);
                assert inputs.length == 5 : "Invalid input format";
                java.text.SimpleDateFormat format = new java.text.SimpleDateFormat("yyyy-MM-dd");
                Date startDate = format.parse(inputs[1]);
                Date endDate = format.parse(inputs[2]);
                Trip trip = new Trip(inputs[0], startDate, endDate, inputs[3], inputs[4]);
                trips.add(trip);
            }
            s.close();
        } catch (FileNotFoundException e) {

            System.out.println("File not found.\nCreating new file...\nFile created.");
            try {
                assert f.createNewFile() : "File creation failed";
                f.createNewFile();
                logger.log(Level.INFO, "File created.");
            } catch (java.io.IOException ex) {
                System.out.println("An error occurred.");
                logger.log(Level.SEVERE, "An error occurred when creating the file.");
            }
        } catch (ParseException e) {
            System.out.println("An error occurred.");
        }
    }

    /**
     * Writes the trips to the trip file.
     * @param trips The list of trips to write to the file.
     * @param tripsCount The number of trips in the list.
     * @param currentDir The current directory of the file.
     */
    public static void writeTripFile(ArrayList<Trip> trips, int tripsCount, String currentDir) {
        //local path of data file
        File f = new File(currentDir + "/local-voyagers.txt");

        try (java.io.FileWriter writer = new java.io.FileWriter(f)) {
            for (int i = 0; i < tripsCount; i++) {
                Trip trip = trips.get(i);
                writer.write(trip.getName() + "|" + FormatDate.dateFormat.format(trip.getStartDate()) + "|" +
                        FormatDate.dateFormat.format(trip.getEndDate()) + "|"
                        + trip.getLocation() + "|" + trip.getDescription() + "\n");
            }
        } catch (IOException e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }

}
