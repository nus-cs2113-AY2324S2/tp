package seedu.duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ResultsTest {
    @Test
    void testScoreCalculation() {
        Results sessionResults = new Results();
        sessionResults.increaseNumberOfQuestions();
        sessionResults.increaseNumberOfQuestions();
        sessionResults.increaseCorrectAnswers();
        sessionResults.calculateScore();
        assertEquals("1/2 (50%)", sessionResults.getScore());
    }
}