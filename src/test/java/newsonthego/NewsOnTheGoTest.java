package newsonthego;

import static newsonthego.NewsOnTheGo.findTopicIndex;
import static newsonthego.NewsOnTheGo.FILENAME;
import static newsonthego.NewsOnTheGo.importNewsFromText;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import newsonthego.commands.DailyNewsCommand;
import org.junit.jupiter.api.Test;


import java.util.List;



class NewsOnTheGoTest {
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
        assertEquals(-1,index);

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
}

