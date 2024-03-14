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
            addMainTrip(tokens);
            break;
        case "deletemaintrip":
            deleteMainTrip(tokens);
            break;
        case "setname":
            setName(tokens);
            break;
        case "setdates":
            setDates(tokens);
            break;
        case "setlocation":
            setLocation(tokens);
            break;
        case "setdescription":
            setDescription(tokens);
            break;
        case "listall":
            listAll();
            break;
        // Other cases for commands like "addsubtrip", "removesubtrip" can be added similarly
        default:
            System.out.println("Unknown command.");
        }
    }

    private void setName(String[] tokens) {
        Trip mainTrip = findTripByName(tokens[1]);
        if (mainTrip == null) {
            System.out.println("Trip not found: " + tokens[1]);
            return;
        }

        String name = "-";
        for (int i = 2; i < tokens.length; i++) {
            if (tokens[i].toLowerCase().equals("/n") && i + 1 < tokens.length) {
                name = tokens[i + 1];
                break;
            }
        }
        mainTrip.setName(name);
        System.out.println("Name set to: " + name);
    }

    private void setDates(String[] tokens) {
        Trip mainTrip = findTripByName(tokens[1]);
        if (mainTrip == null) {
            System.out.println("Trip not found: " + tokens[1]);
            return;
        }

        String start = "-";
        String end = "-";
        for (int i = 2; i < tokens.length; i++) {
            if (tokens[i].toLowerCase().equals("/start") && i + 1 < tokens.length) {
                start = tokens[i + 1];
            } else if (tokens[i].toLowerCase().equals("/end") && i + 1 < tokens.length) {
                end = tokens[i + 1];
            }
        }

        try {
            Date startDate = dateFormat.parse(start);
            Date endDate = dateFormat.parse(end);
            mainTrip.setDates(startDate, endDate);
            System.out.println("Dates set successfully.");
        } catch (ParseException e) {
            System.out.println("Invalid date format. Please use yyyy-MM-dd.");
        }
    }

    private void setLocation(String[] tokens) {
        Trip mainTrip = findTripByName(tokens[1]);
        if (mainTrip == null) {
            System.out.println("Trip not found: " + tokens[1]);
            return;
        }

        String location = "-";
        for (int i = 2; i < tokens.length; i++) {
            if (tokens[i].toLowerCase().equals("/location") && i + 1 < tokens.length) {
                StringBuilder sentenceBuilder = new StringBuilder();
                for (int j = i + 1; j < tokens.length && !tokens[j].startsWith("/"); j++) {
                    sentenceBuilder.append(tokens[j]).append(" ");
                }
                location = sentenceBuilder.toString().trim();
                break;
            }
        }
        mainTrip.setLocation(location);
        System.out.println("Location set to: " + location);
    }

    private void setDescription(String[] tokens) {
        Trip mainTrip = findTripByName(tokens[1]);
        if (mainTrip == null) {
            System.out.println("Trip not found: " + tokens[1]);
            return;
        }

        String description = "-";
        for (int i = 2; i < tokens.length; i++) {
            if (tokens[i].toLowerCase().equals("/d") && i + 1 < tokens.length) {
                StringBuilder sentenceBuilder = new StringBuilder();
                for (int j = i + 1; j < tokens.length && !tokens[j].startsWith("/"); j++) {
                    sentenceBuilder.append(tokens[j]).append(" ");
                }
                description = sentenceBuilder.toString().trim();
                break;
            }
        }
        mainTrip.setDescription(description);
        System.out.println("Description set to: " + description);
    }

    private Trip findTripByName(String tripName) {
        for (Trip trip : tripsList) {
            if (trip.getName().equals(tripName)) {
                return trip;
            }
        }
        return null;
    }

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

    private void addMainTrip(String[] tokens) {
        StringBuilder sentenceBuilder = new StringBuilder();
        String name = "-";
        String start = "-";
        String end = "-";
        String location = "-";
        String description = "-";

        for (int i = 1; i < tokens.length; i++) {
            switch (tokens[i].toLowerCase()) {
            case "/n":
                name = addWordAfterSeparator(tokens, name, i);
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
                description = addWordAfterSeparator(tokens, description, i);
                break;
            case "/location":
                location = addWordAfterSeparator(tokens, location, i);
                break;
            default:
                System.out.println("Bug in add main trip, reached default");
                break;
            }
        }

        if (tripNameExists(name)) {
            System.out.println("A trip with the name '" + name + "' already exists. Cannot create another main trip " +
                    "with the same name.");
            return;
        }

        try {
            if (name == "-") {
                System.out.println("You cannot leave name empty when creating a new main trip.");
                return;
            }
            Date startDate = start.equals("-") ? DEFAULT_START : dateFormat.parse(start);
            Date endDate = end.equals("-") ? DEFAULT_END : dateFormat.parse(end);

            if (!confirmPartialInfo(name, startDate, endDate, location, description)) {
                System.out.println("Partial trip information not confirmed. Aborting.");
                return;
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

    private String addWordAfterSeparator(String[] tokens, String name, int i) {
        if (i + 1 < tokens.length) {
            StringBuilder nameBuilder = new StringBuilder();
            for (int j = i + 1; j < tokens.length && !tokens[j].startsWith("/"); j++) {
                nameBuilder.append(tokens[j]).append(" ");
            }
            name = nameBuilder.toString().trim();
        }
        return name;
    }


    private boolean tripNameExists(String name) {
        for (Trip trip : tripsList) {
            if (trip.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    private boolean confirmPartialInfo(String name, Date startDate, Date endDate, String location, String description) {
        if (!(name.isEmpty() || startDate == DEFAULT_START || endDate == DEFAULT_END
                || location.isEmpty() || description.isEmpty())) {
            return true;
        }
        Scanner scanner = new Scanner(System.in);
        System.out.println("You are about to add a trip with the following information:");
        System.out.println("Name: " + name);
        System.out.println("Start Date: " + (startDate == DEFAULT_START ? "-" : dateFormat.format(startDate)));
        System.out.println("End Date: " + (endDate == DEFAULT_END ? "-" : dateFormat.format(endDate)));
        System.out.println("Location: " + location);
        System.out.println("Description: " + description);
        System.out.println("Confirm? (Y/N)");

        String confirmation = scanner.nextLine().toLowerCase();
        return confirmation.equals("y");
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
