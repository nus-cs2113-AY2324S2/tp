package ui;
import BattleInterface.BattleInterface;
import map.AMap;
import textbox.PlayerStatus;
import textbox.TextBox;

import java.util.ArrayList;
import java.util.Scanner;
public class Ui {
    private static final int DEFAULT_WIDTH_OF_BATTLE_INTERFACE = 50;
    private static final int DEFAULT_HEIGHT_OF_BATTLE_INTERFACE = 50;
    public String readInCommand(){
        return null;
    }
    public void printDividingLine(){
        System.out.println("===========================================================");
    }

    public void printPlayerStatus(PlayerStatus statusBar){
        printDividingLine();
        System.out.print("HEALTH: " + statusBar.getPlayerHealth() + "      ");
        System.out.print("MONEY: $" + statusBar.getPlayerMoney() + "      ");
        System.out.println("EXP: " + statusBar.getPlayerExp() + "      ");
        printDividingLine();
    }


    public void printTextBox(TextBox box){
        printDividingLine();
        if (box.getNextMessage() != null) {
            System.out.println(box.getNextMessage());
        } else {
            System.out.println(" ");
        }
        printDividingLine();
    }

    public void printMap(AMap map){
        printDividingLine();
        for (ArrayList<Character> row : map.getStoredMap()) {
            for (char cell : row) {
                System.out.print(cell + " ");
            }
            System.out.println();
        }
        printDividingLine();
    }

    public void printBattleInterface(BattleInterface currentInteraction){
        ArrayList<ArrayList<Character>> constructedInterface = new ArrayList<>();

        constructedInterface.add(new ArrayList<>());
        for (int j = 0; j < DEFAULT_WIDTH_OF_BATTLE_INTERFACE; j++) {
            constructedInterface.get(0).add('-');
        }

        for (int i = 1; i < DEFAULT_HEIGHT_OF_BATTLE_INTERFACE - 1; i++) {
            constructedInterface.add(new ArrayList<>());
            constructedInterface.get(i).add('|');
            for (int j = 1; j < DEFAULT_WIDTH_OF_BATTLE_INTERFACE - 1; j++) {
                constructedInterface.get(i).add(' ');
            }
            constructedInterface.get(i).add('|');
        }


        constructedInterface.add(new ArrayList<>());
        for (int j = 0; j < DEFAULT_WIDTH_OF_BATTLE_INTERFACE; j++) {
            constructedInterface.get(DEFAULT_HEIGHT_OF_BATTLE_INTERFACE - 1).add('-');
        }


        for (ArrayList<Character> row : constructedInterface) {
            for (Character c : row) {
                System.out.print(c);
            }
            System.out.println();
        }
    }
}
