package seedu.lifetrack.calories;

public class Calorie {

    private int calories;
    private boolean isIntake;
    public Calorie (int calories, boolean isIntake){
        this.calories = calories;
        this.isIntake = isIntake;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public boolean isIntake() {
        return isIntake;
    }

    public void setIntake(boolean intake) {
        isIntake = intake;
    }
}
