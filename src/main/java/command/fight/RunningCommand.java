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
            AMap initMap = new FirstMap();
            initMap.initMap(30, 10);
            initMap.initPlayerLocation(0, 0);
            initMap.placeMonsterInTheMap(2, 3);
            currentMap = initMap;
        }
    }
}
