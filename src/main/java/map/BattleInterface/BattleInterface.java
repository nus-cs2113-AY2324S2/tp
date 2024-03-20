package map.BattleInterface;

import InteractableEntity.Enemy;
import InteractableEntity.InteractableEntity;
import command.Command;
import map.AMap;
import parser.Parser;
import textbox.PlayerStatus;
import textbox.TextBox;
import ui.Ui;

import java.lang.management.PlatformLoggingMXBean;
import java.util.ArrayList;

public class BattleInterface extends AMap {
    protected PlayerStatus currentPlayer;
    protected TextBox currentTextBox;
    protected InteractableEntity currentEntity;


    public BattleInterface(PlayerStatus player, TextBox text, InteractableEntity entity) {
        this.currentPlayer = player;
        this.currentTextBox = text;
        this.currentEntity = entity;

    }

    public void initMap(int givenWidth, int givenHeight) {
        this.width = givenWidth;
        this.height = givenHeight;
        this.currentMap = new ArrayList<>(height);

        for (int i = 0; i < height; i += 1) {
            ArrayList<Character> row = new ArrayList<>(width);
            for (int j = 0; j < width; j += 1) {
                if (i == 0 || i == height - 1) {
                    row.add('=');
                } else if (j == 0 || j == width - 1) {
                    row.add('|');
                } else if ((i == height / 2 - 2 || i == height / 2 + 1)
                        && (j >= width / 2 - 2 && j <= width / 2 + 1)
                        || (j == width / 2 - 2 || j == width / 2 + 1)
                        && (i >= height / 2 - 2 && i <= height / 2 + 1)) {
                    row.add('@');
                } else {
                    row.add(' ');
                }
            }
            currentMap.add(row);
        }
    }


    public void playerHitEnemy(){
        if (currentEntity instanceof Enemy){
            int dmgDone = 10 - (10 * ((Enemy) currentEntity).getDefence());
            ((Enemy) currentEntity).harmHealth(dmgDone);
        }
    }

    public void enemyHitPlayer(){
        if (currentEntity instanceof Enemy){
            int dmgDone = ((Enemy) currentEntity).getDamage();
            currentPlayer.harmHealth(dmgDone);
        }
    }


    public InteractableEntity getCurrentEntity(){
        return currentEntity;
    }

    public PlayerStatus getCurrentPlayer(){
        return currentPlayer;
    }

}
