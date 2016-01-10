/**
 *
 */
package be.kuleuven.OOP.exceptions;

import be.kuleuven.OOP.Alphabet;
import be.kuleuven.OOP.BiologicalSequence;

/**
 * @author delphinecappelle
 */
public class IllegalAlphabetException extends Exception {

    /**
     * Constructs a new exception with the specified detail message.
     *
     * @param message the detail message. The detail message is saved for
     *                later retrieval by the {@link #getMessage()} method.
     */
    public IllegalAlphabetException(String message) {
        super(message);
    }

}
