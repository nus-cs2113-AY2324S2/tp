package ActiveEdge.Task;
import java.util.ArrayList;

public class LogMeals extends Task {

    protected String servings;

    protected String calories;

    public LogMeals(String description, String servings, String calories) {
        super(description);
        this.servings = servings;
        this.calories = calories;
    }

    int servingsNum = Integer.parseInt(servings);
    int caloriesNum = Integer.parseInt(calories);
    int mealCalories = servingsNum * caloriesNum;
    static ArrayList<Integer> calorieTracker = new ArrayList<>();

    public static int addCalories(int mealCalories) {
        int totalCalories = 0;
        calorieTracker.add(mealCalories);
        for (int i = 0; i < calorieTracker.size(); i++) {
            totalCalories = totalCalories + calorieTracker.get(i);
        }
        return totalCalories;
    }


    public String toString() {
        return "You've logged " + servings + " serving of " + super.getDescription() + ".\n" +
                "Estimated calories: " + mealCalories +"\n" +
                "Total calories today: " + addCalories(mealCalories);
    }

}
