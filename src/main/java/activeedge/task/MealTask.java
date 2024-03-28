package activeedge.task;
import java.time.LocalDateTime;
import java.util.Optional;
public class MealTask extends Task {
    protected Integer servings;
    protected Integer mealCalories;

    protected LocalDateTime dateTime;


    public MealTask(String meal, int servings, int mealCalories, LocalDateTime dateTime ){
        super(meal);
        this.servings = Optional.ofNullable(servings).orElse(0);
        this.mealCalories = Optional.ofNullable(mealCalories).orElse(0);
        this.dateTime = dateTime;
    }
    public String getFoodName() {
        return description;
    }


    public int getServings(){
        return servings;
    }

    public int getMealCalories() {
        return mealCalories;
    }


    @Override
    public String toString() {
        return "Meal " + this.getDescription() + " " + this.getServings() + " "
                + this.getMealCalories() + " kcal (Recorded on: " + dateTime + ")";
    }
}
