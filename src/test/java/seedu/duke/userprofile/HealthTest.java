package seedu.duke.userprofile;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HealthTest {

    @Test
    void outputHealth() {
        assertEquals("100", new Health().outputHealth());
    }
}

