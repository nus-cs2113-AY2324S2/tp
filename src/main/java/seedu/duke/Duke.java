package seedu.duke;

import java.util.Scanner;

public class Duke {

    private Ui ui = new Ui();
    public static void main(String[] args) {
        new Duke().runBot();
        boolean userSaysBye = false;
        TravelActivityList list = new TravelActivityList();
        String line;
        Scanner in = new Scanner(System.in);
        while (!userSaysBye) {
            line = in.nextLine();
            String[] command = line.split(" ");

            switch (command[0].toLowerCase()) {
            case "list":
                Ui.printLine();
                Parser.getList(list);
                Ui.printLine();
                break;
            case "add":
                try {
                    Ui.printLine();
                    Parser.addCommand(line, command, list);
                    Ui.printLine();
                }
                catch (OmniException exception){
                    Ui.printLine();
                    System.out.println("Warning! " + exception.getMessage());
                }
                break;
            case "delete":
                try {
                    Ui.printLine();
                    Parser.deleteCommand(command, list);
                    Ui.printLine();
                }
                catch (OmniException exception){
                    Ui.printLine();
                    System.out.println("Warning! " + exception.getMessage());
                }
                break;
            case "find":
                try {
                    Ui.printLine();
                    Parser.findCommand(command, list);
                    Ui.printLine();
                }
                catch (OmniException exception){
                    Ui.printLine();
                    System.out.println("Warning! " + exception.getMessage());
                }
                break;
            case "bye":
                Ui.printLine();
                System.out.println("Hope to see you again soon. Bye!");
                Ui.printLine();
                userSaysBye = true;
                break;
            default:
                Ui.printLine();
                System.out.println("This is not a valid command");
                Ui.printLine();

            }
        }
    }
    public void runBot(){
        ui.printGreeting();
    }


}



