package seedu.duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ResultsListTest {
    Results roundOneResults;
    Results roundTwoResults;
    ResultsList sessionsResults;

    void createResultList() {
        roundOneResults = new Results();
        roundTwoResults = new Results();
        sessionsResults = new ResultsList();
        roundOneResults.increaseNumberOfQuestions();
        roundOneResults.increaseNumberOfQuestions();
        roundOneResults.increaseCorrectAnswers();
        roundOneResults.calculateScore(); // 1 out of 2 correct
        roundTwoResults.increaseNumberOfQuestions();
        roundTwoResults.increaseCorrectAnswers();
        roundTwoResults.calculateScore(); // 1 out of 1 correct
        sessionsResults.addResult(roundOneResults); // returns 1/2 (50%)
        sessionsResults.addResult(roundTwoResults); // returns 1/1 (100%)
    }

    @Test
    void testStringConversion_twoRoundResults() {
        createResultList();
        assertEquals("1. 1/2 (50%)\n2. 1/1 (100%)\n", sessionsResults.toString());
    }
}
