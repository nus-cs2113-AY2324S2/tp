package seedu.grocery;

public class Grocery {
    private String name;
    private String amount;
    private String expiration;

    public Grocery(String name, String amount, String expiration) {
        this.name = name;
        this.amount = amount;
        this.expiration = expiration;
    }

    // Getters and setters
    public String getName() {
        return name;
    }

    public String getAmount() {
        return amount;
    }

    public String getExpiration() {
        return expiration;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public void setExpiration(String expiration) {
        this.expiration = expiration;
    }
}

