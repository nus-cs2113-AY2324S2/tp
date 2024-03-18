package seedu.duke;

import org.junit.jupiter.api.Test;
import seedu.duke.exceptions.CustomException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class QuestionsListTest {
    QuestionsList questionList;
    Question question1;
    Question question2;

    void createQuestionList() {
        questionList = new QuestionsList();
    }

    void createTwoQuestions() {
        question1 = new Question("question1", "solution1", "explanation1");
        question2 = new Question("question2", "solution2", "explanation2");
    }

    // 3 part format
    // methodBeingTested_conditionToTest_expectedOutcome

    @Test
    void getSize_addTwoQuestions_twoQuestions() {
        createQuestionList();
        createTwoQuestions();
        questionList.addQuestion(question1);
        questionList.addQuestion(question2);

        assertEquals(2, questionList.getSize());
    }

    @Test
    void getAllSolutions_twoQuestions_twoExplanations() throws CustomException {
        createQuestionList();
        createTwoQuestions();
        questionList.addQuestion(question1);
        questionList.addQuestion(question2);

        String expectedOutput =
                "Solution for question 1:" + System.lineSeparator()
                        + "solution1" + System.lineSeparator()
                        + System.lineSeparator()
                        + "Solution for question 2:" + System.lineSeparator()
                        + "solution2" + System.lineSeparator()
                        + System.lineSeparator();

        assertEquals(expectedOutput, questionList.getAllSolutions());
    }

    @Test
    void getAllSolutions_noQuestions_customException() {
        createQuestionList(); // empty question List

        assertThrows(CustomException.class, // expect Exception
                () -> questionList.getAllSolutions());
    }
}
