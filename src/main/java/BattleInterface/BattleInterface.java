package BattleInterface;

import InteractableEntity.Enemy;
import InteractableEntity.InteractableEntity;
import textbox.PlayerStatus;
import textbox.TextBox;

import java.lang.management.PlatformLoggingMXBean;

public class BattleInterface {
    protected PlayerStatus currentPlayer;
    protected TextBox currentTextBox;
    protected InteractableEntity currentEntity;

    public BattleInterface(PlayerStatus player, TextBox text, InteractableEntity entity){
        this.currentPlayer = player;
        this.currentTextBox = text;
        this.currentEntity = entity;
    }
}
