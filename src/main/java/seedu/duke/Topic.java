package seedu.duke;

public class Topic {
    protected String topicName;
    protected boolean hasAttempted;

    public Topic(String topicName, boolean hasAttempted){
        this.topicName = topicName;
        this.hasAttempted = hasAttempted;
    }

    public String getStatus() {
        if (hasAttempted) {
            return "Attempted";
        } else {
            return "Not attempted";
        }
    }

    public void markAsAttempted() {
        this.hasAttempted = true;
    }

    public String toString(){
        return "[" + getStatus() + "]" + topicName;
    }

}
