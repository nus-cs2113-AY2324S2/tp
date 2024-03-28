package exceptions;

public class BookUnreadAlreadyException extends RuntimeException{
    public BookUnreadAlreadyException(String message) {
        super(message);
    }
}
