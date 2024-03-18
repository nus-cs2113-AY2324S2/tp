package ActiveEdge.Ui;

import ActiveEdge.Command.ActiveEdgeException;

public class GoalsUi {

    public static void printAddGoalMessage(String description, int goalAmount) {
        try {
            String goalType;
            String unit;
            if (description.equals("c")) {
                goalType = "calories";
                unit = "kcal";
            } else if (description.equals("w")) {
                goalType = "water";
                unit = "ml";
            } else {
                throw new ActiveEdgeException("Invalid goal description. Must be 'c' for calories or 'w' for water.");
            }
            System.out.println("Goal added: " + goalType + " with goal amount " + goalAmount + " " + unit);
        } catch (ActiveEdgeException e) {
            System.out.println("Error: " + e.warning);
        }
    }

    public static void printShowGoalsMessage(int calorieGoal, int waterGoal) {
        System.out.println("Current goals: Daily calories: " + calorieGoal + "kcal" + ", Daily water: " + waterGoal + "ml.");
    }
}
