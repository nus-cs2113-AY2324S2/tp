package seedu.duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class TravelActivity {
    /** Travel activity description */
    private String travelActivity;
    /** Travel activity date */
    private LocalDate date;
    /** Travel activity duration */
    private String duration;
    /** Travel activity status */
    private boolean activityIsDone = false;
    /** Travel activity tag */
    private String tag;
    public TravelActivity(String description, LocalDate date, String duration){
        travelActivity = description;
        this.date = date;
        this.duration = duration;
    }

    @Override
    public String toString(){
        return travelActivity + " :" + date.format(DateTimeFormatter.ofPattern("d MMM yyyy")) + " :" + duration;
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

    public void setDate(LocalDate date){
        this.date = date;
    }

    public LocalDate getDate(){
        return date;
    }

    public void setDuration(String duration){
        this.duration = duration;
    }

    public String getDuration(){
        return duration;
    }

}

