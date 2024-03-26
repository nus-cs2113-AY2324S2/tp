package seedu.duke;

import java.time.LocalDate;

public class Accommodation extends TravelActivity {
    private LocalDate date;
    private String duration;

    public Accommodation(String line, LocalDate date, String duration, String Tag){
        super(line, date, duration, Tag);
    }

    @Override
    public String toString(){
        return "Accommodation: " + super.toString();
    }

}
