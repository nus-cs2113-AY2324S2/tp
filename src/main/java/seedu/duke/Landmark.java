package seedu.duke;

import java.time.LocalDate;

public class Landmark extends TravelActivity {
    public Landmark(String line, LocalDate date, String duration){
        super(line, date, duration);
    }
    @Override
    public String toString(){
        return "Landmark: " + super.toString();
    }
}
