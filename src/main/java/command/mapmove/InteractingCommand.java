package command.mapmove;

import InteractableEntity.*;
import map.*;
import map.BattleInterface.BattleInterface;
import static main.CalculaChroniclesOfTheAlgorithmicKingdom.*;

public class InteractingCommand extends MapMoveCommand {

    public InteractingCommand(){
        commandDescription = "interact";
    }
    @Override
    public void execute() {
        String entityInteractedWith = currentMap.handleInteract();
        textBox.setNextNarration(entityInteractedWith + " appears in your path. What will you do?");
        textBox.setNextInstruction("Will you [fight] or will you [run]?");
        AMap battleMap;
        switch (entityInteractedWith) {
        case "@":
            textBox.setNextDialogue("*the @ symbol stares at you menacingly*");
            InteractableEntity monster = new Enemy(10, 10, 10);
            battleMap = new BattleInterface(playerStatus, textBox, monster);
            battleMap.initMap(30, 10);
            storedMaps.add(battleMap);
            currentOn = 1;
            break;
        default:
            battleMap = new BattleInterface(null, null, null);
            break;
        }

    }
}
