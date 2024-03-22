package command.fight;
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
    }
}
