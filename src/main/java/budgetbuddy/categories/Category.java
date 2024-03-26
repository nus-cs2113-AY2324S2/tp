package budgetbuddy.categories;

public enum Category {
    DINING(1),
    GROCERIES(2),
    UTILITIES(3),
    TRANSPORTATION(4),
    HEALTHCARE(5),
    ENTERTAINMENT(6),
    RENT(7),
    SALARY(8),
    OTHER(9);

    private int categoryNum;

    Category(int categoryNum) {
        this.categoryNum = categoryNum;
    }
}
