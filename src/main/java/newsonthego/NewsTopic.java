package newsonthego;

import java.util.ArrayList;

public class NewsTopic {
    protected String topicName;
    protected ArrayList<NewsArticle> relatedNewsArticles;
    public NewsTopic(String topicName) {
        this.topicName = topicName;
        relatedNewsArticles = new ArrayList<>();
    }

    public String getTopicName() {
        return topicName;
    }

    public void addNewsArticle(NewsArticle newsArticle) {
        this.relatedNewsArticles.add(newsArticle);
    }
}
