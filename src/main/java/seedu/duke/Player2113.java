package seedu.duke;

import java.util.Scanner;

public class Player2113 {
    public static final String SOME_FILE_PATH = "something";
    private Ui ui;
    private QuestionsList questionsList;

    private TopicList topicList;

    public Player2113(String someFilePath) {
        questionsList = new QuestionsList();
        topicList = new TopicList();

    private Helper helper;
    public Player2113(String someFilePath) {
        questionsList = new QuestionsList();
        helper = new Helper();

        if (someFilePath.contentEquals("something")) {
            // TODO: load data from file
            // Add dummy data (for now)
            Question question1 = new Question("question1", "solution1", "explanation1");
            Question question2 = new Question("question2", "solution2", "explanation2");
            questionsList.addQuestion(question1);
            questionsList.addQuestion(question2);

            Topic topic1 = new Topic("topic1", false);
            Topic topic2 = new Topic("topic2", false);
            topicList.addTopic(topic1);
            topicList.addTopic(topic2);
        }

    }
    public void run() {
        ui = new Ui();
        ui.sayHi();
        ui.printTopicList(topicList, ui);
        while (ui.isPlaying) {

            ui.readCommands(ui, questionsList, helper, topicList);
        }

    }
    public static void main(String[] args) {
        new Player2113(SOME_FILE_PATH).run();
    }
}
