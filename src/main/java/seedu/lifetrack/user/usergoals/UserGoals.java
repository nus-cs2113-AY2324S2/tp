package seedu.lifetrack.usergoals;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;


public class UserGoals {


    public static void getGoals() {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://fitness-api.p.rapidapi.com/fitness"))
                    .header("content-type", "application/x-www-form-urlencoded")
                    .header("X-RapidAPI-Key", "313560bcc6msh96f48210f860abep1be49djsn7c3a2058360d")
                    .header("X-RapidAPI-Host", "fitness-api.p.rapidapi.com")
                    .method("POST", HttpRequest.BodyPublishers.ofString("height=190&weight=80&age=30&gender=male&exercise=little&goal=maintenance"))
                    .build();
            HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println(response.body());
        } catch (IOException | InterruptedException e) {
            System.out.println("OOPS");
        }
    }
}
