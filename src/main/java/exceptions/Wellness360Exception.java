package exceptions;

public class Wellness360Exception extends Exception {
    public Wellness360Exception(String message) {
        super("Error: " + message);
    }
}
