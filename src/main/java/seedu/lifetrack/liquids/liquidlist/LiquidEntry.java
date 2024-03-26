package seedu.lifetrack.liquids.liquidlist;

import seedu.lifetrack.liquids.Beverage;

public class LiquidEntry {
    private String description;
    private int volume;
    private String date;
    public LiquidEntry(String description, int volume, String date){
        this.description = description;
        this.volume = volume;
        this.date = date;
    }
    public String getDescription() {
        return description;
    }

    public int getVolume() {
        return volume;
    }

    public String getDate() {
        return date;
    }
}
