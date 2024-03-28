package seedu.duke;

public class Gift extends Favourites {

    protected String completionStatus;
    public Gift(String name, String completionStatus) {
        super(name);
        this.completionStatus = completionStatus;
    }

    @Override
    public String toString() {
        return (description);
    }

    public void markComplete() {
        this.completionStatus = "C";
    }

    public String getCompletionStatus() {
        return completionStatus;
    }

}
