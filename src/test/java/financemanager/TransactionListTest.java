package financemanager;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import transactions.Transaction;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TransactionListTest {

    private TransactionList transactionList;

    @BeforeEach
    public void setUp() {
        transactionList = new TransactionList();
    }

    @Test
    public void getTransactions_initiallyEmpty() {
        assertEquals(0, transactionList.getTransactions().size());
    }

    @Test
    public void processTransaction_addsTransaction() {
        Transaction testTransaction = new Transaction("Test", 200,"Personal", "14-03-2024");
        transactionList.processTransaction("add /n/Test /$/200 /d/14-03-2024 /c/Personal");

        assertEquals(1, transactionList.getTransactions().size());
        assertEquals(testTransaction.getDescription(), transactionList.getTransactions().get(0).getDescription());
        assertEquals(testTransaction.getAmount(), transactionList.getTransactions().get(0).getAmount());
        assertEquals(testTransaction.getCategory(), transactionList.getTransactions().get(0).getCategory());
        assertEquals(testTransaction.getDate(), transactionList.getTransactions().get(0).getDate());
    }
}