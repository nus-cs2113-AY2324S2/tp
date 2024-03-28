package gpa;


public class GPAMain {

    // Method to calculate new GPA with inputs provided as parameters
    public static double calculateNewGPA(double currentGPA, int totalAccumulatedCredits,
                                         int numOfModules, int[] moduleCredits, String[] moduleGrades) {
        double totalPoints = currentGPA * totalAccumulatedCredits;
        int newCredits = 0;

        for (int i = 0; i < numOfModules; i++) {
            double gradePoints = calculatePointsForGrade(moduleGrades[i]);
            totalPoints += gradePoints * moduleCredits[i];
            newCredits += moduleCredits[i];
        }

        return totalPoints / (totalAccumulatedCredits + newCredits);
    }

    private static double calculatePointsForGrade(String grade) {
        // Define GPA points for grades
        switch (grade) {
        case "A+":
        case "A":
            return 5.00;
        case "A-":
            return 4.50;
        case "B+":
            return 4.00;
        case "B":
            return 3.50;
        case "B-":
            return 3.00;
        case "C+":
            return 2.50;
        case "C":
            return 2.00;
        case "D+":
            return 1.50;
        case "D":
            return 1.00;
        case "F":
            return 0.00;

        default:
            System.out.println("Invalid grade input.");
            return 0.00;
        }
    }
}
