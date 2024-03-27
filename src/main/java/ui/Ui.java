package ui;

import map.BattleInterface.BattleInterface;
import map.AMap;
import textbox.PlayerStatus;
import textbox.TextBox;
import Math.*;
import java.util.ArrayList;

public class Ui {
    private static final int DEFAULT_WIDTH_OF_BATTLE_INTERFACE = 50;
    private static final int DEFAULT_HEIGHT_OF_BATTLE_INTERFACE = 50;

    public void printDividingLine() {
        System.out.println("===========================================================");
    }

    public void printPlayerStatus(PlayerStatus statusBar) {
        printDividingLine();
        System.out.print("HEALTH: " + statusBar.getPlayerHealth() + "      ");
        System.out.print("MONEY: $" + statusBar.getPlayerMoney() + "      ");
        System.out.println("EXP: " + statusBar.getPlayerExp() + "      ");
        printDividingLine();
    }


    public void printTextBox(TextBox box) {
        assert box.getNextDialogue() != null : "next dialogue is null";
        assert box.getNextError() != null : "next error is null";
        assert box.getNextInstruction() != null : "next instruction is null";
        assert box.getNextNarration() != null : "next narration is null";


        printDividingLine();
        if (!box.getNextError().isEmpty()) {
            System.out.println(box.getNextError());
        }
        if (!box.getNextNarration().isEmpty()) {
            System.out.println(box.getNextNarration());
            System.out.println("\n");
        }
        if (!box.getNextDialogue().isEmpty()) {
            System.out.println(box.getNextDialogue());
        }
        if (!box.getNextInstruction().isEmpty()) {
            System.out.println(box.getNextInstruction());
        }
        printDividingLine();
        box.clearAll();
    }

    public void printTextbox(String message){ //for custom messages
        printDividingLine();
        System.out.println(message);
        printDividingLine();
    }
    public void printMap(ArrayList<ArrayList<Character>> map) {
        printDividingLine();
        for (ArrayList<Character> row : map) {
            for (char cell : row) {
                System.out.print(cell);
            }
            System.out.println();
        }
        printDividingLine();
    }
    public void printMap(AMap map) {
        printDividingLine();
        for (ArrayList<Character> row : map.getCurrentMap()) {
            for (char cell : row) {
                System.out.print(cell + " ");
            }
            System.out.println();
        }
        printDividingLine();
    }
    public void printEnemy(AMap map) {
        printDividingLine();
        for (ArrayList<Character> row : map.getCurrentMap()) {
            for (char cell : row) {
                System.out.print(cell);
            }
            System.out.println();
        }
        printDividingLine();
    }
    public void printHelpMenu() {
        printDividingLine();
        System.out.println("'w' 'a' 's' 'd' to move around");
        System.out.println("'e' to interact");
        System.out.println("'q' to quit");
        System.out.println("'h' to print help menu");
        System.out.println("'run' to escape the battle interface");
        printDividingLine();
    }
    public void printQuestion(MathQuestion mathQuestion){
        System.out.println(mathQuestion.getQuestion());
    }


    public void printDeathMessage(){
        System.out.println("|\\     /|(  ___  )|\\     /|  (  __  \\ \\__   __/(  ____ \\(  __  \\ ");
        System.out.println("( \\   / )| (   ) || )   ( |  | (  \\  )   ) (   | (    \\/| (  \\  )");
        System.out.println(" \\ (_) / | |   | || |   | |  | |   ) |   | |   | (__    | |   ) |");
        System.out.println("  \\   /  | |   | || |   | |  | |   | |   | |   |  __)   | |   | |");
        System.out.println("   ) (   | |   | || |   | |  | |   ) |   | |   | (      | |   ) |");
        System.out.println("   | |   | (___) || (___) |  | (__/  )___) (___| (____/\\| (__/  )");
        System.out.println("   \\_/   (_______)(_______)  (______/ \\_______/(_______/(______/ ");
    }
}
