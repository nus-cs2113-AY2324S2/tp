package command.fight;
import command.Command;

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
    }
}
