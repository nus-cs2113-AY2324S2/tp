package seedu.duke;

public class Water {
    private int waterIntake = 0;

    public Water(int waterIntake) {
        this.waterIntake += waterIntake;
    }

    public void viewWater() {
        System.out.println("Total water intake: " + waterIntake + " ml");
    }

    public int getWater() {
        return waterIntake;
    }

}
