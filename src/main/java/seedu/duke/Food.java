package seedu.duke;

import java.time.LocalDate;

public class Food extends TravelActivity {
    public Food(String line, LocalDate date, String duration){
        super(line, date, duration);
    }

    @Override
    public String toString(){
        return "Food: " + super.toString();
    }
}
