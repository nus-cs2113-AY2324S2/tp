package seedu.lifetrack.liquids.liquidlist;

import seedu.lifetrack.Entry;

public class LiquidEntry extends Entry {

    private int volume;

    public LiquidEntry(String description, int volume, String date){
        super(description, date);
        this.volume= volume;
    }

    public int getVolume() {
        return volume;
    }

    public String toString() {
        return String.format(super.toString() + ", Volume: " + volume);
    }

    public String toFileFriendlyString() {
        return String.format(super.toFileFriendlyString() + ";" + volume);
    }
}
