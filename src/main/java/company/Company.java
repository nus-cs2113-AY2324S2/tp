package company;

public class Company {
    private final String name;
    private int numberOfEmployees;
    private int revenue;

    public Company(String name, int numberOfEmployees, int revenue) {
        this.name = name;
        this.numberOfEmployees = numberOfEmployees;
        this.revenue = revenue;
    }

    public void hireEmployee() {
        numberOfEmployees++;
    }

    public void fireEmployee() {
        numberOfEmployees--;
    }
}
