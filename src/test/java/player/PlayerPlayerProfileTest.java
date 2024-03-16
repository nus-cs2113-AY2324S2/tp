package player;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PlayerPlayerProfileTest {

    @Test
    void outputName() {
        assertEquals("Jack", new PlayerProfile("Jack", "Robotics").getName());
    }
}
