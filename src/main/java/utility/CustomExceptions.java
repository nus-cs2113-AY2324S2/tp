package utility;

public class CustomExceptions extends Exception {

    public static class OutOfBounds extends Exception {
        public OutOfBounds(String var1) {
            super("\u001b[31mError: " + var1 + "\u001b[0m");
        }
    }

    public static class InvalidInput extends Exception {
        public InvalidInput(String var1) {
            super("\u001b[31mError: " + var1 + "\u001b[0m");
        }
    }
}
