package ActiveEdge.Command;

import ActiveEdge.Task.Goal;
import ActiveEdge.Ui.GoalsUi;


public class AddGoalsCommand extends Command {
    private String input;
    private Goal goalSetting;

    public AddGoalsCommand(String input) {
        this.input = input;
    }

    @Override
    public void execute() {
        if (input.startsWith("set goal c/")) {
            goalSetting.setDailyCalorieGoal(goalAmount);
            GoalsUi.printAddGoalMessage(calorieGoalTask.getDescription(), calorieGoalTask.getGoalAmount());
        } else if (input.startsWith("set goal w/")) {
            goalSetting.setDailyWaterGoal(goalAmount);
            GoalsUi.printAddGoalMessage(waterGoalTask.getDescription(), waterGoalTask.getGoalAmount());
        } else {
            System.out.println("Invalid command.");
        }
    }
}