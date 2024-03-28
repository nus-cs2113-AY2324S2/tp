package newsonthego;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class ArticleScrapper {

    public static void scrapeArticle(String url, String outputFolderPath) {
        try {
            Document doc = Jsoup.connect(url).get();

            // Extract theme from navigation menu (assuming it's in a specific element)
            Element themeElement = doc.selectFirst(".navigation-menu .current-page");
            String theme = (themeElement != null) ? themeElement.text() : "Theme not found";

            // Extract author name
            Element authorElement = doc.selectFirst("meta[name=author]");
            String author = (authorElement != null) ? authorElement.attr("content") : "Unknown";

            // Extract abstract
            Element abstractElement = doc.selectFirst("meta[name=description]");
            String abstractText = (abstractElement != null) ? abstractElement.attr("content") : "Abstract not found";

            // Extract date
            Element dateElement = doc.selectFirst("meta[property=article:published_time]");
            String date = (dateElement != null) ? dateElement.attr("content") : "Date not found";

            // Extract headlines
            String headline = doc.title();

            // Generate the output file path within the data folder
            String outputFilePath = outputFolderPath + File.separator + "testArticleScrapper.txt";

            // Write the extracted information to the output file
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFilePath))) {
                writer.write("\"" + headline + "\";" + author + ";" + date + ";" + theme + ";" + url + ";");
                writer.write("Abstract: " + abstractText + "\n");
                System.out.println("Data saved to: " + outputFilePath);
            } catch (IOException e) {
                e.printStackTrace();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
