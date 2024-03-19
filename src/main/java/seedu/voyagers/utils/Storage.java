package seedu.voyagers.utils;

import seedu.voyagers.classes.Trip;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Storage {
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

    public static void writeTripFile(ArrayList<Trip> trips, int tripsCount, String currentDir) {
        //local path of data file

        File f = new File(currentDir + "/local-voyagers.txt");

        try {
            java.io.FileWriter writer = new java.io.FileWriter(f);
            for (int i = 0; i < tripsCount; i++) {
                Trip trip = trips.get(i);
                writer.write(trip.getName() + "|" + trip.getStartDate() + "|" + trip.getEndDate() + "|"
                        + trip.getLocation() + "|" + trip.getDescription() + "\n");
            }
            writer.close();
        } catch (java.io.IOException e) {
            System.out.println("An error occurred.");
        }
    }
}
