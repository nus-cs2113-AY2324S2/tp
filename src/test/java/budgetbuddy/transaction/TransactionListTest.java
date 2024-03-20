package budgetbuddy.transaction;

import budgetbuddy.account.Account;
import budgetbuddy.transaction.type.Income;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import budgetbuddy.transaction.type.Transaction;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TransactionListTest {

    private TransactionList transactionList;
    private Account account;

    @BeforeEach
    public void setUp() throws IOException {
        transactionList = new TransactionList();
        account = new Account();
    }

    @Test
    public void getTransactions_initiallyEmpty() {
        assertEquals(0, transactionList.getTransactions().size());
    }

    @Test
    public void processTransaction_addsTransaction() {
        Transaction testTransaction = new Income("Test", 200,"Personal", "14-03-2024",
                account);
        transactionList.processTransaction("add /t/Income /n/Test /$/200 /d/14-03-2024 /c/Personal", account);

        assertEquals(1, transactionList.getTransactions().size());
        assertEquals(testTransaction.getDescription(), transactionList.getTransactions().get(0).getDescription());
        assertEquals(testTransaction.getAmount(), transactionList.getTransactions().get(0).getAmount());
        assertEquals(testTransaction.getCategory(), transactionList.getTransactions().get(0).getCategory());
        assertEquals(testTransaction.getDate(), transactionList.getTransactions().get(0).getDate());
    }

    @Test
    public void removeTransaction_removesCorrectTransaction() {
        Transaction testTransaction1 = new Income("Test1", 100, "Category1",
                "14-03-2024", account);
        Transaction testTransaction2 = new Income("Test2", 200, "Category2",
                "16-03-2024", account);
        transactionList.addTransaction(testTransaction1);
        transactionList.addTransaction(testTransaction2);

        transactionList.removeTransaction("delete 1", account);

        assertEquals(1, transactionList.getTransactions().size());
        assertEquals(testTransaction2, transactionList.getTransactions().get(0));
    }

    @Test
    public void removeTransaction_withInvalidIndex_throwsIndexOutOfBoundsException() {
        Transaction testTransaction = new Income("Test", 200, "Personal",
                "14-03-2024", account);
        transactionList.addTransaction(testTransaction);

        assertThrows(IndexOutOfBoundsException.class, () -> transactionList.removeTransaction(
                "delete 2", account));
    }
}
