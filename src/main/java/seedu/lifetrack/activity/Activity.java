package seedu.lifetrack.activity;

public class Activity {

    private String date;
    private String time;
    private String description;

    public Activity(String date, String time,String description){
        this.date= date;
        this.time= time;
        this.description=description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
