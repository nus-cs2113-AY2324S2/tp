package map;
import command.Command;
import java.util.ArrayList;

public abstract class Map {
    protected int width;
    protected int height;
    protected ArrayList<ArrayList<Character>> storedMap;
    protected int playerX;
    protected int playerY;
    protected String mapName;



    public void initMap(int givenWidth, int givenHeight){
        this.width = givenWidth;
        this.height = givenHeight;
        this.storedMap = new ArrayList<>(height);

        for (int i = 0; i < height; i += 1){
            ArrayList<Character> row = new ArrayList<>(width);
            for (int j = 0; j < width; j += 1){
                row.add('.');
            }
            storedMap.add(row);
        }
    }


    public ArrayList<ArrayList<Character>> getStoredMap() {
        return storedMap;
    }

    public void initPlayerLocation(int x, int y){
        if (x >= 0 && x < width && y >= 0 && y < height){
            storedMap.get(y).set(x, 'P');
            this.playerX = x;
            this.playerY = y;
        }
    }


    public void movePlayerUpOne(){
        if (this.playerY - 1 >= 0){
            storedMap.get(playerY).set(playerX, '.');
            storedMap.get(playerY - 1).set(playerX, 'P');
        }
    }


    public void movePlayerDownOne(){
        if (this.playerY + 1 < height){
            storedMap.get(playerY).set(playerX, '.');
            storedMap.get(playerY + 1).set(playerX, 'P');
        }
    }


    public void movePlayerLeftOne(){
        if (this.playerX - 1 >= 0){
            storedMap.get(playerY).set(playerX, '.');
            storedMap.get(playerY).set(playerX - 1, 'P');
        }
    }


    public void movePlayerRightOne(){
        if (this.playerY + 1 < width){
            storedMap.get(playerY).set(playerX, '.');
            storedMap.get(playerY).set(playerX + 1, 'P');
        }
    }

    public void nextMapBasedOnCommand(Command userCommand){
        userCommand.execute();
    }
}
