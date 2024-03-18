package command;

import textbox.*;
import map.AMap;


public abstract class Command {
    protected TextBox textBox;
    protected PlayerStatus playerStatus;
    protected String commandDescription;
    protected AMap currentMap;

    public abstract void execute();

    public Command() {
        commandDescription = "Impossible";
        currentMap = null;
    }

    public String getCommandDescription() {
        return commandDescription;
    }

    public AMap getCurrentMap() {
        return currentMap;
    }

    public void setCurrentMap(AMap givenMap) {
        currentMap = givenMap;
    }

    public void setPlayerStatus(PlayerStatus playerStatus) {
        this.playerStatus = playerStatus;
    }

    public void setTextBox(TextBox textBox){
        this.textBox = textBox;
    }
}
