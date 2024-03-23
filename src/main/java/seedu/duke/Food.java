package seedu.duke;

public class Food extends TravelActivity {
    public Food(String line){
        super(line);
    }

    @Override
    public String toString(){
        return "Food: " + super.toString();
    }
}
