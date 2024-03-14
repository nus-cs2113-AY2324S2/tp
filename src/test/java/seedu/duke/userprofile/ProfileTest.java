package seedu.duke.userprofile;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ProfileTest {

    @Test
    void outputName() {
        assertEquals("Jack", new Profile("Jack", "Robotics").outputName());
    }
}
