package newsonthego;

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
}
