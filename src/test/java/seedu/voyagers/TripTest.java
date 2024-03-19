package seedu.voyagers;

import org.junit.jupiter.api.Test;
import seedu.voyagers.classes.Trip;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertFalse;



import java.util.Date;

public class TripTest {


    @Test
    public void testGetDuration() {
        Trip trip = new Trip("Test Trip",
                new Date(2020, 1, 1),
                new Date(2020, 1, 3),
                "Test Location",
                "Test Description");
        assertEquals(trip.getDuration(), 2);
    }
    @Test
    public void testValidDates() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Trip("Test Trip",
                    new Date(2020, 2, 1),
                    new Date(2020, 1, 3),
                    "Test Location",
                    "Test Description");
        });

        assertDoesNotThrow(() -> {
            new Trip("Test Trip",
                    new Date(2020, 1, 1),
                    new Date(2020, 2, 3),
                    "Test Location",
                    "Test Description");
        });
    }
    @Test
    public void testAddSubTrip() {
        Trip trip = new Trip("Test Trip",
                new Date(2020, 1, 1),
                new Date(2020, 1, 3),
                "Test Location",
                "Test Description");
        Trip subTrip = new Trip("Sub Trip",
                new Date(2020, 1, 1),
                new Date(2020, 1, 3),
                "Test Location",
                "Test Description");
        trip.addSubTrip(subTrip);
        assertTrue(trip.getSubTrips().contains(subTrip));
    }
    @Test
    public void testRemoveSubTrip(){
        Trip trip = new Trip("Test Trip",
                new Date(2020, 1, 1),
                new Date(2020, 1, 3),
                "Test Location",
                "Test Description");
        Trip subTrip = new Trip("Sub Trip",
                new Date(2020, 1, 1),
                new Date(2020, 1, 3),
                "Test Location",
                "Test Description");
        Trip subTrip2 = new Trip("Sub Trip 2",
                new Date(2020, 1, 1),
                new Date(2020, 1, 3),
                "Test Location",
                "Test Description");
        trip.addSubTrip(subTrip);
        trip.addSubTrip(subTrip2);
        trip.removeSubTrip(subTrip);
        assertFalse(trip.getSubTrips().contains(subTrip));

        // There is only one subTrip left
        assertThrows(IndexOutOfBoundsException.class, () -> {
            trip.removeSubTrip(2);
        });

    }
}
