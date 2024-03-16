
package reflect;

import exceptions.ReflectException;
import reflection.ReflectionQuestion;
import reflection.ReflectionQuestionBank;
import java.util.ArrayList;
import java.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

public class ReflectQuestionBankTest {

    private ReflectionQuestionBank reflectionQuestionBank;

    @BeforeEach
    public void setUp() {
        reflectionQuestionBank = new ReflectionQuestionBank();
    }

    @Test
    public void addReflectionQuestion_addQuestion_success() {
        ReflectionQuestion question = new ReflectionQuestion("What is reflection?");
        reflectionQuestionBank.addReflectionQuestion(question);
        assertEquals(42, reflectionQuestionBank.getTaskListSize());
    }

    @Test
    public void addReflectionQuestion_addBlankQuestion_skipOverBlankQuestion() {
        ReflectionQuestion question = new ReflectionQuestion("");
        reflectionQuestionBank.addReflectionQuestion(question);
        assertEquals(41, reflectionQuestionBank.getTaskListSize());
    }

    @Test
    public void getFiveRandomQuestions_getFiveQuestions_success() {
        ReflectionQuestion[] questions = {
            new ReflectionQuestion("Question 1"),
            new ReflectionQuestion("Question 2"),
            new ReflectionQuestion("Question 3"),
            new ReflectionQuestion("Question 4"),
            new ReflectionQuestion("Question 5"),
            new ReflectionQuestion("Question 6"),
            new ReflectionQuestion("Question 7"),
            new ReflectionQuestion("Question 8"),
            new ReflectionQuestion("Question 9"),
            new ReflectionQuestion("Question 10")
        };

        ArrayList<ReflectionQuestion> expectedQuestions = new ArrayList<>(Arrays.asList(questions));

        for (ReflectionQuestion question : expectedQuestions) {
            reflectionQuestionBank.addReflectionQuestion(question);
        }

        try {
            ArrayList<ReflectionQuestion> randomQuestions = reflectionQuestionBank.getFiveRandomQuestions();

            // Assert that there are exactly 5 questions returned
            assertEquals(5, randomQuestions.size());

            // Assert that all questions returned are from the expected set
            for (ReflectionQuestion question : randomQuestions) {
                assertNotNull(expectedQuestions.contains(question));
            }
        } catch (ReflectException e) {
            fail("Question list is empty: " + e.getMessage());
        }
    }

    @Test
    public void testGetTaskListSize() {
        assertEquals(41, reflectionQuestionBank.getTaskListSize());
    }
}
