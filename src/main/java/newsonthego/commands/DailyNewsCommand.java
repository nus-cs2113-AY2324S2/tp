package newsonthego.commands;

import newsonthego.NewsArticle;

import java.util.List;
import java.util.logging.Level;
import java.util.stream.Collectors;

import static newsonthego.FormatDate.formatFromUser;
import static newsonthego.ui.UI.printEmptyLine;
import static newsonthego.ui.UI.printHeadline;
import static newsonthego.ui.UI.printHeadlinesFound;
import static newsonthego.ui.UI.printHeadlinesNotFound;

import java.util.logging.Logger;

public class DailyNewsCommand {

    private static final Logger LOGGER = Logger.getLogger("NewsOnTheGo");
    public List<NewsArticle> articlesOfTheDay;

    private final int dateindex = 1;

    /**
     * Finds articles that match the date input by the user and prints out the list
     *
     * @param input is the user input
     * @param list is the list of articles
     */
    public DailyNewsCommand(String input, List<NewsArticle> list) {
        assert(!list.isEmpty());

        String[] splitInput = input.split(" ", 2);
        String date = splitInput[dateindex];

        String formattedDate = formatFromUser(date);

        if (formattedDate == null) {
            LOGGER.log(Level.WARNING, "Invalid date format");
            return;
        }

        articlesOfTheDay = list.stream()
                .filter(article -> article.getDate().equals(formattedDate))
                .collect(Collectors.toList());

        if (articlesOfTheDay.isEmpty()) {
            printHeadlinesNotFound(date);
        } else {
            printHeadlinesFound();
            printEmptyLine();
            for (NewsArticle article : articlesOfTheDay) {
                printHeadline(article.getHeadline());
            }
            printEmptyLine();
        }
    }

    public List<NewsArticle> getArticlesOfTheDay() {
        return articlesOfTheDay;
    }
}
