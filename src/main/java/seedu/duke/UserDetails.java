package seedu.duke;

public class UserDetails {
    private String name;
    private String age;
    private String gender;
    private String status;
    private String location;
    private String cuisine;
    private String anniversary;

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

    // Getter for name
    public String getName() {
        return name;
    }

    // Setter for name
    public void setName(String name) {
        this.name = name;
    }

    // Getter for status
    public String getStatus() {
        return status;
    }

    // Setter for status
    public void setStatus(String status) {
        this.status = status;
    }

    // Getter for age
    public String getAge() {
        return age;
    }

    // Setter for age
    public void setAge(String age) {
        this.age = age;
    }

    // Getter for gender
    public String getGender() {
        return gender;
    }

    // Setter for gender
    public void setGender(String gender) {
        this.gender = gender;
    }

    // Getter for cuisine
    public String getCuisine() {
        return cuisine;
    }

    // Setter for cuisine
    public void setCuisine(String cuisine) {
        this.cuisine = cuisine;
    }

    // Getter for location
    public String getLocation() {
        return location;
    }

    // Setter for location
    public void setLocation(String location) {
        this.gender = location;
    }

    public String getAnniversary() {
        return anniversary;
    }

    public void setAnniversary(String anniversary) {
        this.anniversary =anniversary;
    }

    public String toFileFormat() {
        return name + " | " + age + " | " + gender + " | " + status + " | " + location + " | " +
                cuisine + " | " + anniversary;
    }
}
