package activeedge.task;
import java.time.LocalDateTime;


/**
 * Represents a task related to water intake.
 * */
public class WaterTask extends Task {
    private int quantity;
    private LocalDateTime dateTime;


    /**
     * Constructs a WaterTask object with the specified quantity.
     *
     * @param quantity The quantity of water intake.
     */
    public WaterTask(int quantity, LocalDateTime dateTime) {
        super("Water intake");
        this.quantity = quantity;
        this.dateTime = dateTime;
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
        return "Water " + this.getQuantity() + " ml"
                + " (Recorded on: " + dateTime + ")";
    }



}
