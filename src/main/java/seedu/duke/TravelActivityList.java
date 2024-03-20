package seedu.duke;
import java.util.ArrayList;
import java.util.logging.Logger;
import java.util.logging.Level;


public class TravelActivityList {
    private static Logger logger = Logger.getLogger("TravelActivityListLogger");
    /** Array of travel activity */
    private ArrayList<TravelActivity> travelActivities;

    /** Number of TravelActivities */
    private int noOfActivities = 0;
    public TravelActivityList() {
        travelActivities = new ArrayList<>();
    }


    /**
     * Adds travel activity to the travel activity list
     * @param travelActivity The travel activity
     */
    public void addTravelActivity(TravelActivity travelActivity){
        logger.log(Level.INFO, "addKeyword function started");
        int initialListSize = noOfActivities;
        travelActivities.add(travelActivity);
        logger.log(Level.INFO, "travelActivity is added");
        noOfActivities += 1;
        int newSize = noOfActivities;
        assert newSize == initialListSize + 1 :"There is an error with list size!";
    }

    /**
     * Prints out all the travel activities
     */
    public void listTravelActivities(){
        int activityCount = 0;
        for (TravelActivity activity: travelActivities) {
            if (activity == null) {
                break;
            }
            String checked = activity.getActivityStatus()? "[X]" : "[ ]";
            activityCount++;
            if(activity.getTag()==null || activity.getTag().isEmpty()){
                System.out.println(checked + " " + activityCount +". " + activity);
            } else {
                System.out.println(checked + " " + activityCount + ". " + activity  + " (" + activity.getTag() + ")" );
            }
        }
        int finalactivityCount = noOfActivities;
        assert finalactivityCount == activityCount : "Index out of bounds while listing activities";
    }

    /**
     * returns the number of travel activities in the list
     * @return the number of travel activities
     */
    public int getNoOfTravelActivities(){
        return noOfActivities;
    }
    /**
     * Removes travel activity from the travel activity list
     * @param activityNumber The travel activity number on the list
     */
    public void removeTravelActivity(int activityNumber) throws OmniException{
        assert activityNumber != 0  :"There is not activities in the list";
        if(activityNumber > travelActivities.size()){
            throw new OmniException("Travel activity cannot be found!");
        }
        int indexOfActivity = activityNumber - 1;
        int initialListSize = noOfActivities;
        TravelActivity removedActivity = travelActivities.get(indexOfActivity);
        travelActivities.remove(indexOfActivity);
        System.out.println("I have removed this activity:");
        System.out.println(removedActivity);
        noOfActivities-=1;
        int newSize = noOfActivities;
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
     * @param activityName keyword specified by the user to find activities in the TravelActivity list
     *                 related to the keyword.
     */

    public void searchKeyword (String activityName) {
        logger.log(Level.INFO, "searchKeyword function started");
        ArrayList<TravelActivity> temporaryArray = new ArrayList<TravelActivity>();;
        int temporaryArrayCounter = 0;
        logger.log(Level.INFO, "temporaryArray is initialised");
        boolean isFound = false;
        logger.log(Level.INFO, "isFound is initialised to false");
        logger.log(Level.INFO, "Search for activities related to activityName given by user started");
        for (int iterator = 0; iterator < travelActivities.size(); iterator += 1){
            if(travelActivities.get(iterator).getPlan().contains(activityName)){
                temporaryArray.add(temporaryArrayCounter ,travelActivities.get(iterator));
                temporaryArrayCounter += 1;
                logger.log(Level.INFO, "An activity is added to the temporaryArray");
                isFound = true;
            }
        }
        if (temporaryArrayCounter == 0 || isFound == false) {
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
     * @param activityNumber The travel activity number on the list
     */
    public void checkTravelActivity(int activityNumber) throws OmniException{

        assert activityNumber != 0 : "There is not activities in the list";
        if (activityNumber > travelActivities.size()) {
            throw new OmniException("Travel activity cannot be found");
        }
        int indexOfActivity = activityNumber - 1;
        TravelActivity markedActivity  = travelActivities.get(indexOfActivity);
        markedActivity.setActivityStatus(true);
        System.out.println("I have checked this activity:");
        System.out.println(markedActivity);
    }

    /**
     * Unchecks travel activity and sets it to uncompleted
     * @param activityNumber The travel activity number on the list
     */
    public void uncheckTravelActivity(int activityNumber) throws OmniException{
        assert activityNumber != 0 : "There is not activities in the list";
        if (activityNumber > travelActivities.size()) {
            throw new OmniException("Travel activity cannot be found");
        }
        int indexOfActivity = activityNumber - 1;
        TravelActivity markedActivity  = travelActivities.get(indexOfActivity);
        markedActivity.setActivityStatus(false);
        System.out.println("I have unchecked this activity:");
        System.out.println(markedActivity);
    }


    /**
     * Adds a tag to travel activity
     * @param taskNumber The travel activity number on the list
     * @param tag The tag of travel activity
     */
    public void tagActivity(int taskNumber, String tag) throws OmniException {
        assert taskNumber != 0 : "There is no tasks in the list";
        if (taskNumber > travelActivities.size()) {
            throw new OmniException("Travel activity cannot be found");
        }
        int indexOfTask = taskNumber - 1;
        TravelActivity taggedTask = travelActivities.get(indexOfTask);
        taggedTask.setTag(tag);
        System.out.println("I have tagged this task:");
        System.out.println(taggedTask + " (" + tag + ")");
    }

    /**
     * Removes the tag on a travel activity
     * @param taskNumber The travel activity number on the list
     */
    public void removeTag(int taskNumber) throws OmniException {
        assert taskNumber != 0 : "There is no task in the list";
        if (taskNumber > travelActivities.size()) {
            throw new OmniException("Travel activity cannot be found");
        }
        int indexOfTask = taskNumber - 1;
        TravelActivity taggedTask = travelActivities.get(indexOfTask);
        taggedTask.removeTag();
        System.out.println("Tag removed from the task:");
        System.out.println(taggedTask);
    }

    public ArrayList<TravelActivity> getTravelActivities () {
        return travelActivities;
    }

}
