package seedu.lifetrack.liquids.liquidlist;

import seedu.lifetrack.liquids.Beverage;

public class LiquidEntry {

    private Beverage beverage;

    public LiquidEntry(Beverage beverage){
        this.beverage= beverage;
    }

    public Beverage getBeverage() {
        return beverage;
    }
}
