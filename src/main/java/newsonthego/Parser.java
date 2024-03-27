package newsonthego;

import java.util.List;
import newsonthego.commands.DailyNewsCommand;
import newsonthego.commands.InfoNewsCommand;

public class Parser {

    public static void handleCommand(String command, String line, List<NewsArticle> list) {
        switch (NewsOnTheGo.Command.valueOf(command.toUpperCase())) {
        case DAILY:
            new DailyNewsCommand(line, list);
            break;
        case GET:
            NewsOnTheGo.getNews(line, list);
            break;
        case TOPICS:
            NewsOnTheGo.showTopics();
            break;
        case FILTER:
            NewsOnTheGo.filterNews(line);
            break;
        case SAVE:
            NewsOnTheGo.saveNews(line, list);
            break;
        case SOURCE:
            NewsOnTheGo.sourceNews(line, list);
            break;
        case INFO:
            InfoNewsCommand.printNewsInfo(line, list);
            break;
        case BYE:
            System.out.println("Bye. Hope to see you again soon!");
            break;
        default:
            System.out.println("I'm sorry, but I don't know what that means :-(");
            break;
        }
    }

}
