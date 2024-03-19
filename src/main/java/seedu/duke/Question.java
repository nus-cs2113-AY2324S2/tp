package seedu.duke;

public class Question {
    private String question;
    private String solution;
    private String explanation;

    public Question(String question, String solution, String explanation){
        this.question = question;
        this.solution = solution;
        this.explanation = explanation;
    }

    public String getExplanation() {
        return explanation;
    }
    public String getQuestion() {
        return question;
    }
    public String getSolution() {
        return solution;
    }
}
