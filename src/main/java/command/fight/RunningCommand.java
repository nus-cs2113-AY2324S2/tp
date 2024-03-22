package command.fight;

import command.Command;
import map.*;
import map.BattleInterface.BattleInterface;

import static main.CalculaChroniclesOfTheAlgorithmicKingdom.currentOn;
import static main.CalculaChroniclesOfTheAlgorithmicKingdom.storedMaps;

public class RunningCommand extends Command {
    public RunningCommand() {
        commandDescription = "RUN!";
    }
    @Override
    public void execute(){
        if(currentMap instanceof BattleInterface) {
            textBox.setNextNarration("You decide to run and successfully got away");
            currentOn = 0;
            currentMap = storedMaps.get(currentOn);
        }
    }
}
