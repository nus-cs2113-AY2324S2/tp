package command.fight;

import command.Command;
import map.*;
import map.BattleInterface.BattleInterface;

public class RunningCommand extends Command {
    public RunningCommand() {
        commandDescription = "RUN!";
    }
    @Override
    public void execute(){
        if(currentMap instanceof BattleInterface) {
            textBox.setNextNarration("You decide to run and successfully got away");
        }
    }
}
