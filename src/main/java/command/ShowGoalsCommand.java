package command;

import activeedge.task.GoalTask;
import activeedge.task.Task;
import activeedge.task.TaskList;
import activeedge.ui.GoalsUi;

public class ShowGoalsCommand  {

    public void execute() {
        int calorieGoal = 0;
        int waterGoal = 0;
        for (Task task : TaskList.tasksList) {
            if (task instanceof GoalTask) {
                GoalTask goalTask = (GoalTask) task;
                if (goalTask.getDescription().startsWith("c")) {
                    calorieGoal = goalTask.getGoalAmount();
                } else if (goalTask.getDescription().startsWith("w")) {
                    waterGoal = goalTask.getGoalAmount();
                }
            }
        }
        GoalsUi.printShowGoalsMessage(calorieGoal, waterGoal);
    }
}
