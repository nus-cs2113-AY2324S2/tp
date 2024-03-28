package seedu.duke;

import java.time.LocalDate;

public class Landmark extends TravelActivity {
    public Landmark(String line, LocalDate date, String duration, String tag){
        super(line, date, duration, tag);
    }
    @Override
    public String toString(){
        return "Landmark: " + super.toString();
    }
}
