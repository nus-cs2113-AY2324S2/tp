package seedu.duke;

public class TravelActivity {
    /** Travel activity description */
    private String travelActivity;
    /** Travel activity status */
    private boolean activityIsDone = false;
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
