package meditracker.exception;

//@@author annoy-o-mus
/**
 * Exception related to the reading and writing to the file and directories.
 */
public class FileReadWriteException extends Exception {
    public FileReadWriteException(String errorMessage) {
        super(errorMessage);
    }
}
