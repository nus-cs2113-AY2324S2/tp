package longah;

import java.util.Scanner;
import java.util.ArrayList;

import longah.util.MemberList;
import longah.util.TransactionList;
import longah.util.Subtransaction;
import longah.exception.LongAhException;
import longah.exception.ExceptionMessage;

/**
 * LongAh class manages debts between members.
 */
public class LongAh {
    private static MemberList members = new MemberList();
    private static TransactionList transactions = new TransactionList();
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
        System.out.println("Welcome to LongAh!");
        LongAh app = new LongAh();
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
                    transactions.add(parts[1], members);
                    break;
                case "listdebts":
                    app.listAllDebts();
                    break;
                case "listtransactions":
                    System.out.println(transactions.listTransactions());
                    break;
                case "delete":
                    transactions.remove(parts);
                    break;
                case "findpayment":
                    System.out.println(transactions.findTransactions(parts));
                    break;
                case "finddebt":
                    System.out.println(transactions.findDebts(parts));
                    break;
                case "clear":
                    transactions.clear();
                    break;
                case "addmember":
                    if (parts.length == 2) {
                        String name = parts[1];
                        members.addMember(name);
                    } else {
                        System.out.println("Invalid command format. Use 'addmember NAME'");
                    }
                    break;
                case "listmembers":
                    members.listMembers();
                    break;
                case "settleup":
                    transactions.settleUp(parts, members);
                    break;
                case "exit":
                    System.exit(0);
                    return;
                default:
                    System.out.println("Invalid command. Use 'add', 'listdebts', 'listtransactions'," +
                            " 'delete', 'findpayment', 'finddebt', 'clear', or 'addmember'" +
                            ", 'exit'.");
                }
            } catch (LongAhException e) {
                LongAhException.printException(e);
            }
        }
    }
}
