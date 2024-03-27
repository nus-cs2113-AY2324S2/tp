package file;

import player.PlayerProfile;
import ui.ResponseManager;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Saver {
    private static final String FILE_PATH = "data/PlayerProfile.json";

    public static void saveProfile(PlayerProfile playerProfile) {
        File file = new File(FILE_PATH);

        try {

            if (file.createNewFile()) {
                ResponseManager.indentPrint("File created: " + file.getName() + "\n");
            }
            String json = constructJson(playerProfile);

            try (FileWriter fileWriter = new FileWriter(FILE_PATH)) {
                fileWriter.write(json);
                fileWriter.flush();
            } catch (IOException e) {
                ResponseManager.indentPrint("Error saving profile: " + e.getMessage() + "\n");
            }
        } catch (IOException e) {
            ResponseManager.indentPrint("Error saving profile: " + e.getMessage() + "\n");
        }

    }

    private static String constructJson(PlayerProfile playerProfile) {
        return "{\n" +
                " \"name\": \"" + playerProfile.getName() + "\", \n" +
                " \"occupation\": \"" + playerProfile.getOccupation() + "\", \n" +
                " \"asset\": \"" + playerProfile.getAsset().outputAsset() + "\", \n" +
                " \"health\": \"" + playerProfile.getHealth().outputHealth() + "\", \n" +
                "}";
    }

}
