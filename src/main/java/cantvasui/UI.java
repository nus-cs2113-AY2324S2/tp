package cantvasui;

import java.util.Scanner;

public class UI {

    /*   static final String divider = "------------------------------------------------";

        private Scanner UserInput;

        public UI(){
            this.UserInput = new Scanner(System.in);
        };

        public static void showErrorMsg(){
            System.out.println("Sorry I think you have input an invalid command.");
            System.out.println("Please check again on your input :D");
      }
    */

    private final Scanner in;

    public UI() {
        this.in = new Scanner(System.in);
    }

    public String getUserCommand() {
        return in.nextLine();
    }

    public static void printLogo() {
        System.out.println("   _____            ___      __\n"
                + "  / ____|          | \\ \\    / /\n"
                + " | |     __ _ _ __ | |\\ \\  / /_ _ ___\n"
                + " | |    / _` | '_ \\| __\\ \\/ / _` / __|\n"
                + " | |___| (_| | | | | |_ \\  / (_| \\__ \\\n"
                + "  \\_____\\__,_|_| |_|\\__| \\/ \\__,_|___/\n"
        );
    }

    public static void printHelpMessage() {
        System.out.println("CantVas Help\n"
                + "To input expenses, use format:"
                + "\n << e/ add/  d/ <description> amt/ <cost> date/ <dd.mm.yyyy> >>\n"
                + "To List saved expenses, use format:\n << list >>");
    }

}
