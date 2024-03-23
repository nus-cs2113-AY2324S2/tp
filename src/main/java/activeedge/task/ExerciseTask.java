package activeedge.task;

public class ExerciseTask extends Task{
    protected String exerciseName;
    protected int duration;
    protected Integer caloriesBurnt;

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

    @Override
    public String toString() {
        return "Exercise " + this.getExerciseName() + " " + this.getDuration() + " " + this.getCaloriesBurnt();
    }
}
