package byteceps.activities;

public class ExerciseLog extends Activity {
    private final int weight;
    private final int sets;
    private final int repetitions;

    public ExerciseLog(String activityName, int weight, int sets, int repetitions) {
        super(activityName);
        this.weight = weight;
        this.sets = sets;
        this.repetitions = repetitions;
    }


    public int getSets() {
        return sets;
    }

    public int getRepetitions() {
        return repetitions;
    }

    public int getWeight() {
        return weight;
    }
}
