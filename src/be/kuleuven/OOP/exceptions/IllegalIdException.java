/**
 *
 */
package be.kuleuven.OOP.exceptions;

import be.kuleuven.cs.som.annotate.*;

import be.kuleuven.OOP.BiologicalSequence;

/**
 * @author delphinecappelle
 */
public class IllegalIdException extends Exception {
    /**
     * Initialize this new illegal id exception with given id.
     *
     * @param message The detail message.
     * @post
     * @effect This new illegal id exception is further initialized as a new exception involving no diagnostic message and no cause
     * | super()
     */
    public IllegalIdException(String message) {
        super(message);
    }
}


