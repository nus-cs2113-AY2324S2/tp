package seedu.voyagers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

public class Parser {

    private static final Calendar defaultStartCalendar = Calendar.getInstance();
    private static final Calendar defaultEndCalendar = Calendar.getInstance();

    static {
        defaultStartCalendar.set(1900, Calendar.JANUARY, 1);
        defaultEndCalendar.set(2500, Calendar.JANUARY, 1);
    }

    private static final Date DEFAULT_START = defaultStartCalendar.getTime();
    private static final Date DEFAULT_END = defaultEndCalendar.getTime();

    private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    private final SimpleDateFormat printDateFormat = new SimpleDateFormat("dd MMMM yyyy");
    private ArrayList<Trip> tripsList;

    public Parser(ArrayList<Trip> tripsList) {
        this.tripsList = tripsList;
    }

    public void parseInput(String input) {
        String[] tokens = input.split(" ");
        if (tokens.length < 2 && !tokens[0].equals("listall")) {
            System.out.println("Insufficient input.");
            return;
        }

        String command = tokens[0].toLowerCase();

        switch (command) {
        case "addmaintrip":
            commandAddMainTrip(tokens);
            break;
        case "deletemaintrip":
            deleteMainTrip(tokens);
            break;
        case "setname":
            commandSetName(tokens);
            break;
        case "setdates":
            commandSetDates(tokens);
            break;
        case "setlocation":
            commandSetLocation(tokens);
            break;
        case "setdescription":
            commandSetDescription(tokens);
            break;
        case "listall":
            listAll();
            break;
        // Other cases for commands like "addsubtrip", "removesubtrip" can be added similarly
        default:
            System.out.println("Unknown command.");
        }
    }

    /**
     * Method to parse user input to change existing trip's name
     *
     * @param tokens String[] of user input split by " "
     *               eg. user input `setname /old The old name /new Vacation to Hokkaido`
     *               setname must be first word, flag-content pair can be input in any order
     *               name can be made up of multiple words
     *               do not input "/" unless as a flag.
     */
    private void commandSetName(String[] tokens) {
        String oldName = null;
        String newName = null;
        boolean oldNameEntered = false;
        boolean newNameEntered = false;
        for (int i = 0; i + 1 < tokens.length; i++) {
            switch (tokens[i].toLowerCase()) {
            case "/old":
                oldName = addWordsAfterSeparator(tokens, i);
                oldNameEntered = true;
                break;
            case "/new":
                newName = addWordsAfterSeparator(tokens, i);
                newNameEntered = true;
                break;
            default:
                //No flags found
                break;
            }
        }
        if (!oldNameEntered) {
            System.out.println("You are missing /old <name>");
            return;
        }
        if (!newNameEntered) {
            System.out.println("You are missing /new <name>");
            return;
        }
        // Check if the trip exists (if not, print an error message and return)
        Trip mainTrip = findTripByName(oldName);
        if (mainTrip == null) {
            System.out.println("Trip not found: " + oldName);
            return;
        }
        mainTrip.setName(newName);
        System.out.println("Name set to: " + newName);
    }

    /**
     * Method to parse user input to change existing trip's dates
     *
     * @param tokens String[] of user input split by " "
     *               eg. user input `setdates /n Current Name /start 2024/01/01 /end 2024/03/31`
     *               setdates must be first word, flag-content pair can be input in any order
     *               dates must be in yyyy/MM/dd format
     *               do not input "/" unless as a flag.
     */
    private void commandSetDates(String[] tokens) {
        String tripName = null;
        String start = "-";
        String end = "-";
        boolean nameEntered = false;
        boolean startEntered = false;
        boolean endEntered = false;
        for (int i = 0; i < tokens.length; i++) {
            switch (tokens[i].toLowerCase()) {
            case "/n":
                tripName = addWordsAfterSeparator(tokens, i);
                nameEntered = true;
                break;
            case "/start":
                start = tokens[i + 1];
                startEntered = true;
                break;
            case "/end":
                end = tokens[i + 1];
                endEntered = true;
                break;
            default:
                break;
            }
        }
        if (!nameEntered) {
            System.out.println("You are missing /n <name>");
            return;
        }
        // Check if the trip exists (if not, print an error message and return)
        Trip mainTrip = findTripByName(tripName);
        if (mainTrip == null) {
            System.out.println("Trip not found: " + tripName);
            return;
        }
        if (!startEntered || !endEntered) {
            System.out.println("You are missing /start <date> or /end <date");
            return;
        }
        // Parse the dates (if valid)
        try {
            Date startDate = dateFormat.parse(start);
            Date endDate = dateFormat.parse(end);
            mainTrip.setDates(startDate, endDate);
            System.out.println("Dates set successfully.");
        } catch (ParseException e) {
            System.out.println("Invalid date format. Please use yyyy-MM-dd.");
        }
    }

