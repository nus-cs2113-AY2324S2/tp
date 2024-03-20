package seedu.duke;

public class TravelActivity {
    /** Travel activity description */
    private String travelActivity;
    /** Travel activity status */
    private boolean taskIsDone = false;
    /** Travel activity tag */
    private String tag;

    public TravelActivity(String line){
        travelActivity = line;
    }

    @Override
    public String toString(){
        return travelActivity;
    }

    /**
     * Sets the task status of the task to complete or incomplete
     * @param taskIsDone Task status
     */
    public void setTaskStatus(boolean taskIsDone){
        this.taskIsDone = taskIsDone;
    }

    /**
     * Gets the description of the travel activity
     * @return The description of the travel activity
     */
    public String getPlan(){
        return travelActivity;
    }

    public boolean getTaskStatus() {
        return taskIsDone;
    }

    /**
     * Gets the tag of the travel activity
     * @return The tag of the travel activity
     */
    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public void removeTag() {
        this.tag = "";
    }

}
