package command.fight;

import command.Command;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FightCommandTest {
    Command a;
    @BeforeEach
    void setup() {
        a = new FightingCommand();
    }

    @Test
    void fightExecuteCorrectly() {
        assertEquals("FIGHT!", a.getCommandDescription());
    }
}
