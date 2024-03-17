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
        travelActivities.add(travelActivity);
        noOfTasks += 1;
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
            taskCount++;
            System.out.println("     " + taskCount +"." + task);
        }
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
    public void removeTravelActivity(int taskNumber){
        int indexOfTask = taskNumber - 1;
        TravelActivity removedTask = travelActivities.get(indexOfTask);
        travelActivities.remove(indexOfTask);
        System.out.println("I have removed this task:");
        System.out.println(removedTask);
        noOfTasks -=1;

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
        for(int iterator = 0; iterator < travelActivities.size(); iterator += 1){
            if(travelActivities.get(iterator).getPlan().contains(taskName)){
                temporaryArray.add(temporaryArrayCounter ,travelActivities.get(iterator));
                temporaryArrayCounter += 1;
            }
        }
        if (temporaryArrayCounter == 0) {
            System.out.println("Sorry I could not find what you are looking for.");
        }
        else {
            System.out.println("Here are what you are looking for:");
            for (int newIterator = 0; newIterator < temporaryArray.size(); newIterator += 1) {
                System.out.println((newIterator + 1) + ". " + temporaryArray.get(newIterator).getPlan());
            }
        }
    }


}
