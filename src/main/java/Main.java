import java.util.Scanner;

import financialtransactions.Inflow;
import financialtransactions.Outflow;
import financialtransactions.TransactionManager;
import user.Authentication;
import user.BaseUser;

public class Main {
    public static void main(String[] args) {
        BaseUser user = new BaseUser("Bob");
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter password: ");
        String password = sc.nextLine();
        Authentication auth = user.getAuthentication();
        if (auth.checkPassword("Bob", password)) {
            System.out.println("Password is correct");
        } else {
            System.out.println("Password is incorrect");
        }

        TransactionManager manager = new TransactionManager();
        Inflow income = new Inflow("Salary payment", 400.00, null);
        income.setCategory(Inflow.Category.INCOME);
        manager.addTransaction(income);

        Inflow investment = new Inflow("Investment", 500.00, null);
        investment.setCategory(Inflow.Category.INVESTMENT);
        manager.addTransaction(investment);

        Inflow loan = new Inflow("Loan payment", 400.00, "23/05/2022 1900");
        loan.setCategory(Inflow.Category.LOAN);
        manager.addTransaction(loan);

        Outflow rent = new Outflow("Rent", 50000, null);
        rent.setCategory(Outflow.Category.RENT);
        manager.addTransaction(rent);

        Outflow shopping = new Outflow("Shopping", 200, "23/05/2022 2000");
        shopping.setCategory(Outflow.Category.SHOPPING);
        manager.addTransaction(shopping);

        System.out.println(manager.toString());
        sc.close();
    }
}
