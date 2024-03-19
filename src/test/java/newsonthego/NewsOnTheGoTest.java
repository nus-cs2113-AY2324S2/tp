package newsonthego;

import static newsonthego.NewsOnTheGo.importNewsFromText;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
    public void testInfoNewsValidIndex() {
        List<NewsArticle> newsArticles = importNewsFromText("data/sampleNews.txt");
        String expectedOutput = "Importance: 9\nReliability: 9\nBias: 3";
        assertEquals(expectedOutput, "Importance: " + newsArticles.get(1).getImportance() +
                "\nReliability: " + newsArticles.get(1).getReliability() +
                "\nBias: " + newsArticles.get(1).getBias());
    }

}

