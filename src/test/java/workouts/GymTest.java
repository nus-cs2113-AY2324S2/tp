package workouts;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;


class GymTest {

    @BeforeEach
    void setUp() {

    }

    @AfterEach
    void cleanup() {
        WorkoutList.clearWorkoutsRunGym();
    }

    /**
     * Tests the behavior of adding a new station to the gym.
     * Verifies whether the newly added station is correctly reflected in {@Code GymSet}.
     * Expected Behaviour is to add stations and sets to the gym.
     */
    @Test
    void addStation_validInput_expectAddedStation() {
        Gym newGym = new Gym();
        try{
            newGym.addStation("Exercise 1", 10, 1, 10);
            newGym.addStation("Exercise 2", 20, 2, 20);
            assertEquals(2, newGym.getStations().size());

            newGym.addStation("Exercise 3", 30, 3, 30);
            ArrayList<GymStation> stations = newGym.getStations();
            assertEquals(3, stations.size());

            for(int i = 0; i < stations.size(); i++){
                String stationName = stations.get(i).getStationName();
                ArrayList<GymSet> sets = stations.get(i).getSets();
                int numberOfSets = sets.size();

                if (i == 0){
                    assertEquals("Exercise 1", stationName);
                    assertEquals(1, numberOfSets );
                    assertEquals(10, sets.get(0).getWeight());
                    assertEquals(10, sets.get(0).getRepetitions());

                } else if (i == 1){
                    assertEquals("Exercise 2", stationName);
                    assertEquals(2, numberOfSets );
                    assertEquals(20, sets.get(0).getWeight());
                    assertEquals(20, sets.get(0).getRepetitions());

                } else if (i == 2){
                    assertEquals("Exercise 3", stationName);
                    assertEquals(3, numberOfSets );
                    assertEquals(30, sets.get(0).getWeight());
                    assertEquals(30, sets.get(0).getRepetitions());
                }
            }

        } catch (Exception e) {
            fail("Should not throw an exception");
        }
    }


    @Test
    void getStations() {
    }

    @Test
    void getStationByIndex() {
    }

    @Test
    void addGymStationInput() {
    }

    @Test
    void checkGymStationInput() {
    }
}
