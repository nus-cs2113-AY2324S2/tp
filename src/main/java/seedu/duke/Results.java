package seedu.duke;

import java.util.logging.LogManager;
import java.util.logging.Logger;
import java.util.logging.Level;

public class Results {
    private static final int HUNDRED_PERCENT = 100;
    private static final int ZERO_QUESTIONS = 0;

    private static Logger logger = Logger.getLogger("ResultsLogger");

    protected int numberOfCorrectAnswers;
    protected int totalNumberOfQuestions;
    protected String score;

    public Results() {
        numberOfCorrectAnswers = ZERO_QUESTIONS;
        totalNumberOfQuestions = ZERO_QUESTIONS;
        score = "";
    }

    public void calculateScore() {
        LogManager.getLogManager().reset();
        logger.setLevel(Level.ALL);
        logger.log(Level.INFO, "going to start calculating score");
        int scorePercentage = (int) ((double) numberOfCorrectAnswers / (double) totalNumberOfQuestions *
                HUNDRED_PERCENT);
        assert scorePercentage >= 0;
        score = numberOfCorrectAnswers + "/" + totalNumberOfQuestions + " (" + scorePercentage + "%)";
        logger.log(Level.INFO,"end of calculation");
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
