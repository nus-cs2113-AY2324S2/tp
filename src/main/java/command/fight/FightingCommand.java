package command.fight;
import InteractableEntity.Enemy;
import command.Command;
import static main.CalculaChroniclesOfTheAlgorithmicKingdom.*;
import java.util.Scanner;


public class FightingCommand extends Command {
    public FightingCommand() {
        commandDescription = "FIGHT!";
    }

    @Override
    public void execute() {
    }

    @Override
    public void execute(Scanner in) {
        currentMap.fightLoop(in);
        currentOn = 0;
        if (currentMap.getEntityDeath()){
            int x_pos = storedMaps.get(currentOn).getInteractX();
            int y_pos = storedMaps.get(currentOn).getInteractY();
            storedMaps.get(currentOn).clearSpot(x_pos, y_pos);
            currentMap.handleLootingByPlayer();
        }
        else if (currentMap.getPlayerDeath()){
            currentMap.handleDeath();
        }
    }
}
