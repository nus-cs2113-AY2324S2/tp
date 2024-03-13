package utility;

public class CustomExceptions extends Exception {

    public static class OutOfBounds extends Exception {
        public OutOfBounds(String message) {
            super("\u001b[31mError: " + message + "\u001b[0m");
        }
    }

    public static class InvalidInput extends Exception {
        public InvalidInput(String message) {
            super("\u001b[31mError: " + message + "\u001b[0m");
        }
    }
}
