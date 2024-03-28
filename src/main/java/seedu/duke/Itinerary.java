package seedu.duke;

public class Itinerary {
    private Food food1;
    private Food food2;
    private Activity activity1;
    private Activity activity2;

    public Itinerary(Food food1, Food food2, Activity activity1, Activity activity2) {
        this.food1 = food1;
        this.food2 = food2;
        this.activity1 = activity1;
        this.activity2 = activity2;
    }

    @Override
    public String toString() {
        return "Here is a rough itinerary for your date: \n" + 
                "We begin with lunch at " + food1 + ", followed by some fun at " + activity1 + ".\n" +
                "We proceed to have dinner at " + food2 + ", and finish the night at " + activity2 + ".\n";
    }
}
