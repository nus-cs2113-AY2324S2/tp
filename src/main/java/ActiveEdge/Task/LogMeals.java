package ActiveEdge.Task;

import static ActiveEdge.Task.TaskList.tasksList;
import ActiveEdge.Task.Task;

public class LogMeals extends Task {

    /* protected String servings;

    protected String calories;

    public LogMeals(String description, String servings, String calories) {
        super(description);
        this.servings = servings;
        this.calories = calories;
    } */

//    int servingsNum = Integer.parseInt(servings);
//    int caloriesNum = Integer.parseInt(calories);
//    int mealCalories = servingsNum * caloriesNum;
//
//    public static int totalCalories;
//
//    public int addCalories() {
//        if(tasksList.size()==0) {
//            totalCalories = 0;
//        } else {
//            totalCalories += mealCalories;
//        }
//        return totalCalories;
//    }
//
//    /* public String toString() {
//        return "You've logged " + servings + " serving of " + super.getDescription() + ".\n" +
//                "Estimated calories: " + mealCalories +"\n" +
//                "Total calories: " + totalCalories;
//    } */
//
//    public int getServings() { return servingsNum; }
//    public int getMealCalories () { return mealCalories; }
//    public int getTotalCalories() { return totalCalories; }


    protected Integer servings;
    //protected int mealCalories;
 
    public LogMeals (int servings, /*int mealCalories,*/ String description) {
        super(description);
        this.servings = servings;
        //this.mealCalories = mealCalories;

    }

    public int getServings() { return servings; }

    //public int getMealCalories() { return mealCalories; }

    public String getFoodName() { return description; }
  
    protected String description;
    protected Integer servings;
    //protected int mealCalories;

    public LogMeals (int servings, /*int mealCalories,*/ String description) {
        super("Log Meal");
        this.servings = servings;
        //this.mealCalories = mealCalories;
        this.description = description;
    }

    public int getServings() { return servings; }

    //public int getMealCalories() { return mealCalories; }

    public String getDescription() { return description; }

}
