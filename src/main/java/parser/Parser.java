package parser;

import command.*;
import command.exception.IllegalCommandException;
import command.fight.FightingCommand;
import command.fight.RunningCommand;
import command.mapmove.*;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {

    boolean isInInteractionScreen;

    public Parser() {
        this.isInInteractionScreen = false;
    }

    public CommandType analyseCommand(String userCommand) {
        Pattern pattern;
        Matcher matcher;
        for (CommandType commandType : CommandType.values()) {
            pattern = Pattern.compile(commandType.getRegExpression());
            matcher = pattern.matcher(userCommand);
            if(matcher.matches()){
                return commandType;
            }
        }
        return CommandType.ERROR;
    }

    public Command fightCommandParser(CommandType commandType, String userCommand) {
        Command command;

        switch (commandType) {
        case FIGHT:
            isInInteractionScreen = false;
            command = new FightingCommand();
            break;
        case RUN:
            isInInteractionScreen = false;
            command = new RunningCommand();
            break;
        case QUIT:
            command = new QuitCommand();
            break;
        case ERROR:
            command = new ErrorCommand(new IllegalCommandException("That's not a valid command.\n\n\nWill you [fight] or will you [run]?"));
            break;
        default:
            command = null;
        }
        return command;
    }

    public Command parseCommand(String userCommand) {
        Command command;
        CommandType commandType = analyseCommand(userCommand);

        if (isInInteractionScreen) {
            return fightCommandParser(commandType, userCommand);
        }

        switch (commandType){
        case FIGHT:
        case RUN:
            command = new ErrorCommand(new IllegalCommandException("You can't do this here\n\n\n"));
            break;
        case MOVE_FORWARD:
            command = new MovingForwardCommand(userCommand);
            break;
        case MOVE_DOWNWARD:
            command = new MovingDownwardCommand(userCommand);
            break;
        case MOVE_LEFT:
            command = new MovingLeftCommand(userCommand);
            break;
        case MOVE_RIGHT:
            command = new MovingRightCommand(userCommand);
            break;
        case QUIT:
            command = new QuitCommand();
            break;
        case INTERACT:
            isInInteractionScreen = true;
            command = new InteractingCommand();
            break;
        case HELP:
            command = new HelpCommand();
            break;
        default:
            command = new ErrorCommand(new IllegalCommandException("That's not a valid command.\n\n\nEnter 'h' or 'help' to find the list of commands."));
        }
        return command;
    }

    public boolean isInInteractionScreen() {
        return isInInteractionScreen;
    }

    public void setInInteractionScreen(boolean inInteractionScreen) {
        this.isInInteractionScreen = inInteractionScreen;
    }

}
