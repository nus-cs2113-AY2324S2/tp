package command;

import exceptions.CommandFormatException;

public abstract class Command  {

    public abstract void execute() throws CommandFormatException;
}
