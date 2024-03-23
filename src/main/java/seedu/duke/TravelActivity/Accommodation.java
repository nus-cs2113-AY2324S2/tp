package seedu.duke.TravelActivity;

public class Accommodation extends TravelActivity {

    public Accommodation(String line){
        super(line);
    }

    @Override
    public String toString(){
        return "Accommodation: " + super.toString();
    }

}
