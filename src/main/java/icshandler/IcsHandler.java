package icshandler;

import net.fortuna.ical4j.data.CalendarOutputter;
import net.fortuna.ical4j.model.Calendar;
import net.fortuna.ical4j.model.DateTime;
import net.fortuna.ical4j.model.component.VEvent;
import net.fortuna.ical4j.model.property.ProdId;
import net.fortuna.ical4j.model.property.Uid;
import net.fortuna.ical4j.util.RandomUidGenerator;
import net.fortuna.ical4j.util.UidGenerator;
import net.fortuna.ical4j.validate.ValidationException;
import net.fortuna.ical4j.model.property.Version;
import net.fortuna.ical4j.model.property.CalScale;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.GregorianCalendar;

public class IcsHandler {

    private static final String filePath = "./src/main/java/icshandler/icstest.ics";

    public static void generateICS() throws IOException, ValidationException {
        // Start Date is on: April 1, 2023, 9:00 am
        java.util.Calendar startDate = new GregorianCalendar();
        startDate.set(java.util.Calendar.MONTH, java.util.Calendar.APRIL);
        startDate.set(java.util.Calendar.DAY_OF_MONTH, 1);
        startDate.set(java.util.Calendar.YEAR, 2023);
        startDate.set(java.util.Calendar.HOUR_OF_DAY, 9);
        startDate.set(java.util.Calendar.MINUTE, 0);
        startDate.set(java.util.Calendar.SECOND, 0);

        // End Date is on: April 1, 2023, 13:00
        java.util.Calendar endDate = new GregorianCalendar();
        endDate.set(java.util.Calendar.MONTH, java.util.Calendar.APRIL);
        endDate.set(java.util.Calendar.DAY_OF_MONTH, 1);
        endDate.set(java.util.Calendar.YEAR, 2023);
        endDate.set(java.util.Calendar.HOUR_OF_DAY, 13);
        endDate.set(java.util.Calendar.MINUTE, 0);
        endDate.set(java.util.Calendar.SECOND, 0);

        // Create the event
        DateTime start = new DateTime(startDate.getTime());
        DateTime end = new DateTime(endDate.getTime());
        VEvent meeting = new VEvent(start, end, "Team Meeting");

        // Generate a UID for the event
        UidGenerator ug = new RandomUidGenerator();
        Uid uid = ug.generateUid();
        meeting.getProperties().add(uid);

        // Add the event and print
        Calendar calendar = new Calendar();
        calendar.getProperties().add(new ProdId("CLINTON CALENDAR"));
        calendar.getProperties().add(Version.VERSION_2_0);
        calendar.getProperties().add(CalScale.GREGORIAN);
        calendar.getComponents().add(meeting);

        // Save to an ICS file
        try (FileOutputStream fout = new FileOutputStream(filePath)) {
            CalendarOutputter outputter = new CalendarOutputter();
            outputter.output(calendar, fout);
        }
    }
}
