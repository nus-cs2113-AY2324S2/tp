package seedu.duke;

import java.util.ArrayList;

public class Timetable {
    // Each inner ArrayList represents a term
    private static final int TERM_PER_YEAR = 4;
    private static final int MAX_CANDIDATURE_YEAR = 6;
    private ArrayList<ArrayList<Course>> courses;

    /**
     * Constructor to initialise the courses attribute with an empty 2D ArrayList
     */
    public Timetable() {
        courses = new ArrayList<ArrayList<Course>>();
    }

    /**
     * Adds a course to the timetable plan
     *
     * @param course New course to be added
     * @throws Exception When the year or term specified is not within acceptable range
     */
    public void addCourse(Course course) throws Exception {
        if (course.getTerm() < 1 || course.getTerm() > TERM_PER_YEAR) {
            throw new Exception("Term is not from 1 to 4");
        }
        if (course.getYear() < 1 || course.getYear() > MAX_CANDIDATURE_YEAR) {
            throw new Exception("Year is not from 1 to 6");
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
                break;
            }
            if (currYearAndTerm > newCourseYearAndTerm) {
                break;
            }
        }

        // If the specified year and term does not exist in the plan yet, we add it in
        if (!hasYearAndTerm) {
            courses.add(i, new ArrayList<Course>());
        }
        courses.get(i).add(course);
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
                    break;
                }
            }

            if (courses.get(i).isEmpty()) {
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
     * Returns a formatted string containing the entire timetable plan of the user
     *
     * @return A string with the timetable plan
     */
    public String getPlan() {
        StringBuilder plan = new StringBuilder();
        int totalCredit = 0;

        for (ArrayList<Course> termCourses : courses) {
            plan.append(termCourses.get(0).getYearAndTerm()).append(":").append(System.lineSeparator());

            int termCredit = 0;
            for (Course course : termCourses) {
                plan.append("  ").append(course.getDetails()).append(System.lineSeparator());
                termCredit += course.getModularCredit();
            }

            plan.append("Term MCs: ").append(termCredit).append(System.lineSeparator())
                    .append("-----------------------------").append(System.lineSeparator());
            totalCredit += termCredit;
        }

        plan.append("Total MCs: ").append(totalCredit).append(System.lineSeparator());
        return plan.toString();
    }

    /**
     * Returns a formatted string containing the timetable plan for the year specified
     *
     * @param year Year of study for which the user wants to check the plan
     * @return A string with the year's timetable plan
     */
    public String getPlan(int year) {
        StringBuilder plan = new StringBuilder();
        int yearCredit = 0;

        for (ArrayList<Course> termCourses : courses) {
            if (termCourses.get(0).getYear() < year) {
                continue;
            }
            if (termCourses.get(0).getYear() > year) {
                break;
            }

            plan.append(termCourses.get(0).getYearAndTerm()).append(":").append(System.lineSeparator());

            int termCredit = 0;
            for (Course course : termCourses) {
                plan.append("  ").append(course.getDetails()).append(System.lineSeparator());
                termCredit += course.getModularCredit();
            }

            plan.append("Term MCs: ").append(termCredit).append(System.lineSeparator())
                    .append("-----------------------------").append(System.lineSeparator());
            yearCredit += termCredit;
        }

        plan.append("Year MCs: ").append(yearCredit).append(System.lineSeparator());
        return plan.toString();
    }

    /**
     * Returns a formatted string containing the timetable plan for the year and term specified
     *
     * @param year Year of study for which the user wants to check the plan
     * @param term Term for which the user wants to check the plan
     * @return A string with the term's timetable plan
     */
    public String getPlan(int year, int term) {
        StringBuilder plan = new StringBuilder();
        int termCredit = 0;

        for (ArrayList<Course> termCourses : courses) {
            if (termCourses.get(0).getYear() < year) {
                continue;
            }
            if (termCourses.get(0).getYear() > year) {
                break;
            }

            if (termCourses.get(0).getTerm() < term) {
                continue;
            }
            if (termCourses.get(0).getTerm() > term) {
                break;
            }

            plan.append(termCourses.get(0).getYearAndTerm()).append(":").append(System.lineSeparator());
            for (Course course : termCourses) {
                plan.append("  ").append(course.getDetails()).append(System.lineSeparator());
                termCredit += course.getModularCredit();
            }
        }

        plan.append("Term MCs: ").append(termCredit).append(System.lineSeparator());
        return plan.toString();
    }

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
