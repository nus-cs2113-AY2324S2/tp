package map;

import command.Command;

public abstract class Map {
    public abstract void initMap();
    public abstract void nextMapBasedOnCommand(Command userCommand);
}
