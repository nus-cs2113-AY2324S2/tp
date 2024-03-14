package seedu.duke;

import org.junit.jupiter.api.Test;
import seedu.duke.userprofile.Profile;

import static org.junit.jupiter.api.Assertions.*;

class InitializeCommandTest {

    @Test
    void getProfile() {
        Profile profile = new Profile("John", "Robotics");
        assertEquals(profile.toString(), InitializeCommand.getProfile(profile));
    }
}