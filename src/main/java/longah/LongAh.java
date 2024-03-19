package longah;

import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.logging.*;

import longah.node.Group;
import longah.util.MemberList;
import longah.util.TransactionList;
import longah.util.Subtransaction;
import longah.exception.LongAhException;
import longah.exception.ExceptionMessage;

/**
 * LongAh class manages debts between members.
 */
public class LongAh {
    private static final Logger LongAhLogger = Logger.getLogger("LongAh");
    private static MemberList members;
    private static TransactionList transactions;
    private static Group group;
    private Scanner scanner;

    /**
     * Constructs a new LongAh instance.
     */
    public LongAh() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Lists all debts between members.
     */
    public void listAllDebts() throws LongAhException {
        if (members.getMemberListSize() == 0) {
            throw new LongAhException(ExceptionMessage.NO_MEMBERS_FOUND);
        }
        ArrayList<Subtransaction> subtransactions = members.solveTransactions();

        System.out.println("Best Way to Solve Debts:");
        for (Subtransaction subtransaction : subtransactions) {
            System.out.println(subtransaction);
        }
    }

    /**
     * The main method to run the LongAh application.
     *
     * @param args The command-line arguments.
     */
    public static void main(String[] args) {
        try {
            FileHandler handler = new FileHandler("./log/LongAh.log");
            handler.setFormatter(new SimpleFormatter());
            LongAhLogger.addHandler(handler);
            LongAhLogger.setUseParentHandlers(false);
        } catch (IOException e) {
            System.out.println("Log file initiation failed.");
        }

        LongAhLogger.log(Level.INFO, "Starting Pre-program preparations.");
        System.out.println("Welcome to LongAh!");
        LongAh app = new LongAh();
        try {
            LongAhLogger.log(Level.INFO, "Loading previous member and transaction info.");
            group = new Group();
            members = group.getMemberList();
            transactions = group.getTransactionList();
        } catch (LongAhException e) {
            LongAhLogger.log(Level.WARNING, "Loading process fails! Unable to create file or " +
                    "file could not be access.");
            LongAhException.printException(e);
        }

        LongAhLogger.log(Level.INFO, "Entering main program body. Begin accepting user commands.");
        while (true) {
            try {
                System.out.print("Enter command: ");
                if (!app.scanner.hasNextLine()) {
                    return;
                }
                String command = app.scanner.nextLine();
                String[] parts = command.split(" ", 2);
                switch (parts[0]) {
                case "add":
                    LongAhLogger.log(Level.INFO, "User requests to add in a transaction.");
                    transactions.addTransaction(parts[1], members);
                    group.updateTransactionSolution();
                    group.saveAllData();
                    break;
                case "listdebts":
                    LongAhLogger.log(Level.INFO, "User requests to list all debts.");
                    app.listAllDebts();
                    break;
                case "listtransactions":
                    LongAhLogger.log(Level.INFO, "User requests to list all transactions.");
                    System.out.println(transactions.listTransactions());
                    break;
                case "delete":
                    LongAhLogger.log(Level.INFO, "User requests to remove a transactions.");
                    transactions.remove(parts);
                    break;
                case "findtransaction":
                    LongAhLogger.log(Level.INFO, "User requests to find all transactions under a member.");
                    System.out.println(transactions.findTransactions(parts));
                    break;
                case "finddebt":
                    LongAhLogger.log(Level.INFO, "User requests to find all debts for a member.");
                    System.out.println(transactions.findDebts(parts));
                    break;
                case "clear":
                    LongAhLogger.log(Level.INFO, "User requests to clear all existing transactions.");
                    transactions.clear();
                    break;
                case "addmember":
                    LongAhLogger.log(Level.INFO, "User requests to add a member.");
                    if (parts.length == 2) {
                        String name = parts[1];
                        members.addMember(name);
                        group.saveMembersData();
                    } else {
                        System.out.println("Invalid command format. Use 'addmember NAME'");
                    }
                    break;
                case "listmembers":
                    LongAhLogger.log(Level.INFO, "User requests to list all existing members.");
                    members.listMembers();
                    break;
                case "settleup":
                    LongAhLogger.log(Level.INFO, "User requests save all current running data.");
                    group.settleUp(parts[1]);
                    group.saveAllData();
                    break;
                case "exit":
                    LongAhLogger.log(Level.INFO, "Exit prompt received. Exiting program.");
                    System.exit(0);
                    return;
                default:
                    System.out.println("Invalid command. Use 'add', 'listdebts', 'listtransactions'," +
                            " 'delete', 'findpayment', 'finddebt', 'clear', or 'addmember'" +
                            ", 'exit'.");
                }
            } catch (LongAhException e) {
                LongAhLogger.log(Level.WARNING, "The previous user command caused an error. Check the returned " +
                        "error message for details");
                LongAhException.printException(e);
            }
        }
    }
}
