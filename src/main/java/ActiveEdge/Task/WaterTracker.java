package ActiveEdge.Task;
import java.util.ArrayList;


public class WaterTracker extends Task {
    private int quantity; // Amount of water consumed in milliliters

    public WaterTracker(String description, int quantity) {
        super(description);
        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
    }
}

