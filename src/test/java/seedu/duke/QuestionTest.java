package seedu.duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class QuestionTest {

    Question oneQuestion;
    final String question1 = "question1";
    final String answer1 = "answer1";
    final String explanation1 = "explanation1";

    void createQuestion() {
        oneQuestion = new Question(question1, answer1, explanation1);
    }

    // 3 part format
    // methodBeingTested_conditionToTest_expectedOutcome

    @Test
    void getExplanation_oneQuestion_expectExplanation() {
        createQuestion();

        assertEquals(explanation1, oneQuestion.getExplanation());
    }

    @Test
    void getQuestion_oneQuestion_expectQuestion() {
        createQuestion();

        assertEquals(question1, oneQuestion.getQuestion());
    }

    @Test
    void getAnswer_oneQuestion_expectAnswer() {
        createQuestion();

        assertEquals(answer1, oneQuestion.getAnswer());
    }
}
