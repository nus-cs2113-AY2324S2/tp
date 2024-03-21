package parser;

import command.*;
import command.fight.FightingCommand;
import command.fight.RunningCommand;
import command.mapmove.*;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {


    public String readInCommand() {
        Scanner in = new Scanner(System.in);
        return in.nextLine();
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

    public Command parseCommand(String userCommand) {
        Command command;

        CommandType commandType = analyseCommand(userCommand);
        switch (commandType){
        case FIGHT:
            command = new FightingCommand();
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
            command = new InteractingCommand();
            break;
        case HELP:
            command = new HelpCommand();
            break;
        case RUN:
            command = new RunningCommand();
            break;
        case ERROR:
            command = new ErrorCommand();
            break;
        default:
            command = null;
        }
        return command;
    }

}
