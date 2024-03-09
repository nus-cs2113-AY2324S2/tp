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

    public Student(String name) {
        this.name = name;
    }

    public Student(String name, Details details) {
        this.name = name;
        this.details = details;
    }
    
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
