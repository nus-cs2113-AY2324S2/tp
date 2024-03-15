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
        Ui ui = new Ui();
        List<DailyMedication> medications = new ArrayList<>(1);
        ui.printMedsList(medications, "Daily Medications");
        StringBuilder expectedOutput = new StringBuilder("Here are the Daily Medications you have to take today: " + System.lineSeparator());


        for (DailyMedication dailyMedication : medications) {
            int numbering = medications.indexOf(dailyMedication) + 1;
            expectedOutput.append("\t").
                    append(numbering).append(". ").append(dailyMedication)
                    .append(System.lineSeparator());
        }
        assertEquals(expectedOutput.toString(), output.toString());
    }
}
