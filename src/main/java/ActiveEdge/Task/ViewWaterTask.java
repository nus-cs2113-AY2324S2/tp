package ActiveEdge.Task;

import java.util.ArrayList;

public class ViewWaterTask {
    public static int getTotalWaterIntake(ArrayList<Task> tasksList) {
        int totalWaterIntake = 0;
        for (Task task : tasksList) {
            if (task instanceof LogWaterTask) {
                totalWaterIntake += ((LogWaterTask) task).getQuantity();
            }
        }
        return totalWaterIntake;
    }
}
