package longah.node;

import java.util.ArrayList;

import longah.util.MemberList;
import longah.util.Subtransaction;
import longah.util.TransactionList;
import longah.handler.StorageHandler;
import longah.exception.LongAhException;
import longah.exception.ExceptionMessage;

public class Group {
    private MemberList members;
    private TransactionList transactions;
    private StorageHandler storage;
    private ArrayList<Subtransaction> transactionSolution;

    /**
     * Constructs a new Group instance with an empty member list and transaction list.
     */
    public Group() throws LongAhException {
        this.members = new MemberList();
        this.transactions = new TransactionList();
        this.storage = new StorageHandler(this.members, this.transactions);
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
     */
    public void updateTransactionSolution() {
        this.transactionSolution = this.members.solveTransactions();
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

        updateTransactionSolution();
        String transactionExpression = "borrowerName";

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
        System.out.println(borrowerName + " has no more debts!");
    }
}
