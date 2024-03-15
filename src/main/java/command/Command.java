package command;

import java.util.ArrayList;

public abstract class Command {

    protected String commandDescription;
    protected ArrayList<ArrayList<Character>> currentMap;
    public abstract void execute();
    public Command(){
        commandDescription = "Impossible";
        currentMap = null;
    }
    public String getCommandDescription(){
        return commandDescription;
    }
    public ArrayList<ArrayList<Character>> getCurrentMap(){
        return currentMap;
    }
}
