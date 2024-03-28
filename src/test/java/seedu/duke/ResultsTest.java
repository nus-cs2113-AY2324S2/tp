package seedu.duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ResultsTest {
    Results roundResults;

    void createResult() {
        roundResults = new Results();
        roundResults.increaseNumberOfQuestions();
        roundResults.increaseNumberOfQuestions(); // total 2 questions
        roundResults.increaseCorrectAnswers(); // 1 correct answer
        roundResults.calculateScore(); // returns 1/2 (50%)
    }

    @Test
    void testScoreCalculation() {
        createResult();
        assertEquals("1/2 (50%)", roundResults.getScore());
    }
}
