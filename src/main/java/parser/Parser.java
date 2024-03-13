package parser;

import financialtransactions.*;
import java.util.ArrayList;

public class Parser {
    protected static boolean isContinue = true;

    public static void parseCommand(String command, TransactionManager manager) {
        String[] commandParts = command.split(" ");
        String action = commandParts[0];

        switch (action) {
        case "login":
            // Implement login functionality
            break;
        case "add-source":
            // Implement add source of income functionality
            break;
        case "delete-source":
            // Implement delete source of income functionality
            break;
        case "add-transaction":
            // Implement add transaction functionality
            break;
        case "delete-transaction":
            // Implement delete transaction functionality
            break;
        case "view-history":
            break;
        case "export-csv":
            // Implement export to CSV functionality
            break;
        case "help":
            // Implement help functionality
            break;
        default:
            System.out.println("Invalid command");
            break;
        }
    }
}

