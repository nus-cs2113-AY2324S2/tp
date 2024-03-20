package seedu.duke;

import java.util.NoSuchElementException;
import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {
        Ui.printGreeting();
        boolean userSaysBye = false;
        TravelActivityList list = new TravelActivityList();
        String line;
        Scanner in = new Scanner(System.in);
        while (!userSaysBye) {
            try {
                line = in.nextLine();
                assert line != null :"Input does not exist!";
                String[] command = line.split(" ");
                switch (command[0].toLowerCase()) {
                case "list":
                    Ui.printLine();
                    Parser.getList(list);
                    Ui.printLine();
                    break;

                case "add":
                    Ui.printLine();
                    Parser.addCommand(line, command, list);
                    Ui.printLine();
                    break;

                case "delete":
                    Ui.printLine();
                    Parser.deleteCommand(command, list);
                    Ui.printLine();
                    break;

                case "check":
                    Ui.printLine();
                    Parser.checkCommand(command, list);
                    Ui.printLine();
                    break;

                case "uncheck":
                    Ui.printLine();
                    Parser.uncheckCommand(command, list);
                    Ui.printLine();
                    break;

                case "find":
                    Ui.printLine();
                    Parser.findCommand(command, list);
                    Ui.printLine();
                    break;

                case "tag":
                    Ui.printLine();
                    Parser.tagCommand(command, list);
                    Ui.printLine();
                    break;

                case "untag":
                    Ui.printLine();
                    Parser.removeTagCommand(command, list);
                    Ui.printLine();
                    break;


                case "help":
                    Ui.printLine();
                    Ui.helpCommand();
                    Ui.printLine();
                    break;

                case "bye":
                    Ui.printBye();
                    userSaysBye = true;
                    break;

                default:
                    Ui.printLine();
                    System.out.println("This is not a valid command");
                    Ui.printLine();
                }
            } catch (OmniException exception){
                Ui.printException(exception);
            } catch (NoSuchElementException exception){
                Ui.printNoSuchElementException(exception);
            }
        }
    }



}



