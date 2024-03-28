package seedu.duke;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Parser {

    private static Logger logger = Logger.getLogger("ParserLogger");
    /**
     * Checks if the string is a number
     *
     * @param str The string that is to be defined as a number or sentence
     * @return true or false
     */
    public static boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch(NumberFormatException e){
            return false;
        }
    }

    /**
     * Obtains the list of travel activities
     *
     * @param list List of travel activities.
     */
    public static void getList(String[] command, TravelActivityList list) throws OmniException{
        if (command.length == 1) {
            System.out.println("Here are the travel activities in your list:");
            list.listTravelActivities();
        } else {
            throw new OmniException("Do you mean the command list?");
        }
    }

    /**
     * Handles the case where the add command is given as input
     *
     * @param line Line that the user inputs into the chatbot
     * @param list List of travel activities
     * @throws OmniException if command.length < 2
     */
    public static void activityCommand(String line, TravelActivityList list) throws OmniException{
        //logger.log(Level.INFO, "activityCommand");
        String[] command = line.split(" ");
        String delimiter = command[0] + "| /date | /duration | /tag ";
        String[] input = line.split(delimiter);
        //logger.log(Level.INFO, input[0] + " // " +  input[1] + " // " +  input[2] + " // " +  input[3]);
        if (input.length >= 4 && input[1].isBlank()) {
            throw new OmniException("The description of accommodation cannot be empty!");
        } else if(input.length >= 4 && input[2].isBlank()){
            throw new OmniException("The date cannot be empty!");
        } else if (input.length >= 4 && input[3].isBlank()){
            throw new OmniException("The duration cannot be empty!");
        } else if (input.length >= 5 && input[4].isBlank()){
            throw new OmniException("The tag cannot be empty!");
        }else if (input.length >= 4 && !line.contains("/tag") ) {
            if (command[0].equals("accommodation")) {
                Accommodation newActivity = new Accommodation(input[1].trim(), LocalDate.parse(input[2]),
                        input[3].trim(), "");
                list.addTravelActivity(newActivity);
                System.out.println("I added a new accommodation");
                System.out.println(newActivity);
            } else if (command[0].equals("food")) {
                Food newActivity = new Food(input[1].trim(), LocalDate.parse(input[2]),
                        input[3].trim(), "");
                list.addTravelActivity(newActivity);
                System.out.println("I added a new restaurant");
                System.out.println(newActivity);
            } else if (command[0].equals("landmark")) {
                Landmark newActivity = new Landmark(input[1].trim(), LocalDate.parse(input[2]),
                        input[3].trim(), "");
                list.addTravelActivity(newActivity);
                System.out.println("I added a new landmark");
                System.out.println(newActivity);
            }
        } else if(input.length >= 5 && line.contains("/tag") ){
            if (command[0].equals("accommodation")) {
                Accommodation newActivity = new Accommodation(input[1].trim(), LocalDate.parse(input[2]),
                        input[3].trim(), input[4].trim());
                list.addTravelActivity(newActivity);
                System.out.println("I added a new accommodation");
                System.out.println(newActivity);
            } else if (command[0].equals("food")) {
                Food newActivity = new Food(input[1].trim(), LocalDate.parse(input[2]),
                        input[3].trim(), input[4].trim());
                list.addTravelActivity(newActivity);
                System.out.println("I added a new restaurant");
                System.out.println(newActivity);
            } else if (command[0].equals("landmark")) {
                Landmark newActivity = new Landmark(input[1].trim(), LocalDate.parse(input[2]),
                        input[3].trim(), input[4].trim());
                list.addTravelActivity(newActivity);
                System.out.println("I added a new landmark");
                System.out.println(newActivity);
            }
        } else {
            throw new OmniException("Please check that your command is in this format: accomodation DESCRIPTION " +
                    "/date YYYY-MM-DD /duration DURATION /tag TAG" + System.lineSeparator()
                    + "or accomodation DESCRIPTION /date YYYY-MM-DD /duration DURATION");
        }
    }

    /**
     * Handles the case where the add command is given as input
     *
     * @param line Line that the user inputs into the chatbot
     * @param list List of travel activities
     * @throws OmniException if command.length < 2
     */
    public static void addCommand(String line, TravelActivityList list) throws OmniException{
        String[] command = line.split("add | /date | /duration | /tag ");
        logger.log(Level.INFO, command[0] + " // " +  command[1]);
        if (command.length >= 4 && command[1].isBlank()) {
            throw new OmniException("The description of add cannot be empty!");
        } else if(command.length >= 4 && command[2].isBlank()){
            throw new OmniException("The date cannot be empty!");
        } else if (command.length >= 4 && command[3].isBlank()){
            throw new OmniException("The duration cannot be empty!");
        } else if (command.length >= 4 && !line.contains("/tag") ) {
            TravelActivity newActivity = new TravelActivity(command[1].trim(), LocalDate.parse(command[2]),
                    command[3].trim(), "");
            list.addTravelActivity(newActivity);
            System.out.println("I added a new travel activity");
            System.out.println(newActivity);
        } else if(command.length >= 5 && line.contains("/tag")){
            TravelActivity newActivity = new TravelActivity(command[1].trim(), LocalDate.parse(command[2]),
                    command[3].trim(), command[4].trim());
            list.addTravelActivity(newActivity);
            System.out.println("I added a new travel activity");
            System.out.println(newActivity);
        } else{
            throw new OmniException("Please check that your add command is in this format: add DESCRIPTION " +
                                    "/date YYYY-MM-DD /duration DURATION"
                                    + " or add DESCRIPTION /date YYYY-MM-DD /duration DURATION /tag TAG");
        }
    }

    /**
     * Handles the case where the delete command is given as input
     *
     * @param command Command array of input string without spaces
     * @param list List of travel activities
     * @throws OmniException if command.length != 2 && command[1] is not numeric
     */
    public static void deleteCommand(String[] command, TravelActivityList list) throws OmniException {
        if (command.length == 2 && isNumeric(command[1])){
            int listNumber = Integer.parseInt(command[1]);
            list.removeTravelActivity(listNumber);

        } else {
            throw new OmniException("Please specify which activity to delete");
        }
    }

    /**
     * Handles the case where the check command is given as input
     *
     * @param command Command array of input string without spaces
     * @param list List of travel activities
     * @throws OmniException if command.length != 2 && command[1] is not numeric
     */
    public static void checkCommand(String[] command, TravelActivityList list) throws OmniException {
        if (command.length == 2 && isNumeric(command[1])){
            int listNumber = Integer.parseInt(command[1]);
            list.checkTravelActivity(listNumber);
        } else {
            throw new OmniException("Please specify which activity to check");
        }
    }

    /**
     * Handles the case where the uncheck command is given as input
     *
     * @param command Command array of input string without spaces
     * @param list List of travel activities
     * @throws OmniException if command.length != 2 && command[1] is not numeric
     */
    public static void uncheckCommand(String[] command, TravelActivityList list) throws OmniException {
        if (command.length == 2 && isNumeric(command[1])){
            int listNumber = Integer.parseInt(command[1]);
            list.uncheckTravelActivity(listNumber);
        } else {
            throw new OmniException("Please specify which activity to uncheck");
        }
    }

    /**
     * Handles the case where the tag command is given as input
     *
     * @param line array of input string
     * @param list List of travel activities
     * @throws OmniException if command.length == 2
     * @throws OmniException if command.length == 1
     */
    public static void tagCommand(String line, TravelActivityList list) throws OmniException {
        String[] command = line.split(" ");
        if (command.length == 3 && isNumeric(command[1])){
            int listNumber = Integer.parseInt(command[1]);
            String tag = command[2];
            list.tagActivity(listNumber, tag);
        } else if (command.length == 2) {
            throw new OmniException("Please specify a tag name");
        } else {
            throw new OmniException("Please specify which task to tag");
        }
    }

    /**
     * Handles the case where the untag command is given as input
     *
     * @param command Command array of input string without spaces
     * @param list List of travel activities
     * @throws OmniException if command.length != 2 && command[1] is not numeric
     */
    public static void removeTagCommand(String[] command, TravelActivityList list) throws OmniException {
        if (command.length == 2 && isNumeric(command[1])) {
            int listNumber = Integer.parseInt(command[1]);
            list.removeTag(listNumber);
        } else {
            throw new OmniException("Please specify which task to remove tag");
        }
    }

    /**
     * Handles the case where the update command is given as input
     * @param line The update command given by the user
     * @param list The list of travel activities
     * @throws OmniException Thrown if update index, date, duration is not given
     */
    public static void updateCommand(String line, TravelActivityList list) throws OmniException {
        String[] command = line.split("update | /date | /duration | /tag ");
        if (command.length >= 4 && (command[1].isBlank() || !isNumeric(command[1]))) {
            throw new OmniException("The update index cannot be empty or non numerical!");
        } else if (command.length >= 4 && command[2].isBlank()) {
            throw new OmniException("The date cannot be empty!");
        } else if (command.length >= 4 && command[3].isBlank()) {
            throw new OmniException("The duration cannot be empty!");
        } else if(command.length >= 5 && command[4].isBlank()){
            throw new OmniException("The tag cannot be empty!");
        } else if (command.length >= 5 && line.contains("/tag")) {
            list.updateTravelActivity(Integer.parseInt(command[1]), LocalDate.parse(command[2]), command[3],
                    command[4]);
        } else if (command.length >= 4 && !line.contains("/tag")){
            list.updateTravelActivity(Integer.parseInt(command[1]), LocalDate.parse(command[2]), command[3],
                    "");
        } else {
            throw new OmniException("Please check that your update command is in this format: update INDEX " +
                    "/date YYYY-MM-DD /duration DURATION"
                    + " or update INDEX /date YYYY-MM-DD /duration DURATION /tag TAG");
        }
    }

    /**
     * Handles the case where the findtag command is given as input
     *
     * @param line User's input into Omnitravel
     * @param list List of travel activities
     * @throws OmniException if command.length < 1
     */

    public static void findTagCommand(String line, TravelActivityList list) throws OmniException {
        String[] command = line.split("findtag");
        if (command.length < 1) {
            throw new OmniException("Please check that your update command is in this format: findtag <task tag>");
        } else {
            list.findTag(command[1].trim());
        }
    }

    /**
     * Handles the case where the findtype command is given as input
     *
     * @param line User's input into Omnitravel
     * @param list list List of travel activities
     * @throws OmniException if command.length < 1
     */

    public static void findTypeCommand(String line, TravelActivityList list) throws OmniException {
        String[] command = line.split("findtype");
        if (command.length < 1) {
            throw new OmniException("Please check that your find type command is in this format: findtype <task type>");
        } else {
            list.findType(command[1].trim());
        }
    }

    /**
<<<<<<< HEAD
     *  Handles the case where the find command is given as input
     *
     * @param command Command array of input string without spaces
     * @param list List of travel activities
     * @throws OmniException if command.length != 2
     */
    public static void findCommand(String[] command, TravelActivityList list) throws OmniException {
        if (command.length == 2) {
            String keyword = command[1];
            list.searchKeyword(keyword);
        } else {
            throw new OmniException("Please specify an appropriate keyword you want to find!");
        }
    }

     /** Handles the case where the expense command is given as input
     *
     * @param line array of input string
     * @param list List of travel activities
     * @throws OmniException if command.length == 2
     * @throws OmniException if command.length == 1
     */
    public static void expenseCommand(String line, TravelActivityList list) throws OmniException {
        String[] command = line.split("expense |-");
        if (command.length == 3 && isNumeric(command[1])){
            int listNumber = Integer.parseInt(command[1]);
            String expense = command[2];
            list.expenseActivity(listNumber, expense);
        } else if (command.length == 2) {
            throw new OmniException("Please specify expense amount");
        } else {
            throw new OmniException("Please specify which task to add expense");
        }
    }

    /**
     * Handles the case where the removeexpense command is given as input
     *
     * @param command Command array of input string without spaces
     * @param list List of travel activities
     * @throws OmniException if command.length != 2 && command[1] is not numeric
     */
    public static void removeExpenseCommand(String[] command, TravelActivityList list) throws OmniException {
        if (command.length == 2 && isNumeric(command[1])) {
            int listNumber = Integer.parseInt(command[1]);
            list.removeExpense(listNumber);
        } else {
            throw new OmniException("Please specify which task to remove expense");
        }
    }

}
