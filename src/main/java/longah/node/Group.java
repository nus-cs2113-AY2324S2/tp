package longah.node;

import java.util.ArrayList;

import java.util.logging.Logger;
import java.util.logging.Level;

import longah.util.MemberList;
import longah.util.Subtransaction;
import longah.util.TransactionList;
import longah.handler.StorageHandler;
import longah.exception.LongAhException;
import longah.exception.ExceptionMessage;

public class Group {
    private static Logger logger = Logger.getLogger("Group Logger");

    private MemberList members;
    private TransactionList transactions;
    private StorageHandler storage;
    private ArrayList<Subtransaction> transactionSolution = new ArrayList<>();

    /**
     * Constructs a new Group instance with an empty member list and transaction list.
     */
    public Group() throws LongAhException {
        this.members = new MemberList();
        this.transactions = new TransactionList();
        this.storage = new StorageHandler(this.members, this.transactions);
        updateTransactionSolution();
    }

    /**
     * Sets the member list of the group.
     * 
     * @param members The member list to be set
     */
    public void setMemberList(MemberList members) {
        this.members = members;
    }

    /**
     * Returns the member list of the group.
     * 
     * @return The member list of the group
     */
    public MemberList getMemberList() {
        return this.members;
    }

    /**
     * Sets the transaction list of the group.
     * 
     * @param transactions The transaction list to be set
     */
    public void setTransactionList(TransactionList transactions) {
        this.transactions = transactions;
    }

    /**
     * Returns the transaction list of the group.
     * 
     * @return The transaction list of the group
     */
    public TransactionList getTransactionList() {
        return this.transactions;
    }

    /**
     * Update the transaction solution of the group based on the debts and credits of the members.
     * 
     * @throws LongAhException If the transaction solution cannot be updated
     */
    public void updateTransactionSolution() throws LongAhException {
        this.transactionSolution = this.members.solveTransactions();
        logger.log(Level.INFO, "Transaction solution updated");
    }

    /**
     * Settles up the debts of the specified borrower by creating a transaction to repay all debts owed.
     *
     * @param borrowerName The name of the borrower to settle up.
     */
    public void settleUp(String borrowerName) throws LongAhException {
        Member borrower = this.members.getMember(borrowerName);
        if (borrower.getBalance() == 0) {
            throw new LongAhException(ExceptionMessage.NO_DEBTS_FOUND);
        }

        String transactionExpression = borrowerName;
        for (Subtransaction subtransaction : this.transactionSolution) {
            Member subBorrower = subtransaction.getBorrower();
            if (borrower == subBorrower) {
                Member lender = subtransaction.getLender();
                double amountRepaid = subtransaction.getAmount();
                transactionExpression += " p/" + lender.getName() + " a/" + amountRepaid;
                System.out.println(borrowerName + " has repaid " + lender.getName() + " $" + amountRepaid);
            }
        }

        this.transactions.addTransaction(transactionExpression, this.members);
        updateTransactionSolution();
        assert this.members.getMember(borrowerName).getBalance() == 0 : "Borrower should have no more debts.";
        System.out.println(borrowerName + " has no more debts!");
    }

    /**
     * Saves the member data into the storage file.
     */
    public void saveMembersData() throws LongAhException {
        this.storage.saveMembersData(this.members);
    }

    /**
     * Saves the transaction data into the storage file.
     */
    public void saveTransactionsData() throws LongAhException {
        this.storage.saveTransactionsData(this.transactions);
    }

    /**
     * Saves the data from the member list and transaction list into storage file.
     * 
     * @throws LongAhException If the data file is not read or the content is invalid
     */
    public void saveAllData() throws LongAhException {
        saveMembersData();
        saveTransactionsData();
    }

    /**
     * Returns the solution to the debts in the group.
     * 
     * @return The solution to the debts in the group
     * @throws LongAhException If there are no debts to be solved
     */
    public String listDebts() throws LongAhException {
        if (this.transactionSolution.size() == 0) {
            throw new LongAhException(ExceptionMessage.TRANSACTIONS_SUMMED_UP);
        }

        String solution = "Best Way to Solve Debts:\n";
        for (Subtransaction subtransaction : this.transactionSolution) {
            solution += subtransaction.toString() + "\n";
        }
        return solution;
    }

    public String listIndivDebt(String name) throws LongAhException {
        double balance = members.getMemberBalance(name);
        if (balance == 0) {
            throw new LongAhException(ExceptionMessage.TRANSACTIONS_SUMMED_UP);
        }

        String output = "";
        for (Subtransaction subtransaction : this.transactionSolution) {
            if (subtransaction.isInvolved(name)) {
                output += subtransaction.toString() + "\n";
            }
        }
        return output;
    }
}
