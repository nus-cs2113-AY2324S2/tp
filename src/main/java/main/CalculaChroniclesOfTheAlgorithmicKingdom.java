package main;

import command.Command;
import map.*;
import parser.Parser;
import textbox.PlayerStatus;
import textbox.TextBox;
import ui.Ui;


public class CalculaChroniclesOfTheAlgorithmicKingdom {
    public static void main(String[] args) {
        new CalculaChroniclesOfTheAlgorithmicKingdom().startGame();
    }

    public void startGame() {
        assert false : "dummy assertion set to fail";
        PlayerStatus playerStatus = new PlayerStatus(100, 0, 0);
        TextBox textBox = new TextBox();
        Parser parser = new Parser();
        AMap map = new FirstMap();
        Ui ui = new Ui();

        map.initMap(30, 10);
        map.initPlayerLocation(0, 0);
        map.placeMonsterInTheMap(2, 3);
        textBox.initTextBox();

        ui.printPlayerStatus(playerStatus);
        ui.printMap(map);
        System.out.println("Type 'h' to get the help menu.");

        Command userCommand;
        while (true) {
            String userCommandText = parser.readInCommand();
            userCommand = parser.parserCommand(userCommandText);
            setUserCommand(userCommand, map, playerStatus, textBox);

            userCommand.execute();

            map = userCommand.getCurrentMap();
            if (!userCommand.getCommandDescription().equals("HelpMe!!")) {
                ui.printPlayerStatus(playerStatus);
                ui.printMap(map);
            }
        }
    }

    private static void setUserCommand(Command userCommand, AMap map, PlayerStatus playerStatus, TextBox textBox) {
        userCommand.setCurrentMap(map);
        userCommand.setPlayerStatus(playerStatus);
        userCommand.setTextBox(textBox);
    }
}
