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
}
