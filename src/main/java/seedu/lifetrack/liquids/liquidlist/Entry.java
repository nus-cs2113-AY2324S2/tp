package seedu.lifetrack.liquids.liquidlist;

import seedu.lifetrack.liquids.beverage.Beverage;

public class Entry {

    private Beverage beverage;

    public Entry(Beverage beverage){
        this.beverage= beverage;
    }

    public Beverage getBeverage() {
        return beverage;
    }
}
