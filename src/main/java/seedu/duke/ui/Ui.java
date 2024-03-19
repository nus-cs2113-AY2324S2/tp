package seedu.duke.ui;
import java.util.Scanner;

public class Ui {

    private static final Scanner IN = new Scanner(System.in);

    public String readUserInput(){
        return IN.nextLine();
    }

    public void printGreet(){
        print("WELCOME BACK");

    }

    public void printBye(){
        print("See you next time on court.");
    }

    private static void print(String thingToPrint){
        System.out.println("    " + "-----NUSFC24-----");
        System.out.println("    " + thingToPrint);
        System.out.println("    " + "-----------------");
    }
}
