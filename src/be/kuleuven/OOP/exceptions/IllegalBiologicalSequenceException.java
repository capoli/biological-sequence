package be.kuleuven.OOP.exceptions;

public class IllegalBiologicalSequenceException extends Exception {
    /**
     * Constructs a new exception with the specified detail message.
     *
     * @param message the detail message. The detail message is saved for
     *                later retrieval by the {@link #getMessage()} method.
     */
    public IllegalBiologicalSequenceException(String message) {
        super(message);
    }
}
