package seedu.duke;

public class Problem {

    private String description;
    private double answer;

    public Problem(String description, double answer) {
        this.description = description;
        this.answer = answer;
    }

    public String solved() {
        return description + answer;
    }

    public String unsolved() {
        return description + "__";
    }


}
