package seedu.lifetrack.liquids.beverage;

public class Beverage {
    private String beverage;
    private int volume;

    public Beverage(String beverage, int volume){
        this.beverage = beverage;
        this.volume = volume;

    }

    public String getBeverage() {
        return beverage;
    }

    public int getVolume() {
        return volume;
    }
}
