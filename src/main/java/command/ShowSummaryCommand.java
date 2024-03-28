package command;

import activeedge.task.Task;
import activeedge.ui.CommandUi;

import static activeedge.task.TaskList.tasksList;

public class ShowSummaryCommand {
    public void execute() {
        int totalCalories = 0;
        int totalWaterIntake = 0;
        int totalCaloriesBurnt = 0;

        String calorieGoal = "0";
        String waterGoal = "0";

        for (Task task : tasksList) {
            if (task.toString().startsWith("Meal")) {
                String[] parts = task.toString().split(" ");
                totalCalories += Integer.parseInt(parts[parts.length - 1]);
            } else if (task.toString().startsWith("Water")) {
                String[] parts = task.toString().split(" ");
                totalWaterIntake += Integer.parseInt(parts[parts.length - 1]);
            } else if (task.toString().startsWith("Exercise")) {
                String[] parts = task.toString().split(" ");
                totalCaloriesBurnt += Integer.parseInt(parts[parts.length - 1]);
            } else if (task.toString().startsWith("Goal")) {
                String[] parts = task.toString().split(" ");
                if (parts[1].equals("c")) {
                    calorieGoal = parts[2];
                } else if (parts[1].equals("w")) {
                    waterGoal = parts[2];
                }
            }
        }

        int netCalories = totalCalories - totalCaloriesBurnt;
        String calorieStatus = calculateCalorieStatus(netCalories, Integer.parseInt(calorieGoal));

        CommandUi.printShowSummaryMessage(totalCalories,totalWaterIntake, totalCaloriesBurnt,
                calorieGoal,waterGoal,netCalories, calorieStatus);
    }

    private String calculateCalorieStatus(int netCalories, int calorieGoal) {
        if (netCalories > calorieGoal) {
            return "Calories Surplus";
        } else if (netCalories < calorieGoal) {
            return "Calories Deficit";
        } else {
            return "Maintenance";
        }
    }
}
