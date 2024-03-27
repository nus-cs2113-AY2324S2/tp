package seedu.duke;

public class Problem {

    private final String description;
    private final double answer;

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

    public double getAnswer() {
        return answer;
    }
}
