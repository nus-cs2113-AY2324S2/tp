package exceptions;

public class BookNotFoundException extends IndexOutOfBoundsException {
    public BookNotFoundException(String message) {
        super(message);
    }
}
