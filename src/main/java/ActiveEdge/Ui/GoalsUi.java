package ActiveEdge.Ui;

public class GoalsUi {

    public static void printAddGoalMessage(String description, int goalAmount) {
        System.out.println("Goal added: " + description + " with goal amount " + goalAmount);
    }

    public static void printShowGoalsMessage(int calorieGoal, int waterGoal) {
        System.out.println("Current goals: Daily calories: " + calorieGoal + ", Daily water: " + waterGoal + "ml.");
    }
}

