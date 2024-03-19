package newsonthego;

public class NewsArticle {
    protected String headline;
    protected String author;
    protected String date;
    protected String source;
    protected int importance;

    protected int reliability;

    protected int bias;

    protected String content;

    public NewsArticle(String headline, String author, String date, String source, int importance, int reliability,
                       int bias) {
        this.headline = headline;
        this.author = author;
        this.date = date;
        this.source = source;
        this.importance = importance;
        this.reliability = reliability;
        this.bias = bias;
        this.content = null;
    }

    public String getHeadline() {
        return headline;
    }

    public void setHeadline(String headline) {
        this.headline = headline;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public int getImportance() {
        return importance;
    }

    public void setImportance(int importance) {
        this.importance = importance;
    }

    public int getReliability() {
        return reliability;
    }

    public void setReliability(int reliability) {
        this.reliability = reliability;
    }

    public int getBias() {
        return bias;
    }

    public void setBias(int bias) {
        this.bias = bias;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}

