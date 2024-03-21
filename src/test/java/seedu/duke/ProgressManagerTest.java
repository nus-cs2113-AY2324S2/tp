package seedu.duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.logging.Logger;
import java.util.logging.Level;

public class ProgressManagerTest {
    ResultsList sessionResults = new ResultsList();

    private Logger logger = Logger.getLogger("ProgressManagerTestLogger");

    void createResultList() {

        logger.log(Level.INFO, "Creating dummy session results.");

        ResultsList sessionResults = new ResultsList();
        Results results = new Results();
        Question questionOne = new Question("Question 1", "Solution 1", "Explanation 1");
        Question questionTwo = new Question("Question 2", "Solution 2", "Explanation 2");
        QuestionsList questions = new QuestionsList();

        questions.addQuestion(questionOne);
        results.increaseNumberOfQuestions();
        questions.addQuestion(questionTwo);
        results.increaseNumberOfQuestions();

        sessionResults.addResults(results);
    }


    @Test
    public void clearProgress_threeResults_emptyResultsList() {

        createResultList();

        logger.log(Level.INFO, "Testing progress manager.");
        ProgressManager pm = new ProgressManager(sessionResults);
        sessionResults = pm.clearProgress();
        int numOfResults = sessionResults.getSizeOfAllResults();
        assertEquals(0, sessionResults.getSizeOfAllResults());
        assert numOfResults == 0 : "Number of results should be 0.";
    }
}