    /**
     * Method to parse user input to change existing trip's location
     *
     * @param tokens String[] of user input split by " "
     *               eg. user input `setlocation /n The current name /location New Place`
     *               setlocation must be first word, flag-content pair can be input in any order
     *               location can be made up of multiple words
     *               do not input "/" unless as a flag.
     */
    private void commandSetLocation(String[] tokens) {
        String tripName = null;
        String location = "-";
        boolean nameEntered = false;
        boolean locationEntered = false;
        for (int i = 0; i + 1 < tokens.length; i++) {
            switch (tokens[i].toLowerCase()) {
            case "/n":
                tripName = addWordsAfterSeparator(tokens, i);
                nameEntered = true;
                break;
            case "/location":
                location = addWordsAfterSeparator(tokens, i);
                locationEntered = true;
                break;
            default:
                break;
            }
        }
        if (!nameEntered) {
            System.out.println("You are missing /n <name>");
            return;
        }
        if (!locationEntered) {
            System.out.println("You are missing /location <location");
            return;
        }
        Trip mainTrip = findTripByName(tripName);
        if (mainTrip == null) {
            System.out.println("Trip not found: " + tripName);
            return;
        }
        mainTrip.setLocation(location);
        System.out.println("Location set to: " + location);
    }

    /**
     * Method to parse user input to change existing trip's description
     *
     * @param tokens String[] of user input split by " "
     *               eg. user input `setdescription /m The current name /description New description`
     *               setdescription must be first word, flag-content pair can be input in any order
     *               description can be made up of multiple words
     *               do not input "/" unless as a flag.
     */
    private void commandSetDescription(String[] tokens) {
        String tripName = null;
        String description = "-";
        boolean nameEntered = false;
        boolean descriptionEntered = false;
        for (int i = 0; i + 1 < tokens.length; i++) {
            switch (tokens[i].toLowerCase()) {
            case "/n":
                tripName = addWordsAfterSeparator(tokens, i);
                break;
            case "/d":
                description = addWordsAfterSeparator(tokens, i);
                descriptionEntered = true;
                break;
            default:
                break;
            }
        }
        if (!nameEntered) {
            System.out.println("You are missing /n <name>");
            return;
        }
        Trip mainTrip = findTripByName(tripName);
        if (mainTrip == null) {
            System.out.println("Trip not found: " + tripName);
            return;
        }
        if (!descriptionEntered) {
            System.out.println("You are missing /n <name> or /d <description");
            return;
        }
        mainTrip.setDescription(description);
        System.out.println("Description set to: " + description);
    }

    /**
     * Method to find a trip by name
     *
     * @param tripName name of the trip to find
     * @return Trip object if found, null otherwise
     */
    protected Trip findTripByName(String tripName) {
        for (Trip trip : tripsList) {
            if (trip.getName().equals(tripName)) {
                return trip;
            }
        }
        return null;
    }

    /**
     * Method to print a list of all trips
     */
    public void listAll() {
        System.out.println("Index\tName\tStart Date\tEnd Date\tLocation\tDescription");
        for (int i = 0; i < tripsList.size(); i++) {
            Trip trip = tripsList.get(i);
            String startDateStr = trip.getStartDate().equals(DEFAULT_START) ?
                    "-" : printDateFormat.format(trip.getStartDate());
            String endDateStr = trip.getEndDate().equals(DEFAULT_END) ? "-" : printDateFormat.format(trip.getEndDate());
            System.out.printf("%d\t%s\t%s\t%s\t%s\t%s\n", i, trip.getName(), startDateStr,
                    endDateStr, trip.getLocation(), trip.getDescription());
        }
    }

