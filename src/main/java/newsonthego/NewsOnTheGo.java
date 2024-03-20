package newsonthego;

import newsonthego.commands.DailyNewsCommand;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class NewsOnTheGo {

    public static final String FILENAME = "data/sampleNews.txt";
    private static final Logger logger = Logger.getLogger("NewsOnTheGo");
    private static ArrayList<NewsTopic> newsTopics = new ArrayList<>();

    /**
     * Main entry-point for the java.newsonthego.NewsOnTheGo application.
     */
    public static void main(String[] args) {
        logger.log(Level.INFO, "Starting NewsOnTheGo");
        String logo = "\n" +
                ",-,-.                 ,---.     ,--,--'.       ,---.      \n" +
                "` | |   ,-. . , , ,-. |   | ,-. `- |   |-. ,-. |  -'  ,-. \n" +
                "  | |-. |-' |/|/  `-. |   | | |  , |   | | |-' |  ,-' | | \n" +
                " ,' `-' `-' ' '   `-' `---' ' '  `-'   ' ' `-' `---|  `-' \n" +
                "                                                ,-.|      \n" +
                "                                                `-+'      \n";
        System.out.println("Hello from\n" + logo);
        System.out.println("What is your name?");

        Scanner in = new Scanner(System.in);
        System.out.println("Hello " + in.nextLine());

        List<NewsArticle> newsArticles = importNewsFromText(FILENAME);

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
                System.out.println(e.getMessage());
            }
        }

        logger.log(Level.INFO, "Ending NewsOnTheGo");
    }

    static List<NewsArticle> importNewsFromText(String filename) {
        List<String> stringList;
        try {
            stringList = Files.readAllLines(Paths.get(filename));
        } catch (IOException e) {
            /* if there is any problem with the file or the file does
               not exist, return an empty list. this means if the file is
               corrupted it will not be used, and overwritten later */
            return new ArrayList<>();
        }

        List<NewsArticle> list = new ArrayList<>();
        for (String s : stringList) {
            String[] split = s.split(";");
            String headline = split[0];
            String author = split[1];
            String date = split[2];
            String source = split[3];
            String url = split[4];
            int importance = Integer.parseInt(split[5].split(" ")[1]);
            int reliability = Integer.parseInt(split[6].split(" ")[1]);
            int bias = Integer.parseInt(split[7].split(" ")[1]);
            NewsArticle newsArticle = new NewsArticle(headline, author, date, source, importance, reliability, bias);
            list.add(newsArticle);
            //identify related topic to the article
            String topic = split[8];
            boolean topicFound = false;
            //checks against current list of topics
            //if topic is recurring, adds article to the current topic list
            //else, a new topic will be added to the list of topics
            for(NewsTopic t: newsTopics) {
                if(topic.equalsIgnoreCase(t.getTopicName())){
                    t.addNewsArticle(newsArticle);
                    topicFound = true;
                }
            }
            if(!topicFound) {
                NewsTopic newsTopic = new NewsTopic(topic, newsArticle);
                newsTopics.add(newsTopic);
            }
        }
        Collections.sort(newsTopics, new TopicComparator());
        return list;
    }

    public enum Command {
        DAILY, GET, TOPICS, FILTER, SAVE, SOURCE, INFO, BYE
    }
    private static boolean processCommand(String command, String line, List<NewsArticle> list) {
        assert !command.isEmpty();
        switch (Command.valueOf(command.toUpperCase())) {
        case DAILY:
            new DailyNewsCommand(line, list);
            break;
        case GET:
            getNews(line, list);
            break;
        case TOPICS:
            showTopics();
            break;
        case FILTER:
            filterNews(line);
            break;
        case SAVE:
            saveNews(line, list);
            break;
        case SOURCE:
            sourceNews(line, list);
            break;
        case INFO:
            infoNews(line,list);
            break;
        case BYE:
            System.out.println("Bye. Hope to see you again soon!");
            return true;
        default:
            System.out.println("I'm sorry, but I don't know what that means :-(");
            break;
        }
        return false;
    }

    /**
     * Displays the list of available news topics.
     * This method prints the list of topics along with their names.
     */
    private static void showTopics() {
        System.out.println("Here are the list of topics for your viewing:");
        for(NewsTopic topic: newsTopics) {
            System.out.println(" - " +topic.getTopicName());
        }
    }

    private static void getNews(String line, List<NewsArticle> list) {
    }

    /**
     * Finds the index of a news topic in the list of topics.
     * This method performs a binary search to find the index of the specified topic.
     *
     * @param topic the name of the topic to search for
     * @return the index of the topic if found, or -1 if the topic is not found
     */
    private static int findTopicIndex(String topic) {
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
    private static void filterNews(String line) {
        int topicIndex = findTopicIndex(line.substring(6).trim());
        if (topicIndex < 0) {
            System.out.println("Sorry, this topic is not available right now :(");
        } else {
            System.out.println("Here are the news articles related to the topic of your interest:");
            newsTopics.get(topicIndex).printNewsArticles();
        }
    }

    private static void saveNews(String line, List<NewsArticle> list) {
    }

    /**
     * Enter the news article number as stored in the array, and it will return the source of the news article.
     */
    static void sourceNews(String line, List<NewsArticle> list) {
        String[] split = line.split(" ");
        int index = Integer.parseInt(split[1]) + 1;
        System.out.println(list.get(index).getSource());
    }

    /**
     * Prints the importance, reliability, and bias of a news article based on its index in the list.
     * @param line The command line containing the index of the news article.
     * @param list The list of NewsArticle objects containing news articles.
     */
    public static void infoNews(String line, List<NewsArticle> list) {
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
