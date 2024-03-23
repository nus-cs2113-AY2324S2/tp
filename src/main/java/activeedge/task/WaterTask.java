package activeedge.task;


/**
 * Represents a task related to water intake.
 * */
public class WaterTask extends Task {
    private int quantity;

    /**
     * Constructs a WaterTask object with the specified quantity.
     *
     * @param quantity The quantity of water intake.
     */
    public WaterTask(int quantity) {
        super("Water intake");
        this.quantity = quantity;
    }

    /**
     * Gets the quantity of water intake.
     *
     * @return The quantity of water intake.
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Returns a string representation of the WaterTask object.
     *
     * @return A string representing the water task and its quantity.
     */
    @Override
    public String toString() {
        return "Water " + this.getQuantity();
    }

}
