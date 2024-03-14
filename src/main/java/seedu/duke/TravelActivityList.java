package seedu.duke;
import java.util.ArrayList;

public class TravelActivityList {
    /** Array of travel activity */
    private ArrayList<TravelActivity> travelActivities;

    /** Number of TravelActivitys */
    private int noOfTasks = 0;
    public TravelActivityList() {
        travelActivities = new ArrayList<>();
    }

    /**
     * Adds travel activity to the travel activity list
     * @param travelActivity The travel activity
     */
    public void addTask(TravelActivity travelActivity){
        travelActivities.add(travelActivity);
    }

    /**
     * Prints out all the task
     */
    public void listTasks(){
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
     * returns the number of tasks in the list
     * @return the number of tasks
     */
    public int getNoOfTasks(){
        return noOfTasks;
    }

    /**
     * Removes task from the task list
     *
     * @param taskNumber The task number
     */
    public void removeTask(int taskNumber){
        int indexOfTask = taskNumber - 1;
        TravelActivity removedTask = travelActivities.get(indexOfTask);
        travelActivities.remove(indexOfTask);
        System.out.println("I have removed this task:");
        System.out.println(removedTask);

    }

}
