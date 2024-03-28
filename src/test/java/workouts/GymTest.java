package workouts;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

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
            ArrayList<Integer> array1 = new ArrayList<>(Arrays.asList(1));
            ArrayList<Integer> array2 = new ArrayList<>(Arrays.asList(1,2));
            ArrayList<Integer> array3 = new ArrayList<>(Arrays.asList(1,2,3));

            newGym.addStation("Exercise 1", array1, 1, 10);
            newGym.addStation("Exercise 2", array2, 2, 20);
            assertEquals(2, newGym.getStations().size());

            newGym.addStation("Exercise 3", array3, 3, 30);
            ArrayList<GymStation> stations = newGym.getStations();
            assertEquals(3, stations.size());

            for(int i = 0; i < stations.size(); i++){
                String stationName = stations.get(i).getStationName();
                ArrayList<GymSet> sets = stations.get(i).getSets();
                int numberOfSets = sets.size();

                if (i == 0){
                    assertEquals("Exercise 1", stationName);
                    assertEquals(1, numberOfSets );
                    for(int j = 0; j < sets.size(); j++){
                        assertEquals(array1.get(j), sets.get(j).getWeight());
                        assertEquals(10, sets.get(j).getRepetitions());
                    }


                } else if (i == 1){
                    assertEquals("Exercise 2", stationName);
                    assertEquals(2, numberOfSets );
                    for(int j = 0; j < sets.size(); j++){
                        assertEquals(array2.get(j), sets.get(j).getWeight());
                        assertEquals(20, sets.get(j).getRepetitions());
                    }

                } else if (i == 2){
                    assertEquals("Exercise 3", stationName);
                    assertEquals(3, numberOfSets );
                    for(int j = 0; j < sets.size(); j++){
                        assertEquals(array3.get(j), sets.get(j).getWeight());
                        assertEquals(30, sets.get(j).getRepetitions());
                    }
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
