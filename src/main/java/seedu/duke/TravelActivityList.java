package seedu.duke;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.logging.Logger;


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
        //logger.log(Level.INFO, "addKeyword function started");
        int initialListSize = noOfActivities;
        travelActivities.add(travelActivity);
        //logger.log(Level.INFO, "travelActivity is added");
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
            if((activity.getTag() == null || activity.getTag().isEmpty()) &&
                    (activity.getExpense() == null || activity.getExpense().isEmpty())){
                System.out.println(checked + " " + activityCount + ". " + activity);
            } else if (!(activity.getTag() == null || activity.getTag().isEmpty())) {
                System.out.println(checked + " " + activityCount + ". " + activity  + " (" + activity.getTag() + ")");
            } else if (!(activity.getExpense() == null || activity.getExpense().isEmpty())) {
                System.out.println(checked + " " + activityCount + ". " + activity
                        + " (" + activity.getExpense() + ")");
            } else {
                System.out.println(checked + " " + activityCount + ". " + activity
                        + " (" + activity.getTag() + ")" + " (" + activity.getExpense() + ")");
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
        boolean isFound = false;
        int foundCounter = 0;
        for (int iterator = 0; iterator < travelActivities.size(); iterator += 1){
            if(travelActivities.get(iterator).getPlan().contains(activityName) &&
                    !travelActivities.get(iterator).getPlan().isEmpty()){
                isFound = true;
                foundCounter += 1;
                if (isFound && foundCounter == 1) {
                    System.out.println("Here are what you are looking for:");
                }
                if (travelActivities.get(iterator).getTag() == "") {
                    System.out.println(foundCounter + ". " + travelActivities.get(iterator).toString());
                } else {
                    System.out.println(foundCounter + ". " + travelActivities.get(iterator).toString() +
                            " (" + travelActivities.get(iterator).getTag() + ")");
                }
            }
        }
        if (foundCounter == 0) {
            System.out.println("Sorry I could not find what you are looking for.");
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

    /**
     * Updates the date, duration and tag of the travel activity
     * @param travelActivityNumber The index of the travel activity
     * @param date The new date of the travel activity
     * @param duration The new duration of the travel activity
     * @param tag The new tag of the travel activity
     * @throws OmniException Thrown if the index of the travel activity cannot be found
     */
    public void updateTravelActivity(int travelActivityNumber, LocalDate date, String duration, String tag)
            throws OmniException{
        if (travelActivityNumber > travelActivities.size() || (travelActivityNumber==0 && travelActivities.isEmpty())
            || travelActivityNumber < 0){
            throw new OmniException("Travel activity cannot be found");
        }
        int indexOfTravelActivity = travelActivityNumber-1;
        TravelActivity updatedTravelActivity = travelActivities.get(indexOfTravelActivity);
        String oldTravelActivity = (updatedTravelActivity.toString()
                                            + " (" + updatedTravelActivity.getTag() + ")");
        updatedTravelActivity.setDate(date);
        updatedTravelActivity.setDuration(duration);
        updatedTravelActivity.setTag(tag);
        System.out.println("I have updated this task\nfrom: " + oldTravelActivity +
                            "\nto: " + updatedTravelActivity + " (" + updatedTravelActivity.getTag() + ")");
    }

    public ArrayList<TravelActivity> getTravelActivities () {
        return travelActivities;
    }

    /**
     * Find all the tasks with a particular tag and prints them out
     *
     * @param tag The tag of tasks that the user wants to find
     */

    public void findTag(String tag){
        boolean isFound = false;
        int foundCounter = 0;
        for (int iterator = 0; iterator < travelActivities.size(); iterator += 1){
            if(travelActivities.get(iterator).getTag().contains(tag) &&
                    !travelActivities.get(iterator).getTag().isEmpty()){
                isFound = true;
                foundCounter += 1;
                if (isFound && foundCounter == 1) {
                    System.out.println("Here are what you are looking for:");
                }
                if (travelActivities.get(iterator).getTag() == "") {
                    System.out.println(foundCounter + ". " + travelActivities.get(iterator).toString());
                } else {
                    System.out.println(foundCounter + ". " + travelActivities.get(iterator).toString() +
                            " (" + travelActivities.get(iterator).getTag() + ")");
                }
            }
        }
        if (foundCounter == 0 || isFound == false) {
            System.out.println("Sorry I could not find what you are looking for.");
        }
    }

    /**
     * Find all the tasks of a particular type and prints them out
     *
     * @param type The type of tasks that the user wants to find
     */

    public void findType(String type){
        boolean isFound = false;
        int foundCounter = 0;

        for (int iterator = 0; iterator < travelActivities.size(); iterator += 1){
            if(type.equals("Food") && travelActivities.get(iterator) instanceof Food &&
                    !travelActivities.get(iterator).toString().isEmpty()){
                isFound = true;
                foundCounter += 1;
                if (isFound && foundCounter == 1) {
                    System.out.println("Here are what you are looking for:");
                }
                if (travelActivities.get(iterator).getTag() == "") {
                    System.out.println(foundCounter + ". " + travelActivities.get(iterator).toString());
                } else {
                    System.out.println(foundCounter + ". " + travelActivities.get(iterator).toString() +
                            " (" + travelActivities.get(iterator).getTag() + ")");
                }
            } else if (type.equals("Landmark") && travelActivities.get(iterator) instanceof Landmark &&
                    !travelActivities.get(iterator).toString().isEmpty()) {
                isFound = true;
                foundCounter += 1;
                if (isFound && foundCounter == 1) {
                    System.out.println("Here are what you are looking for:");
                }
                if (travelActivities.get(iterator).getTag() == "") {
                    System.out.println(foundCounter + ". " + travelActivities.get(iterator).toString());
                } else {
                    System.out.println(foundCounter + ". " + travelActivities.get(iterator).toString() +
                            " (" + travelActivities.get(iterator).getTag() + ")");
                }
            } else if (type.equals("Accommodation") && travelActivities.get(iterator) instanceof Accommodation &&
                    !travelActivities.get(iterator).toString().isEmpty()) {
                isFound = true;
                foundCounter += 1;
                if (isFound && foundCounter == 1) {
                    System.out.println("Here are what you are looking for:");
                }
                if (travelActivities.get(iterator).getTag() == "") {

                    System.out.println(foundCounter + ". " + travelActivities.get(iterator).toString());
                } else {
                    System.out.println(foundCounter + ". " + travelActivities.get(iterator).toString() +
                            " (" + travelActivities.get(iterator).getTag() + ")");
                }
            }
        }
        if (foundCounter == 0 || isFound == false) {
            System.out.println("Sorry I could not find what you are looking for.");
        }
    }

    /**
     * Adds expense to travel activity
     * @param taskNumber The travel activity number on the list
     * @param expense  The expense of travel activity
     */
    public void expenseActivity(int taskNumber, String expense) throws OmniException {
        assert taskNumber != 0 : "There is no tasks in the list";
        if (taskNumber > travelActivities.size()) {
            throw new OmniException("Travel activity cannot be found");
        }
        int indexOfTask = taskNumber - 1;
        TravelActivity task = travelActivities.get(indexOfTask);
        task.setExpense(expense);
        System.out.println("I have added expense for this task:");
        System.out.println(task + " (" + expense + ")");
    }

    /**
     * Removes the expense on a travel activity
     * @param taskNumber The travel activity number on the list
     */
    public void removeExpense(int taskNumber) throws OmniException {
        assert taskNumber != 0 : "There is no task in the list";
        if (taskNumber > travelActivities.size()) {
            throw new OmniException("Travel activity cannot be found");
        }
        int indexOfTask = taskNumber - 1;
        TravelActivity task = travelActivities.get(indexOfTask);
        task.removeExpense();
        System.out.println("Expense removed from the task:");
        System.out.println(task);
    }


}
