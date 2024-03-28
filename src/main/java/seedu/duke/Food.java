package seedu.duke;

import java.time.LocalDate;

public class Food extends TravelActivity {
    public Food(String line, LocalDate date, String duration, String tag){
        super(line, date, duration, tag);
    }

    @Override
    public String toString(){
        return "Food: " + super.toString();
    }
}
