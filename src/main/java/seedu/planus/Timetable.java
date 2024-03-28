package seedu.planus;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Timetable {
    private static final Logger logger = Logger.getLogger("myLogger");
    private static final int TERM_PER_YEAR = 4;
    private static final int MAX_CANDIDATURE_YEAR = 6;

    // Each inner ArrayList represents a term
    ArrayList<ArrayList<Course>> courses;

    /**
     * Constructor to initialise the courses attribute with an empty 2D ArrayList
     */
    public Timetable() {
        courses = new ArrayList<>();
    }

    /**
     * Adds a course to the timetable plan
     *
     * @param course New course to be added
     * @throws Exception When the year or term specified is not within acceptable range
     */
    public void addCourse(Course course) throws Exception {
        if (course.getTerm() < 1 || course.getTerm() > TERM_PER_YEAR) {
            logger.log(Level.WARNING, course.toString() + ": Term provided is not from 1 to 4");
            throw new Exception("Term provided is not from 1 to 4");
        }
        if (course.getYear() < 1 || course.getYear() > MAX_CANDIDATURE_YEAR) {
            logger.log(Level.WARNING, course.toString() + ": Year provided is not from 1 to 6");
            throw new Exception("Year provided is not from 1 to 6");
        }

        boolean hasYearAndTerm = false;
        int newCourseYearAndTerm = 4 * (course.getYear() - 1) + course.getTerm();

        // Finding the suitable position to insert the course according to the year and term
        int i = 0;
        for (; i < courses.size(); i++) {
            Course currCourse = courses.get(i).get(0);
            int currYearAndTerm = 4 * (currCourse.getYear() - 1) + currCourse.getTerm();

            if (currYearAndTerm == newCourseYearAndTerm) {
                hasYearAndTerm = true;
                logger.log(Level.INFO, "Found row for " + course.getYearAndTerm());
                break;
            }
            if (currYearAndTerm > newCourseYearAndTerm) {
                break;
            }
        }

        // If the specified year and term does not exist in the plan yet, we add it in
        if (!hasYearAndTerm) {
            courses.add(i, new ArrayList<Course>());
            logger.log(Level.INFO, "Adding new row for " + course.getYearAndTerm());
        }
        courses.get(i).add(course);
        logger.log(Level.INFO, "Added new course");
    }

    /**
     * Removes a course from the timetable plan
     *
     * @param courseCode The code of the course to be removed
     * @return Whether the course specified existed in the plan previously and has been successfully removed
     */
    public boolean removeCourse(String courseCode) {
        boolean isRemoved = false;

        for (int i = 0; i < courses.size(); i++) {
            for (int j = 0; j < courses.get(i).size(); j++) {
                String currCourseCode = courses.get(i).get(j).getCourseCode();
                if (currCourseCode.equalsIgnoreCase(courseCode)) {
                    courses.get(i).remove(j);
                    isRemoved = true;
                    logger.log(Level.INFO, "Course removed");
                    break;
                }
            }

            if (courses.get(i).isEmpty()) {
                logger.log(Level.INFO, "Row is now empty, removing it");
                courses.remove(i);
                break;
            }
            if (isRemoved) {
                break;
            }
        }

        return isRemoved;
    }

    /**
     * Adds a grade to the existing course
     *
     * @param courseCode Course that the grade is counted for
     * @param grade Grade to be added to the course
     */
    //@@author ZhangWenyue3325
    public void addGrade(String courseCode, String grade) {
        for (ArrayList<Course> courseList : courses) {
            for (Course course : courseList) {
                String currCourseCode = course.getCourseCode();
                if (currCourseCode.equalsIgnoreCase(courseCode)) {
                    course.setGrade(grade);
                    if (course.getLetterGrade() == null) {
                        assert course.getNumberGrade() == -1 : "The numerical grade should be -1.";
                        Ui.printInvalidInputGrade();
                        return;
                    }
                    assert course.getLetterGrade().equals(grade) : "The grade is not correctly set.";
                    Ui.printSuccessToAddGrade(currCourseCode);
                    return;
                }
            }
        }

        Ui.printFailedToAddGrade();
    }

    /**
     * Deletes a grade from the existing course
     *
     * @param courseCode Course that the grade is counted for
     */
    public void removeGrade(String courseCode) {
        for (ArrayList<Course> courseList : courses) {
            for (Course course : courseList) {
                String currCourseCode = course.getCourseCode();
                if (currCourseCode.equalsIgnoreCase(courseCode)) {
                    course.setGrade(null);
                    assert course.getNumberGrade() == -1 : "The numerical grade should be -1.";
                    Ui.printSuccessToRemoveGrade(currCourseCode);
                    return;
                }
            }
        }
        Ui.printFailedToRemoveGrade();
    }

    /**
     * Searches for the index of the specific year and term in the timetable
     *
     * @param year Academic year
     * @param term Semester
     */
    int searchTimetableIndex(int year, int term) {
        int index = -1;
        for (int i = 0; i < courses.size(); i ++) {
            if (courses.get(i).get(0).getYear() == year && courses.get(i).get(0).getTerm() == term) {
                index = i;
                break;
            }
        }
        return index;
    }

    //@@author ZhouJunmin
    @Override
    public String toString() {
        StringBuilder plan = new StringBuilder();

        for (ArrayList<Course> termCourses : courses) {
            for (Course course : termCourses) {
                plan.append(course.toString()).append(System.lineSeparator());
            }
        }

        return plan.toString();
    }
}
