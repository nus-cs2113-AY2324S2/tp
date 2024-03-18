package map;

import java.util.ArrayList;

public abstract class AMap {
    protected static ArrayList<AMap> storedMap;
    protected int width;
    protected int height;
    protected ArrayList<ArrayList<Character>> currentMap;
    protected int playerX;
    protected int playerY;
    protected String mapName;


    public void initMap(int givenWidth, int givenHeight) {
        this.width = givenWidth;
        this.height = givenHeight;
        this.currentMap = new ArrayList<>(height);

        for (int i = 0; i < height; i += 1) {
            ArrayList<Character> row = new ArrayList<>(width);
            for (int j = 0; j < width; j += 1) {
                row.add('.');
            }
            currentMap.add(row);
        }
    }

    public ArrayList<ArrayList<Character>> getCurrentMap() {
        return currentMap;
    }

    public void initPlayerLocation(int x, int y) {
        if (x >= 0 && x < width && y >= 0 && y < height) {
            currentMap.get(y).set(x, 'P');
            this.playerX = x;
            this.playerY = y;
        }
    }


    public void movePlayerUpOne() {
        if (this.playerY - 1 >= 0) {
            currentMap.get(playerY).set(playerX, '.');
            currentMap.get(playerY - 1).set(playerX, 'P');
            this.playerY -= 1;
        }
    }


    public void movePlayerDownOne() {
        if (this.playerY + 1 < height) {
            currentMap.get(playerY).set(playerX, '.');
            currentMap.get(playerY + 1).set(playerX, 'P');
            this.playerY += 1;
        }
    }


    public void movePlayerLeftOne() {
        if (this.playerX - 1 >= 0) {
            currentMap.get(playerY).set(playerX, '.');
            currentMap.get(playerY).set(playerX - 1, 'P');
            this.playerX -= 1;
        }
    }


    public void movePlayerRightOne() {
        if (this.playerY + 1 < width) {
            currentMap.get(playerY).set(playerX, '.');
            currentMap.get(playerY).set(playerX + 1, 'P');
            this.playerX += 1;
        }
    }


    public ArrayList<ArrayList<Character>> getMap() {
        return currentMap;
    }

    public String handleInteract() {
        if (playerY > 0 && currentMap.get(playerY - 1).get(playerX) != '.') {
            return String.valueOf(currentMap.get(playerY - 1).get(playerX));
        }

        if (playerX < currentMap.get(0).size() - 1 && currentMap.get(playerY).get(playerX + 1) != '.') {
            return String.valueOf(currentMap.get(playerY).get(playerX + 1));
        }

        if (playerY < currentMap.size() - 1 && currentMap.get(playerY + 1).get(playerX) != '.') {
            return String.valueOf(currentMap.get(playerY + 1).get(playerX));
        }

        if (playerX > 0 && currentMap.get(playerY).get(playerX - 1) != '.') {
            return String.valueOf(currentMap.get(playerY).get(playerX - 1));
        }
        return "no interaction";
    }

    public void placeMonsterInTheMap(int x, int y) {
        currentMap.get(y).set(x, '@');
    }

    public int getPlayerX() {
        return playerX;
    }

    public int getPlayerY() {
        return playerY;
    }

}
