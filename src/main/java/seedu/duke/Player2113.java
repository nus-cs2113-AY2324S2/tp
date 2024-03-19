package seedu.duke;

public class Player2113 {
    public static final String SOME_FILE_PATH = "something";
    private Ui ui;
    private final QuestionsList questionsList;
    private final Helper helper;
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
        }

    }
    public void run() {
        ui = new Ui();
        ui.sayHi();
        while (ui.isPlaying) {
            ui.readCommands(ui, questionsList, helper);
        }

    }
    public static void main(String[] args) {
        new Player2113(SOME_FILE_PATH).run();
    }
}
