package activeedge.task;

/**
 * Represents an exercise task that extends the functionality of a basic task
 * to include exercise-specific details. It captures the name, duration, and
 * calories burnt of an exercise activity.
 */
public class ExerciseTask extends Task{
    protected String exerciseName;
    protected int duration;
    protected Integer caloriesBurnt;

    /**
     * Constructs a new exercise log with the specified exercise name, duration, and calories burnt.
     *
     * @param exerciseName the name of the exercise
     * @param duration the duration of the exercise, in minutes
     * @param caloriesBurnt the number of calories burnt during the exercise
     */
    public ExerciseTask(String exerciseName, int duration, int caloriesBurnt){
        super(exerciseName);
        this.duration = duration;
        this.caloriesBurnt = caloriesBurnt;
    }
    public String getExerciseName() { return description; }

    public int getDuration(){
        return duration;
    }

    public int getCaloriesBurnt(){
        return caloriesBurnt;
    }

    /**
     * Returns a string representation of the exercise task, including its name,
     * duration, and calories burnt.
     */
    @Override
    public String toString() {
        return "Exercise " + this.getExerciseName() + " " + this.getDuration() + " " + this.getCaloriesBurnt();
    }
}
