package seedu.lifetrack.user.usergoals;

import seedu.lifetrack.user.User;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class UserGoals {

    private static HttpResponse<String> response;
    private static final int JSON_HEADING_SIZE = 67;
    private static final int CALORIES_LENGTH = 4;

    public static void getHealthInfo(User user) {
        try {
            String requestBody = "height=" + user.getHeight() + "&" +
                    "weight=" + user.getWeight() + "&" +
                    "age=" + user.getAge() + "&" +
                    "gender=" + user.getSex() + "&" +
                    "exercise=" + user.getExerciseLevels() + "&" +
                    "goal=" + user.getGoal().replace(" ", "_");
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://fitness-api.p.rapidapi.com/fitness"))
                    .header("content-type", "application/x-www-form-urlencoded")
                    .header("X-RapidAPI-Key", "313560bcc6msh96f48210f860abep1be49djsn7c3a2058360d")
                    .header("X-RapidAPI-Host", "fitness-api.p.rapidapi.com")
                    .method("POST", HttpRequest.BodyPublishers.ofString(requestBody))
                    .build();
            HttpResponse<String> response = HttpClient.newHttpClient()
                    .send(request, HttpResponse.BodyHandlers.ofString());
            int indexOfCalories = response.body().indexOf("neededEnergy") + JSON_HEADING_SIZE;
            int calories = Integer.parseInt(response.body()
                    .substring(indexOfCalories, indexOfCalories + CALORIES_LENGTH));
            System.out.println("\t You should consume " + calories + " calories a day to hit your goals!");
            user.setCaloriesRequired(calories);
        } catch (IOException | InterruptedException e) {
            System.out.println("OOPS");
        }
    }
}
