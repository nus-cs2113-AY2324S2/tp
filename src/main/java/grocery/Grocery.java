package grocery;


/**
 * Represents a grocery.
 */
public class Grocery {
    private String name;
    private String amount;
    private String expiration;

    /**
     * Constructs a Grocery.
     *
     * @param name Name.
     * @param amount Measurement of grocery.
     * @param expiration When grocery expires.
     */
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

    /**
     * Returns a String representation of the Grocery.
     */
    // TODO: cannot take in NULL values ?
    public String printGrocery() {
        assert !(this.name.isEmpty()) : "Grocery does not exist";

        String amt = (this.amount == null || this.amount.isEmpty()) ? "" : ", amount: " + this.amount;
        String exp = (this.amount == null || this.expiration.isEmpty()) ? "" : ", expiration: " + this.expiration;
        return this.name + amt + exp;
    }
}

