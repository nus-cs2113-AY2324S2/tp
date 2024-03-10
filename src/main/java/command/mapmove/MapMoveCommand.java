package command.mapmove;

import command.Command;

public abstract class MapMoveCommand extends Command {
    protected String commandModifier;
    public abstract void execute();
}
