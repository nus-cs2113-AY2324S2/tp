package newsonthego;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Set;
import java.io.IOException;

public class UserPreferences {

    private static final String PREFERENCES_FILE = "userPreferences.txt";
    private final Set<String> interestedTopics;


    public UserPreferences() {
        this.interestedTopics = new HashSet<>();
        loadPreferences();
    }

    public void addTopic(String topic) {
        interestedTopics.add(topic.trim().toLowerCase());
        savePreferences();
    }

    public void removeTopic(String topic) {
        interestedTopics.remove(topic.trim().toLowerCase());
        savePreferences();
    }

    public Set<String> getInterestedTopics() {
        return interestedTopics;
    }

    private void loadPreferences() {
        try {
            Files.lines(Paths.get(PREFERENCES_FILE)).forEach(line -> interestedTopics.add(line.trim().toLowerCase()));
        } catch (IOException e) {
            System.out.println("Could not load user preferences. Starting with an empty list of topics.");
        }
    }

    private void savePreferences() {
        try {
            Files.write(Paths.get(PREFERENCES_FILE), interestedTopics);
        } catch (IOException e) {
            System.out.println("Could not save user preferences.");
        }
    }

    @Override
    public String toString() {
        if (interestedTopics.isEmpty()) {
            return "You are not currently interested in any topics.";
        }
        StringBuilder sb = new StringBuilder("You are interested in the following topics:\n");
        interestedTopics.forEach(topic -> sb.append("- ").append(topic).append("\n"));
        return sb.toString();
    }
}
