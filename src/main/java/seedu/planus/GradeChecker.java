//@@author ZhangWenyue3325
package seedu.planus;

import java.util.logging.Logger;

public class GradeChecker {
    private static Logger logger = Logger.getLogger("myLogger");
    private static final int TERM_PER_YEAR = 4;
    private static final int MAX_CANDIDATURE_YEAR = 6;

    /**
     * Returns a formatted string containing the grades for all semesters of the user
     *
     * @param timetable Timetable of the user
     * @return A string with the grades
     */
    public static String checkGrade(Timetable timetable) {
        int totalMCs = 0;
        int yearMCs = 0;
        int termMCs = 0;
        double totalGrade = 0.00;
        double yearGrade = 0.00;
        double termGrade = 0.00;

        StringBuilder plan = new StringBuilder();


        for (int y = 1; y <= MAX_CANDIDATURE_YEAR; y ++) {
            plan.append("Year ").append(y).append(":").append(System.lineSeparator());

            for (int t = 1; t <= TERM_PER_YEAR; t ++) {
                int index = timetable.searchTimetableIndex(y, t);
                if (index == -1) {
                    continue;
                }

                plan.append(timetable.courses.get(index).get(0).getYearAndTerm()).append(":")
                        .append(System.lineSeparator());

                for (Course course : timetable.courses.get(index)) {
                    plan.append("  ").append(course.getGrade()).append(System.lineSeparator());
                    if (course.getLetterGrade() == null) {
                        continue;
                    }
                    termMCs += course.getModularCredit();
                    termGrade += course.getNumberGrade() * course.getModularCredit();
                }

                double termGPA = 0.00;
                if (termMCs != 0) {
                    termGPA = termGrade / termMCs;
                }

                plan.append("Term GPA: ").append(termGPA).append(System.lineSeparator())
                        .append("-----------------------------").append(System.lineSeparator());

                yearMCs += termMCs;
                yearGrade += termGrade;
                termMCs = 0;
                termGrade = 0.00;
            }

            double yearGPA = 0.00;
            if (yearMCs != 0) {
                yearGPA = yearGrade / yearMCs;
            }

            plan.append("Year ").append(y).append(" GPA: ").append(yearGPA).append(System.lineSeparator())
                    .append(System.lineSeparator());

            totalMCs += yearMCs;
            totalGrade += yearGrade;
            yearMCs = 0;
            yearGrade = 0.00;
        }

        double cumulativeGPA = 0.00;
        if (totalMCs != 0) {
            cumulativeGPA = totalGrade / totalMCs;
        }
        assert cumulativeGPA >= 0.00 : "The cumulative GPA should be non-negative.";
        plan.append("Total GPA: ").append(cumulativeGPA).append(System.lineSeparator()).append(System.lineSeparator());

        return plan.toString();
    }

    /**
     * Returns a formatted string containing the grades for the year specified
     *
     * @param timetable Timetable of the user
     * @param year Year of study for which the user wants to check the grade
     * @return A string with the year's grades
     */
    public static String checkGrade(Timetable timetable, int year) {
        int yearMCs = 0;
        int termMCs = 0;
        double yearGrade = 0.00;
        double termGrade = 0.00;

        StringBuilder plan = new StringBuilder();

        plan.append("Year ").append(year).append(":").append(System.lineSeparator());

        for (int t = 1; t <= TERM_PER_YEAR; t ++) {
            int index = timetable.searchTimetableIndex(year, t);
            if (index == -1) {
                continue;
            }

            plan.append(timetable.courses.get(index).get(0).getYearAndTerm()).append(":")
                    .append(System.lineSeparator());

            for (Course course : timetable.courses.get(index)) {
                plan.append("  ").append(course.getGrade()).append(System.lineSeparator());
                if (course.getLetterGrade() == null) {
                    continue;
                }

                termMCs += course.getModularCredit();
                termGrade += course.getNumberGrade() * course.getModularCredit();
            }

            double termGPA = 0.00;
            if (termMCs != 0) {
                termGPA = termGrade / termMCs;
            }

            plan.append("Term GPA: ").append(termGPA).append(System.lineSeparator())
                    .append("-----------------------------").append(System.lineSeparator());

            yearMCs += termMCs;
            yearGrade += termGrade;
            termMCs = 0;
            termGrade = 0.00;
        }

        double yearGPA = 0.00;
        if (yearMCs != 0) {
            yearGPA = yearGrade / yearMCs;
        }
        assert yearGPA >= 0.00 : "The GPA for the given academic year should be non-negative.";

        plan.append("Year ").append(year).append(" GPA: ").append(yearGPA).append(System.lineSeparator())
                .append(System.lineSeparator());

        return plan.toString();
    }

    /**
     * Returns a formatted string containing the grades for the year and term specified
     *
     * @param timetable Timetable of the user
     * @param year Year of study for which the user wants to check the grade
     * @param term Term of study for which the user wants to check the grade
     * @return A string with the term's grades
     */
    public static String checkGrade(Timetable timetable, int year, int term) {
        int termMCs = 0;
        double termGrade = 0.00;
        int index = timetable.searchTimetableIndex(year, term);

        StringBuilder plan = new StringBuilder();

        plan.append(timetable.courses.get(index).get(0).getYearAndTerm()).append(":").append(System.lineSeparator());

        for (Course course : timetable.courses.get(index)) {
            plan.append("  ").append(course.getGrade()).append(System.lineSeparator());
            if (course.getLetterGrade() == null) {
                continue;
            }
            termMCs += course.getModularCredit();
            termGrade += course.getNumberGrade() * course.getModularCredit();
        }

        double termGPA = 0.00;
        if (termMCs != 0) {
            termGPA = termGrade / termMCs;
        }
        assert termGPA >= 0.00 : "The GPA of the semester should be non-negative.";

        plan.append("Term GPA: ").append(termGPA).append(System.lineSeparator())
                .append("-----------------------------").append(System.lineSeparator());

        return plan.toString();
    }

}
