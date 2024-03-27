package activeedge.userdetails;

/**
 * The UserDetails class represents details of a user.
 * It provides methods to get the value of the user details and to represent the object as a string.
 */
public class UserDetails {

    /**
     * The value of the user details.
     */
    protected Integer value;

    /**
     * Constructs a UserDetails object with the specified value.
     *
     * @param value The value of the user details.
     */
    public UserDetails(Integer value) {
        this.value = value;
    }

    /**
     * Gets the value of the user details.
     *
     * @return The value of the user details.
     */
    public Integer getValue() {
        return this.value;
    }

    /**
     * Returns a string representation of the UserDetails object.
     *
     * @return A string representation of the UserDetails object.
     */
    @Override
    public String toString() {
        return String.valueOf(this.getValue());
    }
}

