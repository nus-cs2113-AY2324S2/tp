package classify.student;

public class Student {

    //@@author Cryolian
    public String name;
    public Details details = new Details();

    public Student(String name) {
        this.name = name;
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
