package FinancialManagement;
import java.util.HashMap;
import java.util.Map;

    public class FinancialManager {
        private double emergencyFund;

        public class Stock {
            private double price;
            private int quantity;

            public Stock(double price, int quantity) {
                this.price = price;
                this.quantity = quantity;
            }

            public double getValue() {
                return price * quantity;
            }
        }

        public class Loan {
            private double amount;
            private double interestRate;

            public Loan(double amount, double interestRate) {
                this.amount = amount;
                this.interestRate = interestRate;
            }

            public double getAmount() {
                return amount;
            }

            public double calculateInterest() {
                return amount * interestRate;
            }
        }

        // 投资组合和财务数据
        private Map<String, Stock> stocks;
        private Map<String, Loan> loans;
        private double cashBalance;

        // 预算管理
        private double monthlyIncome;
        private double monthlyExpense;
        private double budgetLimit;

        public FinancialManager(double initialCash, double monthlyIncome, double budgetLimit) {
            this.stocks = new HashMap<>();
            this.loans = new HashMap<>();
            this.cashBalance = initialCash;
            this.monthlyIncome = monthlyIncome;
            this.monthlyExpense = 0;
            this.budgetLimit = budgetLimit;
        }

        // 添加股票资产
        public void addStock(String symbol, double price, int quantity) {
            stocks.put(symbol, new Stock(price, quantity));
        }

        // 添加贷款负债
        public void addLoan(String loanName, double amount, double interestRate) {
            loans.put(loanName, new Loan(amount, interestRate));
        }

        // 记录支出并更新月支出
        public void recordExpense(double amount) {
            monthlyExpense += amount;
            cashBalance -= amount;
        }

        // 检查是否超出预算
        public boolean isOverBudget() {
            return monthlyExpense > budgetLimit;
        }

        // 计算总资产价值
        public double getTotalAssetsValue() {
            double totalValue = cashBalance;
            for (Stock stock : stocks.values()) {
                totalValue += stock.getValue();
            }
            return totalValue;
        }

        // 计算总负债
        public double getTotalLiabilities() {
            return loans.values().stream().mapToDouble(Loan::getAmount).sum();
        }

        // 打印财务概要
        public void printFinancialSummary() {
            System.out.println("Total Assets Value: $" + getTotalAssetsValue());
            System.out.println("Total Liabilities: $" + getTotalLiabilities());
            System.out.println("Cash Balance: $" + cashBalance);
            System.out.println("Is Over Budget: " + isOverBudget());
        }

        public void adjustInvestmentPortfolio(String stockSymbol, int quantity) {
            Stock stock = stocks.get(stockSymbol);
            if (stock != null) {
                stock.quantity += quantity;
                System.out.println("Adjusted " + stockSymbol + " quantity to " + stock.quantity);
            }
        }

        public void manageEmergencyFund(double amount) {
            this.emergencyFund = amount;
            System.out.println("Emergency fund set to: $" + amount);
        }

    }
