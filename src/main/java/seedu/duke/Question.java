package seedu.duke;

public class Question {
    private String question;
    private String answer;
    private String explanation;

    public Question(String question, String answer, String explanation){
        this.question = question;
        this.answer = answer;
        this.explanation = explanation;
    }

    public String getExplanation() {
        return explanation;
    }
    public String getQuestion() {
        return question;
    }
    public String getAnswer() {
        return answer;
    }
}
