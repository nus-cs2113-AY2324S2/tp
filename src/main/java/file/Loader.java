package file;

import player.PlayerProfile;
import ui.ResponseManager;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Loader {
    private static final String FILE_PATH = "data/PlayerProfile.json";

    public static PlayerProfile loadProfile() {
        File file = new File(FILE_PATH);

        if (!file.exists()) {
            ResponseManager.indentPrint("No existing profile found. Starting new.\n");
            return null;
        }

        StringBuilder content = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }
        } catch (IOException e) {
            ResponseManager.indentPrint("Error reading profile: " + e.getMessage() + "\n");
            return null;
        }

        return parseJson(content.toString());
    }

    private static PlayerProfile parseJson(String json) {
        String name = extractValue(json, "name");
        String occupation = extractValue(json, "occupation");
        int asset = Integer.parseInt(extractValue(json, "asset"));
        int health = Integer.parseInt(extractValue(json, "health"));
        PlayerProfile playerProfile = new PlayerProfile(name, occupation);
        playerProfile.addAsset(asset - 5000);
        playerProfile.addHealth(health - 100);

        return playerProfile;
    }

    private static String extractValue(String json, String key) {
        String pattern = "\"" + key + "\": \"";
        int startIndex = json.indexOf(pattern) + pattern.length();
        int endIndex = json.indexOf("\"", startIndex);
        return json.substring(startIndex, endIndex);
    }

}
