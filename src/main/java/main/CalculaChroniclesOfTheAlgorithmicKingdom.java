package main;

import command.Command;
import map.DemoMap;
import map.Map;
import parser.Parser;
import textbox.PlayerStatus;
import textbox.TextBox;
import ui.Ui;

public class CalculaChroniclesOfTheAlgorithmicKingdom {
    public static void main(String[] args) {
        new CalculaChroniclesOfTheAlgorithmicKingdom().startGame();
    }

    public void startGame() {
        PlayerStatus playerStatus = new PlayerStatus();
        TextBox textBox = new TextBox();
        Parser parser = new Parser();
        Map map = new DemoMap();

        Ui ui = new Ui();
        map.initMap();
        textBox.initTextBox();

        Command userCommand;
        while (true) {
            String userCommandText = ui.readInCommand();
            userCommand = parser.parserCommand(userCommandText);
            userCommand.execute();

            map.nextMapBasedOnCommand(userCommand);
            playerStatus.showPlayerStatus();
            textBox.nextTextBoxBasedOnMapAndCommand(userCommand, map);
        }
    }
}
