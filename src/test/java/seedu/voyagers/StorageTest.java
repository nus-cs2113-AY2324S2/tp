package seedu.voyagers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class StorageTest {

    @Test
    public void testReadTripFile() {
        ArrayList<Trip> trips = new ArrayList<>();
        String tempFilePath = "temp_test_file.txt";

        // Create a temporary file with sample trip data
        try {
            FileWriter writer = new FileWriter(tempFilePath);
            writer.write("Trip1|2024-03-15|2024-03-20|Location1|Description1\n");
            writer.write("Trip2|2024-03-25|2024-03-30|Location2|Description2\n");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Call readTripFile method
        Storage.readTripFile(trips, "");

        // Assert that the trips list contains expected trips
        Assertions.assertEquals(2, trips.size());
        Assertions.assertEquals("Trip1", trips.get(0).getName());
        Assertions.assertEquals("Location1", trips.get(0).getLocation());
        Assertions.assertEquals("Description2", trips.get(1).getDescription());

        // Delete the temporary file
        File tempFile = new File(tempFilePath);
        tempFile.delete();
    }

    @Test
    public void testWriteTripFile() {
        ArrayList<Trip> trips = new ArrayList<>();
        String tempFilePath = "temp_test_file.txt";

        // Populate the trips list with sample trips
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date startDate1 = dateFormat.parse("2024-03-15");
            Date endDate1 = dateFormat.parse("2024-03-20");
            Date startDate2 = dateFormat.parse("2024-03-25");
            Date endDate2 = dateFormat.parse("2024-03-30");
            Trip trip1 = new Trip("Trip1", startDate1, endDate1, "Location1", "Description1");
            Trip trip2 = new Trip("Trip2", startDate2, endDate2, "Location2", "Description2");
            trips.add(trip1);
            trips.add(trip2);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        // Call writeTripFile method
        Storage.writeTripFile(trips, trips.size(), tempFilePath);

        // Read the content of the temporary file and assert its correctness
        try {
            File tempFile = new File(tempFilePath);
            Scanner scanner = new Scanner(tempFile);
            Assertions.assertTrue(scanner.hasNextLine());
            Assertions.assertEquals("Trip1|2024-03-15|2024-03-20|Location1|Description1", scanner.nextLine());
            Assertions.assertTrue(scanner.hasNextLine());
            Assertions.assertEquals("Trip2|2024-03-25|2024-03-30|Location2|Description2", scanner.nextLine());
            scanner.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Delete the temporary file
        File tempFile = new File(tempFilePath);
        tempFile.delete();
    }
}
