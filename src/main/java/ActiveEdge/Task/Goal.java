package ActiveEdge.Task;

public class Goal extends Task {
    private static int dailyCalorieGoal = 0;
    private static int dailyWaterGoal = 0;

    public Goal(String description) {
        super(description);
    }

    public void setDailyCalorieGoal(int goal) {
        dailyCalorieGoal = goal;
    }

    public void setDailyWaterGoal(int goal) {
        dailyWaterGoal = goal;
    }

    public int getDailyCalorieGoal() {
        return dailyCalorieGoal;
    }

    public int getDailyWaterGoal() {
        return dailyWaterGoal;
    }
}
