package seedu.duke;

public class Parser {
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
    public static void getList(TravelActivityList list){
        System.out.println("Here are the travel activities in your list:");
        list.listTravelActivities();
    }

    /**
     * Handles the case where the add command is given as input
     *
     * @param line Line that the user inputs into the chatbot
     * @param command Command array of input string without spaces
     * @param list List of travel activities
     * @throws OmniException if command.length < 2
     */
    public static void addCommand(String line, String[] command, TravelActivityList list) throws OmniException{
        if (command.length >= 2) {
            TravelActivity newActivity = new TravelActivity(line.substring(4));
            list.addTravelActivity(newActivity);
            System.out.println("I added a new travel activity");
            System.out.println(newActivity);
        } else {
            throw new OmniException("The description of add cannot be empty!");
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
            throw new OmniException("Please specify which task to delete");
        }
    }

    /**
     *  Handles the case where the find command is given as input
     *
     * @param command Command array of input string without spaces
     * @param list List of travel activities
     * @throws OmniException if command.length != 2
     */
    public static void findCommand(String[] command, TravelActivityList list) throws OmniException{
        if (command.length == 2) {
            String keyword = command[1];
            list.searchKeyword(keyword);
        } else {
            throw new OmniException("Please specify which keyword you want to find!");
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
            throw new OmniException("Please specify which task to check");
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
            throw new OmniException("Please specify which task to uncheck");
        }
    }

    /**
     * Handles the case where the tag command is given as input
     *
     * @param command Command array of input string without spaces
     * @param list List of travel activities
     * @throws OmniException if command.length == 2
     * @throws OmniException if command.length == 1
     */
    public static void tagCommand(String[] command, TravelActivityList list) throws OmniException {
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
}