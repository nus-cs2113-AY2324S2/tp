package newsonthego.commands;

import newsonthego.NewsArticle;

import java.util.List;
import java.util.stream.Collectors;

public class DailyNewsCommand {

    public List<NewsArticle> articlesOfTheDay;

    /**
     * Finds articles that match the date input by the user and prints out the list
     *
     * @param input is the user input
     * @param list is the list of articles
     */
    public DailyNewsCommand(String input, List<NewsArticle> list) {
        String[] split = input.split(" ", 2);
        String date = split[1];

        articlesOfTheDay = list.stream()
                .filter(a->a.getDate().equals(date))
                .collect(Collectors.toList());

        if (articlesOfTheDay.isEmpty()) {
            System.out.println("Nothing is found on this day: " + date);
        } else {
            System.out.println("Sure! Here are the headlines for today:");
            System.out.println();
            for (NewsArticle a : articlesOfTheDay) {
                System.out.println(a.getHeadline());
            }
            System.out.println();
        }
    }

    public List<NewsArticle> getArticlesOfTheDay() {
        return articlesOfTheDay;
    }
}
