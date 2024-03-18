package bookmarked.parser;

import bookmarked.Book;
import bookmarked.command.ExitCommand;
import bookmarked.ui.Ui;
import bookmarked.command.Command;
import bookmarked.command.ReturnCommand;
import bookmarked.command.AddCommand;
import bookmarked.command.DeleteCommand;
import bookmarked.command.BorrowCommand;
import bookmarked.command.HelpCommand;
import bookmarked.command.ListCommand;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Parser {
    public static void runCommand(String newItem, Scanner in, ArrayList<Book> listOfBooks) {
        Command userCommand = new ListCommand(listOfBooks);
        while (!newItem.equalsIgnoreCase("bye")) {
            String[] splitItem = newItem.split(" ");
            if (splitItem[0].matches("/help")) {
                userCommand = new HelpCommand();
            } else if (splitItem[0].matches("list")) {
                userCommand = new ListCommand(listOfBooks);
            } else if (splitItem[0].matches("add")) {
                userCommand = new AddCommand(newItem, listOfBooks, splitItem);
            } else if (splitItem[0].matches("delete")) {
                userCommand = new DeleteCommand(splitItem, listOfBooks);
            } else if (splitItem[0].matches("borrow")) {
                userCommand = new BorrowCommand(splitItem, listOfBooks);
            } else if (splitItem[0].matches("return")) {
                userCommand = new ReturnCommand(splitItem, listOfBooks);
            }
            userCommand.handleCommand();
            Ui.separateNextInput();
            newItem = in.nextLine();
        }
        userCommand = new ExitCommand();
        userCommand.handleCommand();
    }
}
