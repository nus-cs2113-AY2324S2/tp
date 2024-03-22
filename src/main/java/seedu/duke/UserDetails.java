package seedu.duke;

public class UserDetails {
    private String name;
    private String age;
    private String gender;
    private String status;

    public UserDetails(String name, String age, String gender, String status) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.status = status;
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

    public String toFileFormat() {
        return name + "|" + age + "|" + gender + "|" + status;
    }
}