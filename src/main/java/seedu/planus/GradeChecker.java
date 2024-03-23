package seedu.planus;

public class GradeChecker {
    private static final int TERM_PER_YEAR = 4;
    private static final int MAX_CANDIDATURE_YEAR = 6;

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
            }

            double yearGPA = 0.00;
            if (yearMCs != 0) {
                yearGPA = yearGrade / yearMCs;
            }

            plan.append("Year ").append(y).append(" GPA: ").append(yearGPA).append(System.lineSeparator())
                    .append(System.lineSeparator());

            totalMCs += yearMCs;
            totalGrade += yearGrade;
        }

        double cumulativeGPA = 0.00;
        if (totalMCs != 0) {
            cumulativeGPA = totalGrade / totalMCs;
        }
        plan.append("Total GPA: ").append(cumulativeGPA).append(System.lineSeparator()).append(System.lineSeparator());

        return plan.toString();
    }

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
        }

        double yearGPA = 0.00;
        if (yearMCs != 0) {
            yearGPA = yearGrade / yearMCs;
        }

        plan.append("Year ").append(year).append(" GPA: ").append(yearGPA).append(System.lineSeparator())
                .append(System.lineSeparator());

        return plan.toString();
    }

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
        plan.append("Term GPA: ").append(termGPA).append(System.lineSeparator())
                .append("-----------------------------").append(System.lineSeparator());

        return plan.toString();
    }
}
