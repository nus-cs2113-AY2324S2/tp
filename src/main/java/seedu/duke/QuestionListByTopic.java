package seedu.duke;

import java.util.ArrayList;

public class QuestionListByTopic {
    protected ArrayList<QuestionsList> questionListByTopic;

    public QuestionListByTopic(){
        questionListByTopic = new ArrayList<>();
    }
    public void addQuestionSet(QuestionsList questionsList){
        questionListByTopic.add(questionsList);
    }

    public QuestionsList getQuestionSet(int index){
        return questionListByTopic.get(index);
    }
}
