package activeedge.task;

public class Task {
    protected String description;

    public Task(String description) {
        this.description = description;
    }

    public String getDescription() {
        return this.description;
    }

    @Override
    public String toString() {
        return this.getDescription();
    }
}
