package ui;
import map.Map;
import textbox.PlayerStatus;
import textbox.TextBox;

import java.util.ArrayList;
import java.util.Scanner;
public class Ui {
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

    public void printMap(Map map){
        printDividingLine();
        for (ArrayList<Character> row : map.getStoredMap()) {
            for (char cell : row) {
                System.out.print(cell + " ");
            }
            System.out.println();
        }
        printDividingLine();
    }
}
