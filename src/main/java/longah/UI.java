package longah;

import java.util.Scanner;

public class UI {
    private Scanner scanner;

    public UI() {
        this.scanner = new Scanner(System.in);
    }

    public String getUserInput() {
        return scanner.nextLine().trim();
    }

    public void showWelcomeMessage() {
        System.out.println("Welcome to LongAh!");
    }

    public void showCommandPrompt() {
        System.out.println("Enter command:");
    }

    public void showInvalidCommandMessage() {
        System.out.println("Invalid command. Use 'add', 'list', 'delete', 'find', 'clear', or 'exit'.");
    }

    public void showInvalidFormatMessage() {
        System.out.println("Invalid command format.");
    }

    public void showMessage(String message) {
        System.out.println(message);
    }

    public void showDebts(String debts) {
        System.out.println(debts);
    }

    public void closeScanner() {
        scanner.close();
    }
}
