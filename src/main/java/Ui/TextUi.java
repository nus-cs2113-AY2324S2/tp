package Ui;

import Storage.Storage;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class TextUi {

    public static final String DIVIDER = "----------------";

    public static final String WELCOME_MESSAGE = "Welcome to StockMaster, where you can master the knowledge on your " +
            "Stock!";

    public static final String GOODBYE_MESSAGE = "Thank you for using StockMaster, hope we have helped your lazy ass!";
    private final Scanner in;

    public TextUi() {
        this.in = new Scanner(System.in);
    }

    public String getUserCommand() {
        System.out.println("Enter Command: ");
        Scanner in = new Scanner(System.in);
        return in.nextLine();
    }

    public void showWelcomeMessage(String version, String StorageFilePath) {
        replyToUser(
                DIVIDER,
                version,
                DIVIDER,
                "Data is being extracted from: " + StorageFilePath,
                WELCOME_MESSAGE
        );
    }

    public void showGoodByeMessage(String StorageFilePath) {
        replyToUser(
                DIVIDER,
                "Data is being saved to :" + StorageFilePath,
                DIVIDER,
                GOODBYE_MESSAGE
        );
    }

    public void replyToUser(String...message) {
        for (String m : message) {
            System.out.println(m + "\n");
        }
    }
}


