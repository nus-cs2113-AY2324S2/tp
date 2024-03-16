package command.mapmove;


public class MovingDownwardCommand extends MapMoveCommand {

    @Override
    public void execute() {
        currentMap.movePlayerDownOne();

    }
}
