package seedu.duke;

public class Parser {
    /**
     * Checks if the string is a number
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

    public static void getList(TravelActivityList list){
        System.out.println("Here are the travel activities in your list:");
        list.listTravelActivities();
    }

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

    public static void deleteCommand(String[] command, TravelActivityList list) throws OmniException {
        if (command.length == 2 && isNumeric(command[1])){
            int listNumber = Integer.parseInt(command[1]);
            list.removeTravelActivity(listNumber);
        } else {
            throw new OmniException("Please specify which task to delete");
        }
    }

    public static void findCommand(String[] command, TravelActivityList list) throws OmniException{
        if (command.length == 2) {
            String keyword = command[1];
            list.searchKeyword(keyword);
        } else {
            throw new OmniException("Please specify which keyword you want to find!");
        }
    }
}
