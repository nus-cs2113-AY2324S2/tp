package seedu.duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ResultsTest {
    Results roundResults;

    void createResult() {
        roundResults = new Results();
        roundResults.increaseNumberOfQuestions();
        roundResults.increaseNumberOfQuestions();
        roundResults.increaseCorrectAnswers();
        roundResults.calculateScore();
    }

    @Test
    void testScoreCalculation() {
        createResult();
        assertEquals("1/2 (50%)", roundResults.getScore());
    }
}
