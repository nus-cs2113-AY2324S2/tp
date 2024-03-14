package financialtransactions;


public class TransactionManager {
    private TransactionList<Transaction<?>> transactionList;
    private TransactionList<Inflow> inflows;
    private TransactionList<Outflow> outflows;

    public TransactionManager() {
        this.transactionList = new TransactionList<>();
        this.inflows = new TransactionList<>();
        this.outflows = new TransactionList<>();
    }

    public boolean addTransaction(Transaction<?> transaction) {
        transactionList.addTransaction(transaction);
        if (transaction instanceof Inflow) {
            Inflow inflow = (Inflow) transaction;
            return inflows.addTransaction(inflow);
        } else if (transaction instanceof Outflow) {
            Outflow outflow = (Outflow) transaction;
            return outflows.addTransaction(outflow);
        }
        System.out.println("Invalid transaction type.");
        return false;
    }

    public boolean removeTransaction(int index, boolean isInflow) {
        transactionList.removeTransactionIndex(index);
        if (isInflow) {
            return inflows.removeTransactionIndex(index);
        } else {
            return outflows.removeTransactionIndex(index);
        }
    }

    public double getTotalBalance() {
        double inflowBalance = inflows.getBalance();
        double outflowBalance = outflows.getBalance();
        return inflowBalance + outflowBalance;
    }

    @Override
    public String toString() {
        return "Inflows:\n" + inflows.toString() + "\nOutflows:\n" + outflows.toString();
    }

    public void showLastNTransactions(int n) {
        int listSize = transactionList.getTransactionListSize();
        int index = 1;

        System.out.println("Inflows:\nTransactions:");
        for (int i = listSize - 1; i > listSize - n - 1; i--) {
            Transaction<?> transaction = transactionList.getNthTransaction(i);
            if (transaction instanceof Inflow) {
                System.out.println(index + ") " + transactionList.getNthTransaction(i).toString());
                index++;
            }
        }

        index = 1;
        System.out.println("\nOutflows:\nTransactions:");
        for (int i = listSize - 1; i > listSize - n - 1; i--) {
            Transaction<?> transaction = transactionList.getNthTransaction(i);
            if (transaction instanceof Outflow) {
                System.out.println(index + ") " + transactionList.getNthTransaction(i).toString());
                index++;
            }
        }
    }

    public String toSave() {
        return inflows.toSave() + outflows.toSave();
    }
}
