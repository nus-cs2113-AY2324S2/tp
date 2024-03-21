package seedu.lifetrack.liquids;

public class Liquid {
    private int liquids;

    private String beverage;

    public Liquid (int liquids, String beverage){
        this.liquids = liquids;
        this.beverage = beverage;
    }

    public int getVolume() {
        return liquids;
    }

    public String getBeverage() {
        return beverage;
    }
}
