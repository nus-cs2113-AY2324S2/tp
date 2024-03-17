package ActiveEdge.Task;

public class LogWaterTask extends Task {
    private int quantity;

    public LogWaterTask(int quantity) {
        super("Water intake");
        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
    }

    @Override
    public String toString() {
        return "Water " + this.getQuantity() ;
    }
}

