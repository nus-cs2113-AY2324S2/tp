package budgetbuddy.transaction;

import budgetbuddy.account.Account;
import budgetbuddy.exception.EmptyArgumentException;
import budgetbuddy.exception.InvalidAddTransactionSyntax;
import budgetbuddy.exception.InvalidTransactionTypeException;
import budgetbuddy.transaction.type.Income;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import budgetbuddy.transaction.type.Transaction;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TransactionListTest {

    private TransactionList transactionList;
    private Account account;

    @BeforeEach
    public void setUp() {
        transactionList = new TransactionList();
        account = new Account();
    }

    @Test
    public void getTransactions_initiallyEmpty() {
        assertEquals(0, transactionList.getTransactions().size());
    }

    @Test
    public void processTransaction_addsTransaction() throws InvalidTransactionTypeException, InvalidAddTransactionSyntax {
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
    public void processTransaction_withInvalidAddSyntax_throwsInvalidAddTransactionSyntax() {

        assertThrows(InvalidAddTransactionSyntax.class, () -> transactionList.processTransaction(
                "add Expense /n/Shopping /$/50 /d/14-03-2024 /c/Personal", account));
        assertThrows(InvalidAddTransactionSyntax.class, () -> transactionList.processTransaction(
                "add /t/Expense Shopping /$/50 /d/14-03-2024 /c/Personal", account));
        assertThrows(InvalidAddTransactionSyntax.class, () -> transactionList.processTransaction(
                "add /t/Expense /n/Shopping 50 /d/14-03-2024 /c/Personal", account));
        assertThrows(InvalidAddTransactionSyntax.class, () -> transactionList.processTransaction(
                "add /t/Expense /n/Shopping /$/50 14-03-2024 /c/Personal", account));
        assertThrows(InvalidAddTransactionSyntax.class, () -> transactionList.processTransaction(
                "add /t/Expense /n/Shopping /$/50 /d/14-03-2024 Personal", account));
    }

    @Test
    public void processTransaction_withInvalidTransactionType_throwsTransactionTypeException() {

        assertThrows(InvalidTransactionTypeException.class, () -> transactionList.processTransaction(
                "add /t/Donation /n/Test /$/200 /d/14-03-2024 /c/Personal", account));
    }

    @Test
    public void removeTransaction_removesCorrectTransaction() throws EmptyArgumentException {
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

    @Test
    public void removeTransaction_withMissingIndex_throwsEmptyArgumentException() {
        Transaction testTransaction = new Income("Test", 100, "Personal",
                "14-03-2024", account);
        transactionList.addTransaction(testTransaction);

        assertThrows(EmptyArgumentException.class, () -> transactionList.removeTransaction(
                "delete", account));
    }

    @Test
    public void removeTransaction_withInvalidIndex_throwsNumberFormatException() {
        Transaction testTransaction = new Income("Test", 100, "Personal",
                "14-03-2024", account);
        transactionList.addTransaction(testTransaction);

        assertThrows(NumberFormatException.class, () -> transactionList.removeTransaction(
                "delete one", account));
    }
}
