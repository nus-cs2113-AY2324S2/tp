package ActiveEdge.Task;

public class LogMeals extends Task {
    protected Integer servings;
    //protected int mealCalories;

    public LogMeals (int servings, /*int mealCalories,*/ String description) {
        super(description);
    }
    protected Integer mealCalories;

    public LogMeals (String meal, int servings, int mealCalories){
        super(meal);
        this.servings = servings;
        this.mealCalories = mealCalories;
    }
    public String getFoodName() { return description; }

    public int getServings(){
        return servings;
    }

    public int getMealCalories() { return mealCalories; }

    @Override
    public String toString() {
        return "Meal " + this.getDescription() + " " + this.getServings() + " " + this.getMealCalories();
    }
}
