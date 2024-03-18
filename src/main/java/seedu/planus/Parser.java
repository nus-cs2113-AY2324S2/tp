package seedu.planus;

import java.awt.desktop.SystemEventListener;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
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
        String[] words = line.split(" ");
        String[] yearAndTerm;
        int year;
        int term;

        switch(words[0]) {
        case "init":
            timetable = Storage.loadTimetable(words[1]);
            return false;
        case "add":
            if (Objects.equals(words[1], "course")) {
                Course newCourse;
                String[] courseCodeAndYearAndTerms = words[2].split("/y", 2);
                String courseCode = courseCodeAndYearAndTerms[0].trim();
                String courseName = "userAdded";
                yearAndTerm = courseCodeAndYearAndTerms[1].split("/t", 2);
                year = Integer.parseInt(yearAndTerm[0].trim());
                term = Integer.parseInt(yearAndTerm[1].trim());
                newCourse = new Course(courseCode, courseName, year, term);
                try {
                    timetable.addCourse(newCourse);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            } else if (Objects.equals(words[1], "grade")) {
                timetable.addGrade(words[2], words[3]);
            }
            return false;
        case "rm":
            if (Objects.equals(words[1], "course")) {
                timetable.removeCourse(words[2]);
            } else if (Objects.equals(words[1], "grade")) {
                timetable.removeGrade(words[2]);
            }
            return false;
        case "change":
            timetable.addGrade(words[2], words[3]);
            return false;
        case "check":
            if (words[1].equals("grade")) {
                if ((words.length == 4) && words[3].contains("t/")) {
                    year = Integer.parseInt(words[2]);
                    term = Integer.parseInt(words[3].split("t/", 1)[0].trim());
                    System.out.println(timetable.checkGrade(year, term));
                } else if (words.length == 3) {
                    year = Integer.parseInt(words[2]);
                    System.out.println(timetable.checkGrade(year));
                } else if (words.length == 2) {
                    System.out.println(timetable.checkGrade());
                }
            }
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
