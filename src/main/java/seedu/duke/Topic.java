package seedu.duke;

public class Topic {
    protected QuestionsList chosenQuestionsList;
    protected String topicName;
    protected boolean hasAttemptedStatus;

    protected String summary;

    public Topic(QuestionsList chosenQuestionsList, String topicName, boolean hasAttemptedStatus, String summary){
        this.chosenQuestionsList = chosenQuestionsList;
        this.topicName = topicName;
        this.hasAttemptedStatus = hasAttemptedStatus;
        this.summary = summary;
    }

    public boolean hasAttempted() {
        return this.hasAttemptedStatus;
    }

    public void markAsAttempted() {
        this.hasAttemptedStatus = true;
    }

    public String toString(){
        String status = this.hasAttempted() ? "Attempted" : "Not attempted";
        return "[" + status + "]" + topicName;
    }

}
