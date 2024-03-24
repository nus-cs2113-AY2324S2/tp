package exceptions;

public class BookReadAlreadyException extends RuntimeException{
    public BookReadAlreadyException(String message) {
        super(message);
    }
}
