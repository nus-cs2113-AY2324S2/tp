package ActiveEdge.Command;

import ActiveEdge.Task.Goal;
import ActiveEdge.Ui.GoalsUi;


public class ShowGoalsCommand extends Command {
    private Goal goalSetting;

    public ShowGoalsCommand(Goal goalSetting) {
        this.goalSetting = goalSetting;
    }

    @Override
    public void execute() {
        int calorieGoal = goalSetting.getDailyCalorieGoal();
        int waterGoal = goalSetting.getDailyWaterGoal();
        GoalsUi.printShowGoalsMessage();
    }
}

