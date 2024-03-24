package seedu.duke;

import org.junit.jupiter.api.Test;
import seedu.duke.exceptions.FlirtForkException;
import java.util.NoSuchElementException;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertTrue;

class DukeTest {
    private static final String FILE_PATH = "./data/FlirtFork.txt";
    private Ui ui = new Ui();
    private Storage storage = new Storage(FILE_PATH);
    private FavouritesList favourites;
    private FoodList foods;
    private ActivityList activities;
    private UserDetails userDetails;

    public void sampleTest() {
        assertTrue(true);
    }

    @Test
    public void testGenerateIdeaCommand() {
        try {
            favourites = new FavouritesList(storage.loadFavourites());
            foods = new FoodList(storage.loadFood());
            activities = new ActivityList(storage.loadActivity());
            userDetails = storage.loadUserDetails();
        } catch (FileNotFoundException e) {
            ui.errorMessage("File not found. Starting with an empty task list :)");
            favourites = new FavouritesList(new ArrayList<>());
        } 
        GenerateIdeaCommand generateIdeaCommand = new GenerateIdeaCommand();

        // Backup original system
        InputStream sysInBackup = System.in;
        PrintStream sysOutBackup = System.out;

        // Provide the simulated input
        String inputData = "Yes";
        ByteArrayInputStream in = new ByteArrayInputStream(inputData.getBytes());

        // Capture System.out output
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        System.setOut(printStream);

        try {
            generateIdeaCommand.execute(favourites, foods, activities, ui, storage, userDetails);
        } catch (NoSuchElementException e) {
            System.setIn(in);
            String output = outputStream.toString();
            assertTrue(output.contains("You can do"));
            assertTrue(output.contains("and have a nice meal at"));
            assertTrue(output.length() > 36);
        } catch (FlirtForkException e) {
            ui.errorMessage(e.getMessage());
        } finally {
            System.setIn(sysInBackup);
            System.setOut(sysOutBackup);
        }
    }

    @Test
    public void generateItineraryCommand_validInputs_success() {
        try {
            favourites = new FavouritesList(storage.loadFavourites());
            foods = new FoodList(storage.loadFood());
            activities = new ActivityList(storage.loadActivity());
        } catch (FileNotFoundException e) {
            ui.errorMessage("File not found. Starting with an empty task list :)");
            favourites = new FavouritesList(new ArrayList<>());
        }
        GenerateItineraryCommand generateItineraryCommand = new GenerateItineraryCommand("C C");

        // Backup original system
        InputStream sysInBackup = System.in;
        PrintStream sysOutBackup = System.out;

        // Provide the simulated input
        String inputData = "Yes";
        ByteArrayInputStream in = new ByteArrayInputStream(inputData.getBytes());

        // Capture System.out output
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        System.setOut(printStream);

        try {
            generateItineraryCommand.execute(favourites, foods, activities, ui, storage, userDetails);
        } catch (NoSuchElementException e) {
            System.setIn(in);
            String output = outputStream.toString();
            assertTrue(output.contains("Here is a rough itinerary for your date:"));
        } catch (FlirtForkException e) {
            ui.errorMessage(e.getMessage());
        } finally {
            System.setIn(sysInBackup);
            System.setOut(sysOutBackup);
        }
    }

    @Test
    public void generateItineraryCommand_invalidInputs_errorMessagePrinted() {
        try {
            favourites = new FavouritesList(storage.loadFavourites());
            foods = new FoodList(storage.loadFood());
            activities = new ActivityList(storage.loadActivity());
        } catch (FileNotFoundException e) {
            ui.errorMessage("File not found. Starting with an empty task list :)");
            favourites = new FavouritesList(new ArrayList<>());
        }
        GenerateItineraryCommand generateItineraryCommand = new GenerateItineraryCommand("THW GDBE");
        try {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            PrintStream printStream = new PrintStream(outputStream);
            System.setOut(printStream);
            generateItineraryCommand.execute(favourites, foods, activities, ui, storage, userDetails);
            String output = outputStream.toString();
            assertTrue(output.contains("We could not generate a suitable itineray based on your inputs! Sorry!!"));
            System.setOut(System.out);
        } catch (FlirtForkException e) {
            ui.errorMessage(e.getMessage());
        }
    }
}
