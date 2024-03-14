package parser;

import command.Command;
import command.fight.FightingCommand;

public class Parser {

    public Command parserCommand(String userCommand){
        Command command;
        switch (userCommand) {
        case "fight":
            command = new FightingCommand();
            break;
        default:
            command = null;
        }
        return command;
    }
}
