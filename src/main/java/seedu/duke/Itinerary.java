package seedu.duke;

public class Itinerary {
    private Food food;
    private Activity activity;

    public Itinerary(Food food, Activity activity) {
        this.food = food;
        this.activity = activity;
    }

    @Override
    public String toString() {
        return "You can do " + activity + " and have a nice meal at " + food;
    }
}
