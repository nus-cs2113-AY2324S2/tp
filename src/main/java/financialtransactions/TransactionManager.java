package financialtransactions;


public class TransactionManager {
    private TransactionList<Inflow> inflows;
    private TransactionList<Outflow> outflows;

    public TransactionManager() {
        this.inflows = new TransactionList<>();
        this.outflows = new TransactionList<>();
    }

    public boolean addTransaction(Transaction<?> transaction) {
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
        if (isInflow) {
            return inflows.removeTransacitonIndex(index);
        } else {
            return outflows.removeTransacitonIndex(index);
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

    public void displayTransactionHistory(int n) {
        System.out.println("Inflows:\n" + inflows.lastNTransactions(n) + "\nOutflows:\n" + outflows.lastNTransactions(n));
    }
}
