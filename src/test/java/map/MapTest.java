package map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

class MapTest {

    private FirstMap map;

    @BeforeEach
    void setUp() {
        map = new FirstMap();
        map.initMap(5, 5);
        map.initPlayerLocation(2, 2);
    }

    @Test
    void mapShouldBeCorrectlyInitialized() {
        ArrayList<ArrayList<Character>> expectedMap = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            ArrayList<Character> row = new ArrayList<>();
            for (int j = 0; j < 5; j++) {
                if (i == 2 && j == 2) {
                    row.add('P');
                } else {
                    row.add('.');
                }
            }
            expectedMap.add(row);
        }

        assertEquals(expectedMap, map.getMap(), "Map should be initialized correctly with player at (2,2)");
    }

    @Test
    void playerShouldMoveUpCorrectly() {
        map.movePlayerUpOne();
        assertEquals('P', map.getStoredMap().get(1).get(2), "Player should move up 1 place");
        assertEquals('.', map.getStoredMap().get(2).get(2), "Original player position should be empty after moving up");
    }

    @Test
    void playerShouldMoveDownCorrectly() {
        map.movePlayerDownOne();
        assertEquals('P', map.getStoredMap().get(3).get(2), "Player should move down 1 place");
        assertEquals('.', map.getStoredMap().get(2).get(2), "Original player position should be empty after moving down");
    }

    @Test
    void playerShouldMoveLeftCorrectly() {
        map.movePlayerLeftOne();
        assertEquals('P', map.getStoredMap().get(2).get(1), "Player should move left 1 place");
        assertEquals('.', map.getStoredMap().get(2).get(2), "Original player position should be empty after moving left");
    }

    @Test
    void playerShouldMoveRightCorrectly() {
        map.movePlayerRightOne();
        assertEquals('P', map.getStoredMap().get(2).get(3), "Player moves right 1 place");
        assertEquals('.', map.getStoredMap().get(2).get(2), "Original player position should be empty after moving right");
    }
}

