package seedu.duke.TravelActivity;

public class Landmark extends TravelActivity {
    public Landmark(String line){
        super(line);
    }
    @Override
    public String toString(){
        return "Landmark: " + super.toString();
    }
}
