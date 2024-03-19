package seedu.duke;
public class Game {
    protected boolean isExit; // true to exit
    protected String gameName;

    public Game(String line) {
        gameName = line;
        isExit = false;
    }

    public void startGame() {
        switch (gameName) { //to parse string
        case ("tic tac toe"):
            //start tic-tac-toe
            break;
        case ("hangman"):
            //starts hangman
            break;
        default:
            //throw exception
            break;
        }
    }
}
