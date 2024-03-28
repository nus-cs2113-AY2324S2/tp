package budgetbuddy.transaction;

import budgetbuddy.account.Account;
import budgetbuddy.categories.Category;
import budgetbuddy.exceptions.EmptyArgumentException;
import budgetbuddy.exceptions.InvalidAddTransactionSyntax;
import budgetbuddy.exceptions.InvalidIndexException;
import budgetbuddy.exceptions.InvalidTransactionTypeException;
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
    public void processTransaction_addsTransaction()
            throws InvalidTransactionTypeException, InvalidAddTransactionSyntax, EmptyArgumentException {
        Transaction testTransaction = new Income("Test", 200, "14-03-2024",
                account);
        testTransaction.setCategory(Category.fromNumber(1));
        transactionList.processTransaction("add /t/Income /n/Test /$/200 /d/14-03-2024 /c/1", account);

        assertEquals(1, transactionList.getTransactions().size());
        assertEquals(testTransaction.getDescription(), transactionList.getTransactions().get(0).getDescription());
        assertEquals(testTransaction.getAmount(), transactionList.getTransactions().get(0).getAmount());
        assertEquals(testTransaction.getCategory().getCategoryName(),
                transactionList.getTransactions().get(0).getCategory().getCategoryName());
        assertEquals(testTransaction.getDate(), transactionList.getTransactions().get(0).getDate());
    }
    @Test

    public void processTransaction_withInvalidAddSyntax_throwsInvalidAddTransactionSyntax() {

        assertThrows(InvalidAddTransactionSyntax.class, () -> transactionList.processTransaction(
                "add Expense /n/Shopping /$/50 /d/14-03-2024 /c/2", account));
        assertThrows(InvalidAddTransactionSyntax.class, () -> transactionList.processTransaction(
                "add /t/Expense Shopping /$/50 /d/14-03-2024 /c/2", account));
        assertThrows(InvalidAddTransactionSyntax.class, () -> transactionList.processTransaction(
                "add /t/Expense /n/Shopping 50 /d/14-03-2024 /c/2", account));
        assertThrows(InvalidAddTransactionSyntax.class, () -> transactionList.processTransaction(
                "add /t/Expense /n/Shopping /$/50 14-03-2024 /c/2", account));
    }

    @Test
    public void processTransaction_withInvalidTransactionType_throwsTransactionTypeException() {

        assertThrows(InvalidTransactionTypeException.class, () -> transactionList.processTransaction(
                "add /t/Donation /n/Test /$/200 /d/14-03-2024 /c/2", account));
    }

    @Test
    public void removeTransaction_removesCorrectTransaction() throws EmptyArgumentException, InvalidIndexException {
        Transaction testTransaction1 = new Income("Test1", 100,
                "14-03-2024", account);
        testTransaction1.setCategory(Category.fromNumber(1));
        Transaction testTransaction2 = new Income("Test2", 200,
                "16-03-2024", account);
        testTransaction1.setCategory(Category.fromNumber(2));
        transactionList.addTransaction(testTransaction1);
        transactionList.addTransaction(testTransaction2);

        transactionList.removeTransaction("delete 1", account);

        assertEquals(1, transactionList.getTransactions().size());
        assertEquals(testTransaction2, transactionList.getTransactions().get(0));
    }

    @Test
    public void removeTransaction_withInvalidIndex_throwsIndexOutOfBoundsException() {
        Transaction testTransaction = new Income("Test", 200,
                "14-03-2024", account);
        transactionList.addTransaction(testTransaction);

        assertThrows(InvalidIndexException.class, () -> transactionList.removeTransaction(
                "delete 2", account));
    }

    @Test
    public void removeTransaction_withMissingIndex_throwsEmptyArgumentException() {
        Transaction testTransaction = new Income("Test", 100,
                "14-03-2024", account);
        transactionList.addTransaction(testTransaction);

        assertThrows(EmptyArgumentException.class, () -> transactionList.removeTransaction(
                "delete", account));
    }

    @Test
    public void removeTransaction_withInvalidIndex_throwsNumberFormatException() {
        Transaction testTransaction = new Income("Test", 100,
                "14-03-2024", account);
        transactionList.addTransaction(testTransaction);

        assertThrows(NumberFormatException.class, () -> transactionList.removeTransaction(
                "delete one", account));
    }
}
