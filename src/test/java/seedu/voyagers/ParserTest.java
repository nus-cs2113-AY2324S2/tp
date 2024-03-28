package seedu.voyagers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.voyagers.classes.Trip;
import seedu.voyagers.classes.TripList;
import seedu.voyagers.utils.Ui;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;
import seedu.voyagers.paser.NewParser;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {

    private static final String DEFAULT_START = "01 January 1900";
    private static final String DEFAULT_END = "01 January 2500";
    private final SimpleDateFormat printDateFormat = new SimpleDateFormat("dd MMMM yyyy", new Locale("en"));
    private TripList tripsList;
    private Ui ui;
    private Parser parser;

    @BeforeEach
    public void setUp() {
        tripsList = new TripList(new ArrayList<>());
        ui = new Ui();
        NewParser newParser = new NewParser();
    }

    @Test
    public void testAddMainTrip() {
        String input = "addmaintrip /n Trip1 with long name /start 2024-03-15 /end 2024-03-20 " +
                "/location Location1 /d This is the description";
        NewParser.parse(input).execute(tripsList, ui, null);

        assertEquals(1, tripsList.size());
        Trip addedTrip = tripsList.getTrip("Trip1 with long name");
        assertEquals("Trip1 with long name", addedTrip.getName());
        assertEquals("15 March 2024", printDateFormat.format(addedTrip.getStartDate()));
        assertEquals("20 March 2024", printDateFormat.format(addedTrip.getEndDate()));
        assertEquals("Location1", addedTrip.getLocation());
        assertEquals("This is the description", addedTrip.getDescription());
    }

    @Test
    public void testSetName() {
        String input = "addmaintrip /n TripName " +
                "/start 2024-03-15 /end 2024-03-20 /location Location1 /d This is the description";
        NewParser.parse(input).execute(tripsList, ui, null);

        assertEquals(1, tripsList.size());
        Trip addedTrip = tripsList.getTrip("TripName");
        assertEquals("TripName", addedTrip.getName());
        input = "setname TripName /n newName";
        NewParser.parse(input).execute(tripsList, ui, null);
        assertEquals("newName", addedTrip.getName());
    }

    @Test
    public void testSetLocation() {
        String input = "addmaintrip /n TripName " +
                "/start 2024-03-15 /end 2024-03-20 /location Location1 /d This is the description";
        NewParser.parse(input).execute(tripsList, ui, null);

        assertEquals(1, tripsList.size());
        Trip addedTrip = tripsList.getTrip("TripName");
        assertEquals("Location1", addedTrip.getLocation());
        input = "setlocation TripName /location newLocation";
        NewParser.parse(input).execute(tripsList, ui, null);
        assertEquals("newLocation", addedTrip.getLocation());
    }
}
