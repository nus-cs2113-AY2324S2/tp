package seedu.duke;

import org.junit.jupiter.api.Test;
import seedu.duke.exceptions.CustomException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ResultsListTest {
    Results roundOneResults;
    Results roundTwoResults;
    Question roundOneQuestionOne;
    Question roundOneQuestionTwo;
    Question roundTwoQuestionOne;
    QuestionsList roundOneQuestions;
    QuestionsList roundTwoQuestions;
    ResultsList sessionsResults;

    void createResultList() {
        roundOneResults = new Results();
        roundOneQuestions = new QuestionsList();
        roundTwoResults = new Results();
        roundTwoQuestions = new QuestionsList();
        sessionsResults = new ResultsList();


        roundOneQuestionOne = new Question("question 1-1","solution 1-1","explanation 1-1");
        roundOneQuestions.addQuestion(roundOneQuestionOne);
        roundOneResults.increaseNumberOfQuestions();
        roundOneQuestionTwo = new Question("question 1-1","solution 1-1","explanation 1-1");
        roundOneQuestions.addQuestion(roundOneQuestionTwo);
        roundOneResults.increaseNumberOfQuestions();
        roundOneResults.increaseCorrectAnswers();
        roundOneResults.calculateScore(); // 1 out of 2 correct

        roundTwoQuestionOne = new Question("question 2-1","solution 2-1", "explanation 2-1");
        roundTwoQuestions.addQuestion(roundTwoQuestionOne);
        roundTwoResults.increaseNumberOfQuestions();
        roundTwoResults.increaseCorrectAnswers();
        roundTwoResults.calculateScore(); // 1 out of 1 correct

        sessionsResults.addResults(roundOneResults); // returns 1/2 (50%)
        sessionsResults.addResults(roundTwoResults); // returns 1/1 (100%)
    }

    @Test
    void testStringConversion_twoRoundResults() throws CustomException {
        createResultList();
        assertEquals("1/2 (50%)\n1/1 (100%)\n",
                sessionsResults.getSpecifiedResult(0).getScore()
                        +"\n"+ sessionsResults.getSpecifiedResult(1).getScore()+"\n");
    }
}
