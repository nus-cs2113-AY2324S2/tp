package command.mapmove;

import InteractableEntity.*;
import map.*;
import map.BattleInterface.BattleInterface;

public class InteractingCommand extends MapMoveCommand {
    @Override
    public void execute() {
        String entityInteractedWith = currentMap.handleInteract();
        System.out.println(entityInteractedWith);
        AMap battleMap;
        switch (entityInteractedWith) {
        case "@":
            InteractableEntity monster = new Enemy(10, 10);
            battleMap = new BattleInterface(playerStatus, textBox, monster);
            battleMap.initMap(30, 10);
            currentMap = battleMap;
            break;
        default:
            battleMap = new BattleInterface(null, null, null);
            break;
        }

    }
}
