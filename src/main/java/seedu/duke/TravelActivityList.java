package seedu.duke;
import java.util.ArrayList;

public class TravelActivityList {
    /** Array of travel activity */
    private ArrayList<TravelActivity> travelActivities;

    /** Number of TravelActivities */
    private int noOfTasks = 0;
    public TravelActivityList() {
        travelActivities = new ArrayList<>();
    }

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

    public void searchKeyword (String taskName) {
        ArrayList<TravelActivity> temporaryArray = new ArrayList<TravelActivity>();;
        int temporaryArrayCounter = 0;
        boolean isFound = false;
        for (int iterator = 0; iterator < travelActivities.size(); iterator += 1){
            if(travelActivities.get(iterator).getPlan().contains(taskName)){
                temporaryArray.add(temporaryArrayCounter ,travelActivities.get(iterator));
                temporaryArrayCounter += 1;
            }
        }
        if (temporaryArrayCounter == 0) {
            System.out.println("Sorry I could not find what you are looking for.");
        } else {
            System.out.println("Here are what you are looking for:");
            for (int newIterator = 0; newIterator < temporaryArray.size(); newIterator += 1) {
                System.out.println((newIterator + 1) + ". " + temporaryArray.get(newIterator).getPlan());
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

    /**
     * Adds a tag to travel activity
     * @param taskNumber The travel activity number on the list
     * @param tag The tag of travel activity
     */
    public void tagActivity(int taskNumber, String tag) throws OmniException{
        assert taskNumber != 0 : "There is no tasks in the list";
        if (taskNumber > travelActivities.size()) {
            throw new OmniException("Travel activity cannot be found");
        }
        int indexOfTask = taskNumber - 1;
        TravelActivity taggedTask  = travelActivities.get(indexOfTask);
        taggedTask.setTag(tag);
        System.out.println("I have tagged this task:");
        System.out.println(taggedTask + "(" + tag + ")");
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
    
}
