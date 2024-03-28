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
    OTHERS(9, "Others");

    private final int categoryNum;
    private final String categoryName;

    Category(int categoryNum, String categoryName) {
        this.categoryNum = categoryNum;
        this.categoryName = categoryName;
    }

    public static Category fromNumber(int number) {
        for (Category category : Category.values()) {
            if (category.categoryNum == number) {
                return category;
            }
        }
        return null;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public int getCategoryNum() {
        return categoryNum;
    }
}
