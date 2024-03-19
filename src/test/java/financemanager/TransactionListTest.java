package financemanager;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import transaction.Transaction;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

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

    public void removeTransaction_removesCorrectTransaction() {
        Transaction testTransaction1 = new Transaction("Test1", 100, "Category1", "14-03-2024");
        Transaction testTransaction2 = new Transaction("Test2", 200, "Category2", "16-03-2024");
        transactionList.addTransaction(testTransaction1);
        transactionList.addTransaction(testTransaction2);

        transactionList.removeTransaction("delete 1");

        assertEquals(1, transactionList.getTransactions().size());
        assertEquals(testTransaction2, transactionList.getTransactions().get(0));
    }

    @Test
    public void removeTransaction_withInvalidIndex_throwsIndexOutOfBoundsException() {
        Transaction testTransaction = new Transaction("Test", 200, "Personal", "14-03-2024");
        transactionList.addTransaction(testTransaction);

        assertThrows(IndexOutOfBoundsException.class, () -> transactionList.removeTransaction("delete 2"));
    }
}
