package timetable;

public class Days {

    protected String classDescription;
    protected String classTime;
    protected String classDuration;
    protected String classLocation;

    public Days(String classDescription, String classTime, String classDuration, String classLocation){
        this.classDescription = classDescription;
        this.classTime = classTime;
        this.classDuration = classDuration;
        this.classLocation = classLocation;
    }

    public String getClassDescription() {
        return classDescription;
    }

    public String getClassTime() {return classTime;}

    public String getClassDuration() {return classDuration;}

    public String getClassLocation() {
        return classLocation;
    }


}
