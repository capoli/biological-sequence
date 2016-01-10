package be.kuleuven.OOP.exceptions;

/**
 * Created by delphinecappelle on 30/12/15.
 */
public class IllegalParseException extends Exception {
    /**
     * Constructs a new exception with the specified detail message.
     *
     * @param message the detail message. The detail message is saved for
     *                later retrieval by the {@link #getMessage()} method.
     */
    public IllegalParseException(String message) {
        super(message);
    }
}
