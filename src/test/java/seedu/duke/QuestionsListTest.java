package seedu.duke;

import org.junit.jupiter.api.Test;
import seedu.duke.exceptions.CustomException;

import java.util.logging.Level;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class QuestionsListTest {
    private static final Logger LOGGER = Logger.getLogger("testLogger"); // for logging purposes
    QuestionsList questionsList;
    Question question1;
    Question question2;

    void createQuestionList() {
        questionsList = new QuestionsList();
    }

    void createTwoQuestions() {
        question1 = new Question("question1", "solution1", "explanation1");
        question2 = new Question("question2", "solution2", "explanation2");
    }

    // 3 part format
    // methodBeingTested_conditionToTest_expectedOutcome

    @Test
    void getSize_addTwoQuestions_twoQuestions() {
        LOGGER.log(Level.INFO, "startLog1");
        LOGGER.setLevel(Level.OFF); // disableLogs

        createQuestionList();
        createTwoQuestions();
        questionsList.addQuestion(question1);
        questionsList.addQuestion(question2);

        assertEquals(2, questionsList.getSize());
    }

    @Test
    void getAllSolutions_twoQuestions_twoExplanations() throws CustomException {
        LOGGER.log(Level.INFO, "This log will be ignored");
        LOGGER.setLevel(Level.ALL); // enableLogs

        createQuestionList();
        createTwoQuestions();
        questionsList.addQuestion(question1);
        questionsList.addQuestion(question2);

        String expectedOutput =
                "Solution for question 1:" + System.lineSeparator()
                        + "solution1" + System.lineSeparator()
                        + System.lineSeparator()
                        + "Solution for question 2:" + System.lineSeparator()
                        + "solution2" + System.lineSeparator()
                        + System.lineSeparator();
        if (questionsList.getSize() != 0) {
            LOGGER.log(Level.WARNING, "warningLog1");
        }
        LOGGER.setLevel(Level.WARNING);
        LOGGER.log(Level.INFO, "this log will be ignored");
        LOGGER.log(Level.WARNING, "but this log will not coz priority=WARNING");
        assertEquals(expectedOutput, questionsList.getAllSolutions());
        LOGGER.log(Level.WARNING, "this log will be printed");
    }

    @Test
    void getAllSolutions_noQuestions_customException() {
        createQuestionList(); // empty question List

        assertThrows(CustomException.class, // expect Exception
                () -> questionsList.getAllSolutions());
    }
}
