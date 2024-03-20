package newsonthego;

import newsonthego.commands.DailyNewsCommand;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class NewsOnTheGo {

    public static final String FILENAME = "data/sampleNews.txt";
    private static final Logger logger = Logger.getLogger("NewsOnTheGo");

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
        }

        return list;
    }

    public enum Command {
        DAILY, GET, FILTER, SAVE, SOURCE, BYE
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
        case FILTER:
            filterNews(line, list);
            break;
        case SAVE:
            saveNews(line, list);
            break;
        case SOURCE:
            sourceNews(line, list);
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

    private static void getNews(String line, List<NewsArticle> list) {
    }

    private static void filterNews(String line, List<NewsArticle> list) {
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
}
