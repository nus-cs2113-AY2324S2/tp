package seedu.voyagers.classes;

import seedu.voyagers.utils.Currency;

//TODO: update so each person can get a list of the bills they still owe.

public class Profile {
    private String name;
    private Currency currency;

    public Profile(String name, Currency currency) {
        this.name = name;
        this.currency = currency;
    }

    public boolean equals(Profile p) {
        if (this.name.equals(p.name)) {
            return true;
        } else {
            return false;
        }
    }

    //TODO
    public Bill billsOwed() {
        return null;
    }

    //TODO
    public Bill billsPaid() {
        return null;
    }

    public String getName() {
        return name;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }
}
