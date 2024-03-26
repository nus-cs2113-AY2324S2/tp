package seedu.duke;

/**
 * Represents the user's personal details within the application.
 * This class stores information such as the user's name, age, gender, relationship status,
 * location, favorite cuisine, and anniversary date if applicable. It provides getters and
 * setters for each field, allowing for encapsulated access and modification of these details.
 */
public class UserDetails {
    private String name;
    private String age;
    private String gender;
    private String status;
    private String location;
    private String cuisine;
    private String anniversary;

    /**
     * Constructs a new UserDetails instance with default values for all fields.
     * Default values are placeholder strings indicating that the details have not been set.
     */
    public UserDetails() {
        this.name = "NOT SET";
        this.age = "N.A";
        this.gender = "N.A";
        this.status = "N.A";
        this.location = "N.A";
        this.cuisine = "N.A";
        this.anniversary = "N.A";
    }

    /**
     * Constructs a new UserDetails instance with specified values for all fields.
     *
     * @param name The user's name.
     * @param age The user's age.
     * @param gender The user's gender.
     * @param status The user's relationship status.
     * @param location The user's preferred location.
     * @param cuisine The user's favorite cuisine.
     * @param anniversary The user's anniversary date, if applicable.
     */    
    public UserDetails(String name, String age, String gender, String status, String location,
                       String cuisine, String anniversary) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.status = status;
        this.location = location;
        this.cuisine = cuisine;
        this.anniversary = anniversary;
    }

    /**
     * Returns the user's name.
     *
     * @return The name of the user.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the user's name.
     *
     * @param name The new name of the user.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the user's relationship status.
     *
     * @return The relationship status of the user.
     */
    public String getStatus() {
        return status;
    }

    /**
     * Sets the user's relationship status.
     *
     * @param status The relationship status of the user.
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * Returns the user's age.
     *
     * @return The age of the user.
     */
    public String getAge() {
        return age;
    }

    /**
     * Sets the user's age.
     *
     * @param age The age of the user.
     */
    public void setAge(String age) {
        this.age = age;
    }

    /**
     * Returns the user's gender.
     *
     * @return The gender of the user.
     */
    public String getGender() {
        return gender;
    }

    /**
     * Sets the user's gender.
     *
     * @param gender The gender of the user.
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     * Returns the user's favourite cuisine.
     *
     * @return The favourite cuisine of the user.
     */
    public String getCuisine() {
        return cuisine;
    }

    /**
     * Sets the user's favourite cuisine.
     *
     * @param cuisine The favourite cuisine of the user.
     */
    public void setCuisine(String cuisine) {
        this.cuisine = cuisine;
    }

    /**
     * Returns the user's address location.
     *
     * @return The address location of the user.
     */
    public String getLocation() {
        return location;
    }

    /**
     * Sets the user's address location.
     *
     * @param location The address location of the user.
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * Returns the user's anniversary date.
     *
     * @return The anniversary date of the user.
     */
    public String getAnniversary() {
        return anniversary;
    }

    /**
     * Sets the user's anniversary date.
     *
     * @param anniversary The anniversary date of the user.
     */
    public void setAnniversary(String anniversary) {
        this.anniversary =anniversary;
    }

    /**
     * Converts the user's details into a string format suitable for file storage.
     * The details are concatenated with " | " as a delimiter.
     *
     * @return A string representation of the user's details in file storage format.
     */
    public String toFileFormat() {
        return name + " | " + age + " | " + gender + " | " + status + " | " + location + " | " +
                cuisine + " | " + anniversary;
    }
}
