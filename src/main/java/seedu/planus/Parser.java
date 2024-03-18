package seedu.planus;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * The Parser class handles the parsing of user commands in the PlaNus application.
 */
public class Parser {

    /**
     * Parses the user command and performs the corresponding action on the timetable.
     * @param line The user command to be parsed.
     * @param timetable The timetable to be modified.
     * @return A boolean indicating whether the application should exit.
     */
    public static boolean parseCommand(String line, Timetable timetable) {
        String[] words = line.split(" ", 2);
        String[] yearAndTerm;
        int year;
        int term;

        switch(words[0]) {
            case "init":
                timetable = Storage.loadTimetable(words[1]);
                return false;
            case "add":
                Course newCourse;
                String[] courseCodeAndYearAndTerms = words[1].split("/y",2);
                String courseCode = courseCodeAndYearAndTerms[0].trim();
                String courseName = "userAdded";
                yearAndTerm = courseCodeAndYearAndTerms[1].split("/t",2);
                year = Integer.parseInt(yearAndTerm[0].trim());
                term = Integer.parseInt(yearAndTerm[1].trim());
                newCourse = new Course(courseCode, courseName, year, term);
                try {
                    timetable.addCourse(newCourse);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                return false;
            case "rm":
                timetable.removeCourse(words[1]);
                return false;
            case "view":
                if (words.length == 1) {
                    System.out.println(timetable.getPlan());
                    return false;
                } else {
                    yearAndTerm = words[1].split("/y",2);
                    year = Integer.parseInt(yearAndTerm[0].trim());
                    term = Integer.parseInt(yearAndTerm[1].trim());
                    timetable.getPlan(year,term);
                    return false;
                }
            case "bye":
                return true;
        }
        return false;
    }
}
