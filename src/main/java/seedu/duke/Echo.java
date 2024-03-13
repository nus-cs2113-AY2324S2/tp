package seedu.duke;

import java.util.ArrayList;
import java.util.Scanner;
public class Echo {

private ArrayList<String> inputs;

public Echo() {
    inputs = new ArrayList<>();
}

    public void startEcho() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter text to echo back, or type 'exit' to quit:");

        while (true) {
            String input = scanner.nextLine();
            if ("exit".equalsIgnoreCase(input)) {
                break;
            }
            inputs.add(input);
            echoBack();
        }
    }

    private void echoBack() {
        for (String input : inputs) {
            System.out.println(input);
        }
    }

    public static void main(String[] args) {
        Echo echo = new Echo();
        echo.startEcho();
    }
}
