package classify.student;

/**
 * Meant to represent a particular student of the tuition centre.
 * The student can first be declared with only a name, and the subsequent details
 * added later.
 */
public class Student {

    //@@author Cryolian
    public String name;
    public Details details = new Details();

    //@@author tayponghee
    private StudentAttributes attributes;

    //@@author Cryolian

    public Student(String name) {
        this.name = name;
    }

    public Student(String name, Details details) {
        this.name = name;
        this.details = details;
    }

    //@@author tayponghee
    public Student(String name, StudentAttributes attributes) {
        this.name = name;
        this.attributes = attributes;
    }

    public StudentAttributes getAttributes() {
        return attributes;
    }

    public void setAttributes(StudentAttributes attributes) {
        this.attributes = attributes;
    }
    public StudentAttributes getAttributesList() {
        return attributes;
    }

    //@@ Cryolian
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
