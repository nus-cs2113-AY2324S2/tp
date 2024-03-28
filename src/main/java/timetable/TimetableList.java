package timetable;

import seedu.duke.InvalidInputFormatException;

import java.util.ArrayList;


public class TimetableList {

    public static int classCount;

    private static ArrayList<Days> mon;
    private static ArrayList<Days> tue;
    private static ArrayList<Days> wed;

    private static ArrayList<Days> thurs;
    private static ArrayList<Days> fri;

    public TimetableList() {
        mon = new ArrayList<>();
        classCount = 0;
    }

    public static void addClass(String schedule, Boolean userAdded) {
        try {
            String[] parts = schedule.split("day/", 2);
            if (parts.length < 2) {
                throw new InvalidInputFormatException("Invalid input format for class day.");
            }
            // Description part directly after "d/"
            String classDayPart = parts[1].trim();

            parts = classDayPart.split(" time/", 2);
            if (parts.length < 2) {
                throw new InvalidInputFormatException("Invalid input format for class time.");
            }
            String classDay = parts[0].trim();
            String classTimePart = parts[1].trim();

            parts = classTimePart.split(" duration/", 2);
            if (parts.length < 2) {
                throw new InvalidInputFormatException("Invalid input format for class duration.");
            }
            String classTime = parts[0].trim();
            String classDurationPart = parts[1].trim();

            parts = classDurationPart.split(" location/", 2);
            if (parts.length < 2) {
                throw new InvalidInputFormatException("Invalid input format for class duration.");
            }
            String classDuration = parts[0].trim();
            String classLocation = parts[1].trim();

            mon.add(new Days(classDay, classTime, classDuration, classLocation));
            userAddedMessage(userAdded);
            classCount++;
        } catch (InvalidInputFormatException e) {
            System.out.println(e.getMessage());

        }
    }

    private static void userAddedMessage(Boolean userAdded) {
        if (userAdded){
            System.out.println("Class added successfully.");
        }
    }

    public static void deleteClass(int index) {
        assert index > 0 && index <= mon.size() : "Index out of bounds.";
        Days day = mon.get(index - 1);
        System.out.println("deleted: " + day.getClassDescription() +
                " | Time: " + day.getClassTime() +
                " | Duration: " + day.getClassDuration() +
                " | Location: " + day.getClassLocation());
        mon.remove(index - 1);
        classCount--;
    }

}
