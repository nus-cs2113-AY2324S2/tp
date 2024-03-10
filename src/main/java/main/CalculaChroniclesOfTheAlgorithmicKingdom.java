package main;

import command.Command;
import map.FirstMap;
import parser.Parser;
import textbox.PlayerStatus;
import textbox.TextBox;
import ui.Ui;


public class CalculaChroniclesOfTheAlgorithmicKingdom {
    public static void main(String[] args) {
        new CalculaChroniclesOfTheAlgorithmicKingdom().startGame();
    }

    public void startGame() {
        PlayerStatus playerStatus = new PlayerStatus(100, 0, 0);
        TextBox textBox = new TextBox();
        Parser parser = new Parser();
        FirstMap map = new FirstMap();

        Ui ui = new Ui();
        map.initMap(30, 10);
        map.initPlayerLocation(0,0);
        textBox.initTextBox();
        Command userCommand;
        while (true) {
            String userCommandText = ui.readInCommand();
            userCommand = parser.parserCommand(userCommandText);
            userCommand.execute();

            map.nextMapBasedOnCommand(userCommand);
            ui.printPlayerStatus(playerStatus);
            textBox.nextTextBoxBasedOnMapAndCommand(userCommand, map);
        }
    }
}
