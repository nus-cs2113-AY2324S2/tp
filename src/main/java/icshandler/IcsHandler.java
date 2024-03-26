package icshandler;

import net.fortuna.ical4j.data.CalendarOutputter;
import net.fortuna.ical4j.model.Calendar;
import net.fortuna.ical4j.model.component.VEvent;
import net.fortuna.ical4j.model.property.ProdId;
import net.fortuna.ical4j.model.property.Uid;
import net.fortuna.ical4j.model.property.immutable.ImmutableCalScale;
import net.fortuna.ical4j.model.property.immutable.ImmutableVersion;

import java.io.FileOutputStream;
import java.io.IOException;

public class IcsHandler {

    public static void generateICS() throws IOException {
        Calendar calendar = new Calendar();

        calendar.add(new ProdId("-//Ben Fortuna//iCal4j 1.0//EN"));
        calendar.add(ImmutableVersion.VERSION_2_0);
        calendar.add(ImmutableCalScale.GREGORIAN);

        VEvent event = new VEvent();
        event.add(new Uid());
        calendar.add(event);

        try (FileOutputStream fout = new FileOutputStream("./src/main/java/testingmycalendar.ics")) {
            CalendarOutputter outputter = new CalendarOutputter();
            outputter.output(calendar, fout);
        }
    }
}
