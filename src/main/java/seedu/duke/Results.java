package seedu.duke;

public class Results {
    protected static int numberOfCorrectAnswers;
    protected static int totalNumberOfQuestions;
    protected static String score;

    private static final int HUNDRED_PERCENT = 100;
    private static final int ZERO_QUESTIONS = 0;

    public Results() {
        numberOfCorrectAnswers = ZERO_QUESTIONS;
        totalNumberOfQuestions = ZERO_QUESTIONS;
    }

    private static void calculateScore() {
        int scorePercentage = numberOfCorrectAnswers / totalNumberOfQuestions * HUNDRED_PERCENT;
        score = numberOfCorrectAnswers + "/" + totalNumberOfQuestions + " (" + scorePercentage + "%)";
    }

    public static String getScore() {
        return score;
    }

    public static void increaseCorrectAnswers() {
        numberOfCorrectAnswers++;
    }

    public static void increaseNumberOfQuestions() {
        totalNumberOfQuestions++;
    }
}
