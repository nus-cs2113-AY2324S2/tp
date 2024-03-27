package command.mapmove;

import InteractableEntity.*;
import InteractableEntity.enemies.*;
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
        case "@": //centaur
            int x_pos = currentMap.getInteractX();
            int y_pos = currentMap.getInteractY();
            InteractableEntity monster = new Centaur(10, 10, 10, x_pos, y_pos, 10, 10);
            textBox.setNextDialogue("*the " + monster.getName() + " stares at you menacingly*");
            battleMap = new BattleInterface(playerStatus, textBox, monster);
            battleMap.initMap(30, monster.getHeight());
            storedMaps.add(battleMap);
            currentOn = 1;
            break;
        default:
            battleMap = new BattleInterface(null, null, null);
            break;
        }

    }
}
