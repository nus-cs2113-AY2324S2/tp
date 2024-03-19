package seedu.lifetrack.calories.activity;

public class Activity {

    private String date;
    private String time;
    private String description;

    public Activity(String date, String time,String description){
        this.date = date;
        this.time = time;
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public String getDescription() {
        return description;
    }
}
