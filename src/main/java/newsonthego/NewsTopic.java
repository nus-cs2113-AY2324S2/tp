package newsonthego;

import java.util.ArrayList;

public class NewsTopic {
    protected String topicName;
    protected ArrayList<NewsArticle> relatedNewsArticles;
    public NewsTopic(String topicName, NewsArticle newsArticle) {
        this.topicName = topicName;
        relatedNewsArticles = new ArrayList<>();
        relatedNewsArticles.add(newsArticle);
    }

    public String getTopicName() {
        return topicName;
    }

    public void addNewsArticle(NewsArticle newsArticle) {
        this.relatedNewsArticles.add(newsArticle);
    }

    public void printNewsArticles() {
        for(NewsArticle newsArticle : this.relatedNewsArticles) {
            System.out.println(newsArticle.getHeadline());
        }
    }
}
