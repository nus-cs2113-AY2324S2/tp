package command.mapmove;

import command.Command;

public abstract class MapMoveCommand extends Command {
    protected int commandModifier;
    public MapMoveCommand(){

    }
    public MapMoveCommand(String userInput) {
        commandDescription = "MapMove";
        userInput = userInput.trim();
        String[] splitUserInput = userInput.split("\\h+");
        if(splitUserInput.length == 1){
            commandModifier = 1;
        } else {
            commandModifier = Integer.parseInt(splitUserInput[1]);
        }
    }

    public abstract void execute();
}
