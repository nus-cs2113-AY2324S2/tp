package command;

import map.AMap;

public abstract class Command {

    protected String commandDescription;
    protected AMap currentMap;
    public abstract void execute();
    public Command(){
        commandDescription = "Impossible";
        currentMap = null;
    }
    public String getCommandDescription(){
        return commandDescription;
    }
    public AMap getCurrentMap(){
        return currentMap;
    }

    public void setCurrentMap(AMap givenMap){
        currentMap = givenMap;
    }
}
