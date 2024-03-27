package meditracker.ui;

import meditracker.DailyMedication;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

//Solution below adapted by https://stackoverflow.com/questions/58665761
class UiTest {
    private static final ByteArrayOutputStream output = new ByteArrayOutputStream();
    private static final PrintStream originalOut = System.out;

    @BeforeAll
    public static void setUpStream() {
        System.setOut(new PrintStream(output));
    }

    @AfterAll
    public static void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    void printMedsList_sizeOfMedicationList_expectPrintedList() {
        List<DailyMedication> medications = new ArrayList<>(1);

        Ui.printMedsList(medications);
        StringBuilder expectedOutput = new StringBuilder();

        for (DailyMedication dailyMedication : medications) {
            int numbering = medications.indexOf(dailyMedication) + 1;
            expectedOutput.append("\t").
                    append(numbering).append(". ").append(dailyMedication)
                    .append(System.lineSeparator());
        }
        assertEquals(expectedOutput.toString(), output.toString());
    }
}
