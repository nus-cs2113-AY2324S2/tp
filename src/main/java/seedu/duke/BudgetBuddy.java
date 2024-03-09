package seedu.duke;

import java.util.Scanner;

public class BudgetBuddy {

    private Ui ui;

    public BudgetBuddy() {
        ui = new Ui();
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        ui.showWelcome();

        ui.showGoodbye();
        scanner.close();
    }

    public static void main(String[] args) {
        new BudgetBuddy().run();
    }
}
