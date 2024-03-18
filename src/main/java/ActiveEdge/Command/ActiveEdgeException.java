package ActiveEdge.Command;
public class ActiveEdgeException extends Exception{
    public String warning;

    public ActiveEdgeException(String warning) {
        super(warning);
    }
}
