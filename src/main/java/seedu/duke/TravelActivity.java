package seedu.duke;

public class TravelActivity {
    /** Travel activity description */
    private String travelActivity;
    /** Travel activity status */
    private boolean activityIsDone = false;
    public TravelActivity(String line){
        travelActivity = line;
    }

    @Override
    public String toString(){
        return travelActivity;
    }

    /**
     * Sets the status of the activity to complete or incomplete
     * @param activityIsDone activity status
     */
    public void setActivityStatus(boolean activityIsDone){
        this.activityIsDone = activityIsDone;
    }

    /**
     * Gets the description of the travel activity
     * @return The description of the travel activity
     */
    public String getPlan(){
        return travelActivity;
    }

    public boolean getActivityStatus() {
        return activityIsDone;
    }

}
