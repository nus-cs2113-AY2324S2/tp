package command;
public class ActiveEdgeException extends Exception{
    public String warning;

    public ActiveEdgeException(String warning) {
        super(warning);
    }
}
