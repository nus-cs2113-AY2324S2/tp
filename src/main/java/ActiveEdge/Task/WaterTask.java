package ActiveEdge.Task;

import java.util.ArrayList;

public class WaterTask extends Task {
    private int quantity;

    public WaterTask(int quantity) {
        super("Water intake");
        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
    }

    @Override
    public String toString() {
        return "Water " + this.getQuantity();
    }

    public static int getTotalWaterIntake(ArrayList<Task> tasksList) {
        int totalWaterIntake = 0;
        for (Task task : tasksList) {
            if (task instanceof WaterTask) {
                totalWaterIntake += ((WaterTask) task).getQuantity();
            }
        }
        return totalWaterIntake;
    }
}
