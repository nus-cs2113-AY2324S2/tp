package budgetbuddy.categories;

public enum Category {
    DINING(1, "Dining"),
    GROCERIES(2, "Groceries"),
    UTILITIES(3, "Utilities"),
    TRANSPORTATION(4, "Transportation"),
    HEALTHCARE(5, "Healthcare"),
    ENTERTAINMENT(6, "Entertainment"),
    RENT(7, "Rent"),
    SALARY(8, "Salary"),
    OTHER(9, "Other");

    private int categoryNum;
    private String categoryName;

    Category(int categoryNum, String categoryName) {
        this.categoryNum = categoryNum;
        this.categoryName = categoryName;
    }
}
