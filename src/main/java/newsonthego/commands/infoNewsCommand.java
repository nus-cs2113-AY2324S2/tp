package newsonthego.commands;

import newsonthego.NewsArticle;

import java.util.List;

public class infoNewsCommand {

    /**
     * Prints the importance, reliability, and bias of a news article based on its index in the list.
     *
     * @param line The command line containing the index of the news article.
     * @param list The list of NewsArticle objects containing news articles.
     */
    public static void printNewsInfo(String line, List<NewsArticle> list) {
        String[] split = line.split(" ");
        int index = Integer.parseInt(split[1]) - 1;
        if (index >= 0 && index < list.size()) {
            NewsArticle article = list.get(index);
            System.out.println("Importance: " + article.getImportance());
            System.out.println("Reliability: " + article.getReliability());
            System.out.println("Bias: " + article.getBias());
        } else {
            System.out.println("Invalid article index.");
        }
    }
}
