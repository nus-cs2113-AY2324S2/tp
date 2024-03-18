package parser;

import command.Command;
import command.HelpCommand;
import command.QuitCommand;
import command.fight.FightingCommand;
import command.fight.RunningCommand;
import command.mapmove.*;

import java.util.Scanner;

public class Parser {
    public String readInCommand() {
        Scanner in = new Scanner(System.in);
        return in.nextLine();
    }

    public Command parserCommand(String userCommand) {
        Command command;
        userCommand = userCommand.trim();
        if (userCommand.equalsIgnoreCase("fight")) {
            command = new FightingCommand();
        } else if (userCommand.toLowerCase().startsWith("w")) {
            command = new MovingForwardCommand();
        } else if (userCommand.toLowerCase().startsWith("a")) {
            command = new MovingLeftCommand();
        } else if (userCommand.toLowerCase().startsWith("s")) {
            command = new MovingDownwardCommand();
        } else if (userCommand.toLowerCase().startsWith("d")) {
            command = new MovingRightCommand();
        } else if (userCommand.toLowerCase().startsWith("e")) {
            command = new InteractingCommand();
        } else if (userCommand.equalsIgnoreCase("q")) {
            command = new QuitCommand();
        } else if (userCommand.equalsIgnoreCase("h")) {
            command = new HelpCommand();
        } else if (userCommand.equalsIgnoreCase("run")) {
            command = new RunningCommand();
        } else {
            command = null;
        }
        return command;
    }
}
