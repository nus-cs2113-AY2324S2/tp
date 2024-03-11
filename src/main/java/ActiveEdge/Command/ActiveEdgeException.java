package ActiveEdge.Command;
public class ActiveEdgeException extends Exception{
    public String warning;
    public ActiveEdgeException(String warning) {
        this.warning = warning;
    }
}