package newsonthego;

import static newsonthego.NewsOnTheGo.findTopicIndex;
import static newsonthego.NewsOnTheGo.FILENAME;
import static newsonthego.NewsOnTheGo.importNewsFromText;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import newsonthego.commands.DailyNewsCommand;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Set;



class NewsOnTheGoTest {

    private static final String PREFERENCES_FILE = "userPreferences.txt";

    private UserPreferences userPreferences;
    
    @Test
    public void sampleTest() {
        assertTrue(true);
        assertTrue(true);
    }

    @Test
    public void sampleTestSource() {
        List<NewsArticle> newsArticles = importNewsFromText("data/sampleNews.txt");
        assertEquals("Financial Times", newsArticles.get(1).getSource());
    }

    @Test
    public void testFindTopicIndex() {
        int index = findTopicIndex("abcdefg");
        assertEquals(-1, index);
    }

    @Test
    public void dailyFunctionTest() {
        String input = "daily March 10, 2024";
        String expected = "\"Scientists Discover New Species of Butterfly in the Amazon\"";

        List<NewsArticle> newsArticles = importNewsFromText(FILENAME);

        DailyNewsCommand command = new DailyNewsCommand(input, newsArticles);
        List<NewsArticle> outputHeadlines = command.getArticlesOfTheDay();
        assertEquals(1, outputHeadlines.size());
        String output = outputHeadlines.get(0).getHeadline();
        assertEquals(expected, output);
    }
      
    @Test
    public void testInfoNewsValidIndex() {
        List<NewsArticle> newsArticles = importNewsFromText("data/sampleNews.txt");
        String expectedOutput = "Importance: 9\nReliability: 9\nBias: 3";
        assertEquals(expectedOutput, "Importance: " + newsArticles.get(1).getImportance() +
                "\nReliability: " + newsArticles.get(1).getReliability() +
                "\nBias: " + newsArticles.get(1).getBias());
    }

    // UserPreferences Tests
    @BeforeEach
    void setUpUserPreferences() {
        // Initialize UserPreferences before each test
        userPreferences = new UserPreferences();
        userPreferences.getInterestedTopics().clear();
    }

    @AfterEach
    void tearDownUserPreferences() throws Exception {

        Files.deleteIfExists(Paths.get(PREFERENCES_FILE));
    }

    @Test
    void testAddAndRemoveTopic() {
        String testTopic = "science";
        userPreferences.addTopic(testTopic);
        assertTrue(userPreferences.getInterestedTopics().contains(testTopic), "Topic should be added to preferences");

        userPreferences.removeTopic(testTopic);
        assertFalse(userPreferences.getInterestedTopics().contains(testTopic), "Topic should be removed");
    }

    @Test
    void testPersistence() {
        String testTopic = "technology";
        userPreferences.addTopic(testTopic);

        // Simulate reloading preferences by creating a new instance
        UserPreferences newUserPreferences = new UserPreferences();
        Set<String> loadedTopics = newUserPreferences.getInterestedTopics();

        assertTrue(loadedTopics.contains(testTopic), "Persisted topic should be loaded on new instance initialization");
    }

    @Test
    void testToString() {
        String expectedInitialMessage = "You are not currently interested in any topics.";
        assertEquals(expectedInitialMessage, userPreferences.toString(), "Message should indicate no interests");

        String testTopic = "health";
        userPreferences.addTopic(testTopic);
        String expectedMessageAfterAddition = "You are interested in the following topics:\n- health\n";
        assertEquals(expectedMessageAfterAddition, userPreferences.toString(), "Message should list added topics");
    }
}
