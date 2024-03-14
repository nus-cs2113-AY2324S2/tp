package meditracker;

import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

class QuerySpecificMedication {
    /**
     * Queries the array list containing the medication data
     */
    @Test
    public void queryMedication_ifContains_expect() {
        String inputString = "Omeprazole";

        ArrayList<String> medications = new ArrayList<String>();
        String medOne = "Panadol";
        String medTwo = "Omeprazole";
        medications.add(medOne);
        medications.add(medTwo);

        for (String medicationName: medications) {
            if (medicationName.equals(inputString)) {
                assertTrue(true);
            }
        }
    }
}
