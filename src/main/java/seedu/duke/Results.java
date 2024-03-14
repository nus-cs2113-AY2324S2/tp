package seedu.duke;

public class Results {
    protected int numberOfCorrectAnswers;
    protected int totalNumberOfQuestions;
    protected String score;

    private static final int HUNDRED_PERCENT = 100;
    private static final int ZERO_QUESTIONS = 0;

    public Results() {
        numberOfCorrectAnswers = ZERO_QUESTIONS;
        totalNumberOfQuestions = ZERO_QUESTIONS;
        score = "";
    }

    public void calculateScore() {
        int scorePercentage = (int) ((double) numberOfCorrectAnswers / (double) totalNumberOfQuestions * HUNDRED_PERCENT);
        score = numberOfCorrectAnswers + "/" + totalNumberOfQuestions + " (" + scorePercentage + "%)";
    }

    public String getScore() {
        return score;
    }

    public void increaseCorrectAnswers() {
        numberOfCorrectAnswers++;
    }

    public void increaseNumberOfQuestions() {
        totalNumberOfQuestions++;
    }
}
