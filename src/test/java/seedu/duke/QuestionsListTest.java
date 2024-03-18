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
        question1 = new Question("question1", "answer1", "explanation1");
        question2 = new Question("question2", "answer2", "explanation2");
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
    void getAllExplanations_twoQuestions_twoExplanations() throws CustomException {
        createQuestionList();
        createTwoQuestions();
        questionList.addQuestion(question1);
        questionList.addQuestion(question2);

        String expectedOutput =
                "Explanation for question 1:" + System.lineSeparator()
                        + "explanation1" + System.lineSeparator()
                        + System.lineSeparator()
                        + "Explanation for question 2:" + System.lineSeparator()
                        + "explanation2" + System.lineSeparator()
                        + System.lineSeparator();

        assertEquals(expectedOutput, questionList.getAllExplanations());
    }

    @Test
    void getAllExplanations_noQuestions_customException() {
        createQuestionList(); // empty question List

        assertThrows(CustomException.class, // expect Exception
                () -> questionList.getAllExplanations());
    }
}
