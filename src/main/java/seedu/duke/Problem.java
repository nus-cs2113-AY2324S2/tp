package seedu.duke;

public class Problem {

    private String desctiption;
    private double answer;

    public Problem(String desctiption, double answer) {
        this.desctiption = desctiption;
        this.answer = answer;
    }

    public String solved() {
        return desctiption + answer;
    }

    public String unsolved() {
        return desctiption + "__";
    }

    public double getAnswer() {
        return answer;
    }
}
