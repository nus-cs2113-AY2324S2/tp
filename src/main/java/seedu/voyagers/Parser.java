package seedu.voyagers;

import seedu.voyagers.commands.*;


import java.text.ParseException;
import java.text.SimpleDateFormat;

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

    private static final SimpleDateFormat dateFormat = Util.dateFormat;




    public static Command parseInput(String input) throws IllegalArgumentException{
        String[] tokens = input.split(" ");
        if (tokens.length < 2 && !tokens[0].equals("listall") && !tokens[0].equals("exit")
                && !tokens[0].equals("help")) {
            System.out.println("Insufficient input.");
            throw new IllegalArgumentException("Insufficient input.");
        }

        String command = tokens[0].toLowerCase();

        switch (command) {
        case "addmaintrip":
            return commandAddMainTrip(tokens);
        case "deletemaintrip":
            return deleteMainTrip(tokens);
        case "setname":
            return commandSetName(tokens);
        case "setdates":
            return commandSetDates(tokens);
        case "setlocation":
            return commandSetLocation(tokens);
        case "setdescription":
            return commandSetDescription(tokens);
        case "listall":
            return listAll();
        case "exit":
            return new ExitCommand();
        case "help":
            return new HelpCommand();
        // Other cases for commands like "addsubtrip", "removesubtrip" can be added similarly
        default:
            System.out.println("Unknown command.");
        }

        return new EmptyCommand();
    }

    /**
     * Method to parse user input to change existing trip's name
     *
     * @param tokens String[] of user input split by " "
     */
    private static ModifyTripCommand commandSetName(String[] tokens) {
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

        //TODO: change for exception
        if (!oldNameEntered) {
            System.out.println("You are missing /old <name>");
            throw new IllegalArgumentException("You are missing /old <name>");
        }
        if (!newNameEntered) {
            System.out.println("You are missing /new <name>");
            throw new IllegalArgumentException("You are missing /new <name>");
        }

        assert (oldNameEntered && newNameEntered) : "oldName and newName must be entered";

        String args[] = {oldName, "name", newName};
        return new ModifyTripCommand(args);
    }

    /**
     * Method to parse user input to change existing trip's dates
     *
     * @param tokens String[] of user input split by " "
     */
    private static Command commandSetDates(String[] tokens) {
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
            throw new IllegalArgumentException("You are missing /n <name>");
        }

        if (!startEntered || !endEntered) {
            System.out.println("You are missing /start <date> or /end <date");
            throw new IllegalArgumentException("You are missing /start <date> or /end <date");
        }

        String args[] = {tripName, "dates", start, end};
        return new ModifyTripCommand(args);

    }

    /**
     * Method to parse user input to change existing trip's location
     *
     * @param tokens String[] of user input split by " "
     */
    private static Command commandSetLocation(String[] tokens) {
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
            throw new IllegalArgumentException("You are missing /n <name>");
        }
        if (!locationEntered) {
            System.out.println("You are missing /location <location");
            throw new IllegalArgumentException("You are missing /location <location");
        }

        String args[] = {tripName, "location", location};
        return new ModifyTripCommand(args);
    }

    /**
     * Method to parse user input to change existing trip's description
     *
     * @param tokens String[] of user input split by " "
     */
    private static Command commandSetDescription(String[] tokens) {
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
            throw new IllegalArgumentException("You are missing /n <name>");
        }

        if (!descriptionEntered) {
            System.out.println("You are missing /n <name> or /d <description");
            throw new IllegalArgumentException("You are missing /n <name> or /d <description");
        }

        String args[] = {tripName, "description", description};
        return new ModifyTripCommand(args);
    }

    /**
     * Method to print a list of all trips
     */
    private static Command listAll() {
       return new ListCommand();
    }

    /**
     * Method to parse user input to add a new main trip
     *
     * @param tokens String[] of user input split by " "
     */
    private static Command commandAddMainTrip(String[] tokens) {
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

        System.out.println("Check 1");
        try {
            //check if name has was inputted
            if (name == "-") {
                System.out.println("You cannot leave name empty when creating a new main trip.");
                throw new IllegalArgumentException("Name cannot be empty.");
            }
            System.out.println("Check 2");
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
                    return new EmptyCommand();
                }
            }
            System.out.println("Check 3");
            // print start date in the format yyyy-MM-dd
            String startDateString = dateFormat.format(startDate);
            String endDateString = dateFormat.format(endDate);
            String args[] = {name, startDateString, endDateString, location, description};
            return new AddTripCommand(args);

        } catch (ParseException e) {
            System.out.println("Invalid date format for main trip. Please use yyyy-MM-dd.");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("Check 4");
        return new EmptyCommand();
    }

    /**
     * Method to concatenate words until the end of the String[] tokens or until the next "/"
     *
     * @param tokens user input split by " "
     * @param i      index of the current word in the String[] tokens
     * @return sentence String name with concatenated words
     */

    private static String addWordsAfterSeparator(String[] tokens, int i) {
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



    private static boolean isPartialTripInfo(String name, Date startDate, Date endDate, String location, String description) {
        if (startDate == DEFAULT_START || endDate == DEFAULT_END || location.isEmpty() || description.isEmpty()) {
            return true;
        }
        return false;
    }

    private static Command deleteMainTrip(String[] tokens) {

        String args[] = {tokens[1]};
        return new DeleteCommand(args);

    }

}
