package main;

import command.Command;
import command.fight.FightingCommand;
import command.mapmove.MapMoveCommand;
import map.*;
import parser.Parser;
import textbox.PlayerStatus;
import textbox.TextBox;
import ui.Ui;
import Math.*;

import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;


public class CalculaChroniclesOfTheAlgorithmicKingdom {
    public static int currentOn;
    public static ArrayList<AMap> storedMaps = new ArrayList<>();
    public static void main(String[] args) {
        new CalculaChroniclesOfTheAlgorithmicKingdom().startGame();
    }

    public void startGame() {
        Scanner in  = new Scanner(System.in);

        PlayerStatus playerStatus = new PlayerStatus(100, 0, 0);
        TextBox textBox = new TextBox();
        Parser parser = new Parser();
        AMap map = new FirstMap();
        Ui ui = new Ui();

        map.initMap(30, 10);
        map.initPlayerLocation(0, 0);
        map.placeMonsterInTheMap(2, 3);
        textBox.initTextBox();
        currentOn = 0;
        storedMaps.add(map);
        assert storedMaps.size() == 1;

        ui.printPlayerStatus(playerStatus);
        ui.printMap(storedMaps.get(currentOn));
        System.out.println("Type 'h' to get the help menu.");

        Command userCommand;
        while (true) {
            String userCommandText = in.nextLine();

            userCommand = parser.parseCommand(userCommandText);
            setUserCommand(userCommand, storedMaps.get(currentOn), playerStatus, textBox);

            if (!(storedMaps.get(currentOn) instanceof FirstMap) && userCommand instanceof MapMoveCommand) {
                System.out.println("Invalid Command");
            } else if (userCommand.getCommandDescription().equals("FIGHT!")){
                userCommand.execute(in);
            } else {
                userCommand.execute();
            }

            if (!userCommand.getCommandDescription().equals("HelpMe!!")) {
                ui.printPlayerStatus(playerStatus);
                ui.printMap(storedMaps.get(currentOn));
            }

        }
    }

    private static void setUserCommand(Command userCommand, AMap map, PlayerStatus playerStatus, TextBox textBox) {
        userCommand.setCurrentMap(map);
        userCommand.setPlayerStatus(playerStatus);
        userCommand.setTextBox(textBox);
    }
}
