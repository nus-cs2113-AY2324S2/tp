package grocery;


/**
 * Represents a grocery.
 */
public class Grocery {
    private String name;
    private int amount;
    private String expiration;

    /**
     * Constructs a Grocery.
     *
     * @param name Name.
     * @param amount Measurement of grocery.
     * @param expiration When grocery expires.
     */
    public Grocery(String name, int amount, String expiration) {
        this.name = name;
        this.amount = amount;
        this.expiration = expiration;
    }

    // Getters and setters
    public String getName() {
        return name;
    }

    public int getAmount() {
        return amount;
    }

    public String getExpiration() {
        return expiration;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAmount(int amount) {
        assert amount >= 0 : "Amount entered is invalid!";
        this.amount = amount;
    }

    public void setExpiration(String expiration) {
        assert !(expiration.isEmpty()) : "Expiration date entered is invalid!";
        this.expiration = expiration;
    }

    /**
     * Returns a String representation of the Grocery.
     */
    public String printGrocery() {
        assert !(this.name.isEmpty()) : "Grocery does not exist!!";

        // TODO: update amount output according to Grocery subclass
        // TODO: consider stating amount == 0 now that we track amount ?
        String amt = (amount == 0) ? "" : ", amount: " + amount;
        String exp = (expiration == null || expiration.isEmpty()) ? "" : ", expiration: " + expiration;
        return this.name + amt + exp;
    }
}

