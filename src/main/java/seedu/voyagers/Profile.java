package seedu.voyagers;

public class Profile {
    private String name;
    private Currency currency;

    public Profile(String name, Currency currency) {
        this.name = name;
        this.currency = currency;
    }

    public String getName() {
        return name;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }
}
