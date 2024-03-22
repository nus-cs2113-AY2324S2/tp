package newsonthego;


import newsonthego.ui.UI;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class NewsOnTheGo {

    public static final String FILENAME = "data/sampleNews.txt";
    private static final Logger logger = Logger.getLogger("NewsOnTheGo");
    private static final ArrayList<NewsTopic> newsTopics = new ArrayList<>();

    /**
     * Main entry-point for the java.newsonthego.NewsOnTheGo application.
     */
    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        UI.initializeUI(in);

        List<NewsArticle> newsArticles = NewsImporter.importNewsFromText(FILENAME, newsTopics);

        while (true) {
            System.out.println("What do you want from me?");
            String line = in.nextLine();
            String command = line.split(" ")[0];
            try {
                boolean endLoop = processCommand(command, line, newsArticles);
                if (endLoop) {
                    break;
                }
            } catch (Exception e) {
                UI.printError(e.getMessage());
            }
        }
        logger.log(Level.INFO, "Ending NewsOnTheGo");
    }

    public enum Command {
        DAILY, GET, TOPICS, FILTER, SAVE, SOURCE, INFO, BYE
    }

    private static boolean processCommand(String command, String line, List<NewsArticle> list) {
        assert !command.isEmpty();
        Parser.handleCommand(command, line, list);
        return command.equalsIgnoreCase(Command.BYE.toString());
    }

    /**
     * Displays the list of available news topics.
     * This method prints the list of topics along with their names.
     */
    static void showTopics() {
        System.out.println("Here are the list of topics for your viewing:");
        for (NewsTopic topic : newsTopics) {
            System.out.println(" - " + topic.getTopicName());
        }
    }

    static void getNews(String line, List<NewsArticle> list) {
    }

    /**
     * Finds the index of a news topic in the list of topics.
     * This method performs a binary search to find the index of the specified topic.
     *
     * @param topic the name of the topic to search for
     * @return the index of the topic if found, or -1 if the topic is not found
     */
    static int findTopicIndex(String topic) {
        int left = 0;
        int right = newsTopics.size() - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            int comparisonResult = topic.trim().compareToIgnoreCase(newsTopics.get(mid).getTopicName().trim());
            if (comparisonResult == 0) {
                return mid;
            } else if (comparisonResult < 0) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return -1;
    }

    /**
     * Filters news articles based on a specified topic.
     * This method finds the index of the specified topic and prints news articles related to that topic.
     *
     * @param line the input string containing the topic to filter
     */
    static void filterNews(String line) {
        int topicIndex = findTopicIndex(line.substring(6).trim());
        System.out.println(topicIndex);
        if (topicIndex < 0) {
            System.out.println("Sorry, this topic is not available right now :(");
        } else {
            System.out.println("Here are the news articles related to the topic of your interest:");
            newsTopics.get(topicIndex).printNewsArticles();
        }
    }

    static void saveNews(String line, List<NewsArticle> list) {
    }

    /**
     * Enter the news article number as stored in the array, and it will return the source of the news article.
     */
    static void sourceNews(String line, List<NewsArticle> list) {
        String[] split = line.split(" ");
        int index = Integer.parseInt(split[1]) + 1;
        System.out.println(list.get(index).getSource());
    }


}
