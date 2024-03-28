package health;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import utility.CustomExceptions;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;


public class AppointmentTest {
    private static final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private static final PrintStream originalOut = System.out;

    @BeforeEach
    void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    void cleanup() {
        System.setOut(originalOut);
        HealthList.clearHealthLists();
        outContent.reset();
    }

    @Test
    void showAppointmentList_printCorrectAppointmentList() {
        Appointment firstAppointment = new Appointment("25-03-2024", "16:30", "Physiotherapy session");
        Appointment secondAppointment = new Appointment("22-03-2024", "16:00", "Wound dressing change");
        Appointment thirdAppointment = new Appointment("22-03-2024", "11:00", "Doctor consultation");

        HealthList.addAppointment(firstAppointment);
        HealthList.addAppointment(secondAppointment);
        HealthList.addAppointment(thirdAppointment);

        String expected = "1. On "
                + thirdAppointment.getDate()
                + " at "
                + thirdAppointment.getTime()
                + ": "
                + thirdAppointment.description
                + System.lineSeparator()
                + "2. On "
                + secondAppointment.getDate()
                + " at "
                + secondAppointment.getTime()
                + ": "
                + secondAppointment.description
                + System.lineSeparator()
                + "3. On "
                + firstAppointment.getDate()
                + " at "
                + firstAppointment.getTime()
                + ": "
                + firstAppointment.description
                + System.lineSeparator();

        HealthList.showAppointmentList();
        assertEquals(expected, outContent.toString());
    }

    @Test
    void deleteAppointment_deleteCorrectAppointment_printCorrectList() throws CustomExceptions.OutOfBounds {
        Appointment firstAppointment = new Appointment("25-03-2024", "16:30", "Physiotherapy session");
        Appointment secondAppointment = new Appointment("22-03-2024", "16:00", "Wound dressing change");
        Appointment thirdAppointment = new Appointment("22-03-2024", "11:00", "Doctor consultation");

        HealthList.addAppointment(firstAppointment);
        HealthList.addAppointment(secondAppointment);
        HealthList.addAppointment(thirdAppointment);

        String expected = "Removed appointment on "
                + secondAppointment.getDate()
                + " at "
                + secondAppointment.getTime()
                + ": "
                + secondAppointment.description
                + System.lineSeparator()
                + "1. On "
                + thirdAppointment.getDate()
                + " at "
                + thirdAppointment.getTime()
                + ": "
                + thirdAppointment.description
                + System.lineSeparator()
                + "2. On "
                + firstAppointment.getDate()
                + " at "
                + firstAppointment.getTime()
                + ": "
                + firstAppointment.description
                + System.lineSeparator();

        HealthList.deleteAppointment(2);

        assertEquals(expected, outContent.toString());
    }
}
