package longah.node;

import longah.util.MemberList;
import longah.util.TransactionList;
import longah.handler.StorageHandler;
import longah.exception.LongAhException;

public class Group {
    private MemberList members;
    private TransactionList transactions;
    private StorageHandler storage;

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
}
