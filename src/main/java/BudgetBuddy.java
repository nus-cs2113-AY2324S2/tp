import java.util.Scanner;

public class BudgetBuddy {

    /**
     * Main entry-point for the java.BudgetBuddy application.
     */

    public static void main(String[] args){
        String logo = "▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄\n" +
                      "██ ▄▄▀█ ██ █ ▄▀█ ▄▄▄█ ▄▄█▄ ▄████ ▄▄▀█ ██ █ ▄▀█ ▄▀█ ██ █\n" +
                      "██ ▄▄▀█ ██ █ █ █ █▄▀█ ▄▄██ █████ ▄▄▀█ ██ █ █ █ █ █ ▀▀ █\n" +
                      "██ ▀▀ ██▄▄▄█▄▄██▄▄▄▄█▄▄▄██▄█████ ▀▀ ██▄▄▄█▄▄██▄▄██▀▀▀▄█\n" +
                      "▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("What is your name?");

        Scanner in = new Scanner(System.in);
        System.out.println("Hello " + in.nextLine());
    }
}