    /**
     * Method to parse user input to add a new main trip
     *
     * @param tokens String[] of user input split by " "
     *               eg. user input `addmain /n The current name /start 2024/01/01
     *               /end 2024/03/31 /d Description /location New Place`
     *               addmain must be first word, flag-content pair can be input in any order
     *               dates must be in yyyy/MM/dd format
     *               name, location, descrption can be made up of multiple words
     *               /n name is required to add trip
     *               other flag-content pairs may be omitted to add trip with partial information
     *               Name cannot match an existing trip's name
     *               do not input "/" unless as a flag.
     */
    private void commandAddMainTrip(String[] tokens) {
        StringBuilder sentenceBuilder = new StringBuilder();
        String name = "-";
        String start = "-";
        String end = "-";
        String location = "-";
        String description = "-";
        //Loop through user input words to find flag-content pairs
        for (int i = 1; i < tokens.length; i++) {
            switch (tokens[i].toLowerCase()) {
            case "/n":
                name = addWordsAfterSeparator(tokens, i);
                break;
            case "/start":
                if (i + 1 < tokens.length) {
                    start = tokens[i + 1];
                }
                break;
            case "/end":
                if (i + 1 < tokens.length) {
                    end = tokens[i + 1];
                }
                break;
            case "/d":
                description = addWordsAfterSeparator(tokens, i);
                break;
            case "/location":
                location = addWordsAfterSeparator(tokens, i);
                break;
            default:
                //No flags found
                break;
            }
        }
        //check if trip with same name exists
        if (tripNameExists(name)) {
            System.out.println("A trip with the name '" + name + "' already exists. Cannot create another main trip " +
                    "with the same name.");
            return;
        }

        try {
            //check if name has was inputted
            if (name == "-") {
                System.out.println("You cannot leave name empty when creating a new main trip.");
                return;
            }
            Date startDate = start.equals("-") ? DEFAULT_START : dateFormat.parse(start);
            Date endDate = end.equals("-") ? DEFAULT_END : dateFormat.parse(end);
            //check if only partial info is provided (name must be entered)
            if (isPartialTripInfo(name, startDate, endDate, location, description)) {
                Scanner scanner = new Scanner(System.in);
                System.out.println("You are about to add a trip with the following information:");
                System.out.println("Name: " + name);
                System.out.println("Start Date: " + (startDate == DEFAULT_START ? "-" : dateFormat.format(startDate)));
                System.out.println("End Date: " + (endDate == DEFAULT_END ? "-" : dateFormat.format(endDate)));
                System.out.println("Location: " + location);
                System.out.println("Description: " + description);
                System.out.println("Confirm? (Y/N)");
                //print to user asking for confirmation of partial trip info
                String confirmation = null;
                while (confirmation == null) {
                    confirmation = scanner.nextLine().toLowerCase();
                }
                if (!confirmation.toLowerCase().equals("y")) {
                    System.out.println("Add main trip aborted");
                    return;
                }
            }

            Trip newTrip = new Trip(name, startDate, endDate, location, description);
            tripsList.add(newTrip);
            System.out.println("Main trip added successfully.");
        } catch (ParseException e) {
            System.out.println("Invalid date format for main trip. Please use yyyy-MM-dd.");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Method to concatenate words until the end of the String[] tokens or until the next "/"
     *
     * @param tokens user input split  by " "
     *               eg. String[] tokens = {"/n", "The", "current", "name", "/location", "New", "Place"};
     * @param i index of the current word in the String[] tokens
     * @return sentence String name with concatenated words
     *               eg. sentence = "New Place"
     */
    private String addWordsAfterSeparator(String[] tokens, int i) {
        String sentence = null;
        if (i + 1 < tokens.length) {
            StringBuilder nameBuilder = new StringBuilder();
            for (int j = i + 1; j < tokens.length && !(tokens[j].startsWith("/")); j++) {
                nameBuilder.append(tokens[j]).append(" ");
            }
            sentence = nameBuilder.toString().trim();
        }
        return sentence;
    }


    private boolean tripNameExists(String name) {
        for (Trip trip : tripsList) {
            if (trip.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    private boolean isPartialTripInfo(String name, Date startDate, Date endDate, String location, String description) {
        if (startDate == DEFAULT_START || endDate == DEFAULT_END
                || location.isEmpty() || description.isEmpty()) {
            return true;
        }
        return false;
    }

    private void deleteMainTrip(String[] tokens) {
        String tripName = tokens[1];
        Trip tripToDelete = findTripByName(tripName);
        if (tripToDelete == null) {
            System.out.println("Trip not found: " + tripName);
            return;
        }

        tripsList.remove(tripToDelete);
        System.out.println("Main trip '" + tripName + "' deleted successfully.");
    }

}
