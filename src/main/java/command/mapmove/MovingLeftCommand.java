package command.mapmove;

public class MovingLeftCommand extends MapMoveCommand {
    @Override
    public void execute() {
        currentMap.movePlayerLeftOne();

    }
}
