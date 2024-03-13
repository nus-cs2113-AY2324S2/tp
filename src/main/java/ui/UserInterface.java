package ui;

public class UserInterface {

    public static void printDeleteMessage(String transaction){
        System.out.println("Got it. I have removed the following transaction from the history");
        System.out.println(transaction);
    }

    public static void printIndexOutOfBounds(String message){
        System.out.println(message + ". Check you input properly");
    }
}
