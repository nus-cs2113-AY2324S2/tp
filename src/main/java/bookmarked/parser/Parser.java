package bookmarked.parser;

import bookmarked.Book;
import bookmarked.command.ExitCommand;
import bookmarked.command.FindCommand;
import bookmarked.ui.Ui;
import bookmarked.command.Command;
import bookmarked.command.ReturnCommand;
import bookmarked.command.AddCommand;
import bookmarked.command.DeleteCommand;
import bookmarked.command.BorrowCommand;
import bookmarked.command.HelpCommand;
import bookmarked.command.ListCommand;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class Parser {
    public static void runCommand(String newItem, Scanner in, ArrayList<Book> listOfBooks, File bookDataFile) {
        Command userCommand = new ListCommand(listOfBooks);

        while (!newItem.equalsIgnoreCase("bye")) {
            String[] splitItem = newItem.split(" ");
            parseCommand(newItem, in, listOfBooks, bookDataFile, splitItem);
            Ui.separateNextInput();
            newItem = in.nextLine();
        }
        userCommand = new ExitCommand();
        userCommand.handleCommand();
    }


    public static void parseCommand(String newItem, Scanner in, ArrayList<Book> listOfBooks,
                                     File bookDataFile, String[] splitItem) {
        Command userCommand = new ListCommand(listOfBooks);
        switch(splitItem[0]) {
        case ("/help"):
            userCommand = new HelpCommand();
            break;
        case ("list"):
            userCommand = new ListCommand(listOfBooks);
            break;
        case ("add"):
            userCommand = new AddCommand(newItem, listOfBooks, splitItem, bookDataFile);
            break;
        case ("delete"):
            userCommand = new DeleteCommand(splitItem, listOfBooks, bookDataFile);
            break;
        case ("borrow"):
            userCommand = new BorrowCommand(splitItem, listOfBooks, bookDataFile);
            break;
        case ("return"):
            userCommand = new ReturnCommand(splitItem, listOfBooks, bookDataFile);
            break;
        case ("find"):
            userCommand = new FindCommand(newItem, listOfBooks);
            break;
        default:
            Ui.printUnknownCommand();
            Ui.separateNextInput();
            newItem = in.nextLine();
            break;
        }
        userCommand.handleCommand();
    }


}


