package seedu.duke;

public class Topic {
    protected QuestionsList chosenQuestionsList;
    protected String topicName;
    protected boolean hasAttempted;

    public Topic(QuestionsList chosenQuestionsList, String topicName, boolean hasAttempted){
        this.chosenQuestionsList = chosenQuestionsList;
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
