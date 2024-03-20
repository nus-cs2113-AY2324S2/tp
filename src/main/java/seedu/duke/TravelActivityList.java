package seedu.duke;
import java.util.ArrayList;
import java.util.logging.*;

public class TravelActivityList {
    /** Array of travel activity */
    private ArrayList<TravelActivity> travelActivities;

    /** Number of TravelActivities */
    private int noOfTasks = 0;
    public TravelActivityList() {
        travelActivities = new ArrayList<>();
    }

    private static Logger logger = Logger.getLogger("TravelActivityListLogger");

    /**
     * Adds travel activity to the travel activity list
     * @param travelActivity The travel activity
     */
    public void addTravelActivity(TravelActivity travelActivity){
        int initialListSize = noOfTasks;
        travelActivities.add(travelActivity);
        noOfTasks += 1;
        int newSize = noOfTasks;
        assert newSize == initialListSize + 1 :"There is an error with list size!";
    }

    /**
     * Prints out all the travel activities
     */
    public void listTravelActivities(){
        int taskCount = 0;
        for (TravelActivity task: travelActivities) {
            if (task == null) {
                break;
            }
            String checked = task.getTaskStatus()? "[X]" : "[ ]";
            taskCount++;
            System.out.println("     " + checked + " " + taskCount +". " + task);
        }
        int finalTaskCount = noOfTasks;
        assert finalTaskCount == taskCount : "Index out of bounds while listing activities";
    }

    /**
     * returns the number of travel activities in the list
     * @return the number of travel activities
     */
    public int getNoOfTravelActivities(){
        return noOfTasks;
    }

    /**
     * Removes travel activity from the travel activity list
     * @param taskNumber The travel activity number on the list
     */
    public void removeTravelActivity(int taskNumber) throws OmniException{
        assert taskNumber != 0  :"There is not tasks in the list";
        if(taskNumber > travelActivities.size()){
            throw new OmniException("Travel activity cannot be found!");
        }
        int indexOfTask = taskNumber - 1;
        int initialListSize = noOfTasks;
        TravelActivity removedTask = travelActivities.get(indexOfTask);
        travelActivities.remove(indexOfTask);
        System.out.println("I have removed this task:");
        System.out.println(removedTask);
        noOfTasks -=1;
        int newSize = noOfTasks;
        assert newSize == initialListSize - 1 :"There is an error with list size!";
    }


    public String getDescription(String plan){
        for(TravelActivity travelActivity: travelActivities){
            if(travelActivity.getPlan().equals(plan)){
                return travelActivity.getPlan();
            }
        }
        return "cant be found";
    }

    /**
     * Finds all activities in the TravelActivity list that contains a keyword specified
     * by the user.
     *
     * @param taskName keyword specified by the user to find activities in the TravelActivity list
     *                 related to the keyword.
     */

    public void searchKeyword (String taskName) {
        logger.log(Level.INFO, "searchKeyword function started");
        ArrayList<TravelActivity> temporaryArray = new ArrayList<TravelActivity>();;
        int temporaryArrayCounter = 0;
        logger.log(Level.INFO, "temporaryArray is initialised");
        boolean isFound = false;
        logger.log(Level.INFO, "isFound is initialised to false");
        logger.log(Level.INFO, "Search for activities related to taskName given by user started");
        for (int iterator = 0; iterator < travelActivities.size(); iterator += 1){
            if(travelActivities.get(iterator).getPlan().contains(taskName)){
                temporaryArray.add(temporaryArrayCounter ,travelActivities.get(iterator));
                temporaryArrayCounter += 1;
                logger.log(Level.INFO, "An activity is added to the temporaryArray");
            }
        }
        if (temporaryArrayCounter == 0) {
            System.out.println("Sorry I could not find what you are looking for.");
            logger.log(Level.INFO, "No activity is stored in the temporaryArray");
        } else {
            assert !temporaryArray.isEmpty();
            System.out.println("Here are what you are looking for:");
            logger.log(Level.INFO, "Starting the printing of activities in temporaryArray");
            for (int newIterator = 0; newIterator < temporaryArray.size(); newIterator += 1) {
                System.out.println((newIterator + 1) + ". " + temporaryArray.get(newIterator).getPlan());
                logger.log(Level.INFO, "An activity in temporaryArray is printed");
            }
        }
    }

    /**
     * Checks travel activity as completed
     * @param taskNumber The travel activity number on the list
     */
    public void checkTravelActivity(int taskNumber) throws OmniException{

        assert taskNumber != 0 : "There is not tasks in the list";
        if (taskNumber > travelActivities.size()) {
            throw new OmniException("Travel activity cannot be found");
        }
        int indexOfTask = taskNumber - 1;
        TravelActivity markedTask  = travelActivities.get(indexOfTask);
        markedTask.setTaskStatus(true);
        System.out.println("I have checked this task:");
        System.out.println(markedTask);
    }

    /**
     * Unchecks travel activity and sets it to uncompleted
     * @param taskNumber The travel activity number on the list
     */
    public void uncheckTravelActivity(int taskNumber) throws OmniException{
        assert taskNumber != 0 : "There is not tasks in the list";
        if (taskNumber > travelActivities.size()) {
            throw new OmniException("Travel activity cannot be found");
        }
        int indexOfTask = taskNumber - 1;
        TravelActivity markedTask  = travelActivities.get(indexOfTask);
        markedTask.setTaskStatus(false);
        System.out.println("I have unchecked this task:");
        System.out.println(markedTask);
    }


}
