package seedu.planus;

import java.util.ArrayList;

public class PlanGetter {
    /**
     * Returns a formatted string containing the entire timetable plan of the user
     *
     * @return A string with the timetable plan
     */
    public static String getPlan(Timetable timetable) {
        StringBuilder plan = new StringBuilder();
        int totalCredit = 0;

        for (ArrayList<Course> termCourses : timetable.courses) {
            assert !termCourses.isEmpty(): "Accessing empty term";
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
        assert !plan.toString().isEmpty(): "Plan should not be empty";
        return plan.toString();
    }

    /**
     * Returns a formatted string containing the timetable plan for the year specified
     *
     * @param year Year of study for which the user wants to check the plan
     * @return A string with the year's timetable plan
     */
    public static String getPlan(Timetable timetable, int year) {
        StringBuilder plan = new StringBuilder();
        int yearCredit = 0;

        for (ArrayList<Course> termCourses : timetable.courses) {
            if (termCourses.get(0).getYear() < year) {
                continue;
            }
            if (termCourses.get(0).getYear() > year) {
                break;
            }

            assert termCourses.get(0).getYear() == year: "Accessing wrong year";
            assert !termCourses.isEmpty(): "Accessing empty term";
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
        assert !plan.toString().isEmpty(): "Plan should not be empty";
        return plan.toString();
    }

    /**
     * Returns a formatted string containing the timetable plan for the year and term specified
     *
     * @param year Year of study for which the user wants to check the plan
     * @param term Term for which the user wants to check the plan
     * @return A string with the term's timetable plan
     */
    public static String getPlan(Timetable timetable, int year, int term) {
        StringBuilder plan = new StringBuilder();
        int termCredit = 0;

        for (ArrayList<Course> termCourses : timetable.courses) {
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

            assert termCourses.get(0).getYear() == year: "Accessing wrong year";
            assert termCourses.get(0).getTerm() == term: "Accessing wrong term";
            assert !termCourses.isEmpty(): "Accessing empty term";
            plan.append(termCourses.get(0).getYearAndTerm()).append(":").append(System.lineSeparator());

            for (Course course : termCourses) {
                plan.append("  ").append(course.getDetails()).append(System.lineSeparator());
                termCredit += course.getModularCredit();
            }
        }

        plan.append("Term MCs: ").append(termCredit).append(System.lineSeparator());
        assert !plan.toString().isEmpty(): "Plan should not be empty";
        return plan.toString();
    }

}
