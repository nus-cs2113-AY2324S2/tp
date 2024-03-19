package seedu.planus;

/**
 *  Represents the grade of a course
 */
public class Grade {
    private String letterGrade;
    private double numberGrade;

    public Grade() {
        this(null);
    }

    public Grade(String letterGrade) {
        setLetterGrade(letterGrade);
    }

    public String getLetterGrade() {
        return letterGrade;
    }

    public void setLetterGrade(String letterGrade) {
        this.letterGrade = letterGrade;
        setNumberGrade(letterGrade);

        if (numberGrade == -1) {
            this.letterGrade = null;
        }
    }

    public double getNumberGrade() {
        return numberGrade;
    }

    /**
     * Transforms the grade from letter form to number form
     *
     * @param letterGrade Grade entered in letter form
     */
    private void setNumberGrade(String letterGrade) {
        if (letterGrade == null) {
            this.numberGrade = -1;
            return;
        }

        switch(letterGrade) {
        case "A+":
        case "A":
            this.numberGrade = 5.00;
            break;
        case "A-":
            this.numberGrade = 4.50;
            break;
        case "B+":
            this.numberGrade = 4.00;
            break;
        case "B":
            this.numberGrade = 3.50;
            break;
        case "B-":
            this.numberGrade = 3.00;
            break;
        case "C+":
            this.numberGrade = 2.50;
            break;
        case "C":
            this.numberGrade = 2.00;
            break;
        case "D+":
            this.numberGrade = 1.50;
            break;
        case "D":
            this.numberGrade = 1.00;
            break;
        case "F":
            this.numberGrade = 0.00;
            break;
        default:
            this.numberGrade = -1;
        }
    }
}
