package be.kuleuven.OOP;

import be.kuleuven.cs.som.annotate.Basic;

/**
 * A class symbols involving actual and complementary symbols representing nucleotides in the single letter codes..
 *
 * @author delphinecappelle
 * @version 1.0
 */
public class Symbol {

    /**
     * Initialize this symbol with the given actual and complementary symbol.
     *
     * @param actual        The actual symbol of this new symbol.
     * @param complementary The complementary symbol of this new symbol
     * @post This symbol has the given actual symbol as its actual symbol.
     * | new.getActual() == actual
     * @post This symbol has the given complementary symbol as its complementary symbol.
     * | new.getComplementary() == complementary
     */
    public Symbol(char actual, char complementary) {
        this.actual = actual;
        this.complementary = complementary;
    }

    /**
     * Return the actual symbol of this symbol.
     */
    @Basic
    public char getActual() {

        return actual;
    }

    /**
     * A variable registering the actual symbol of this symbol.
     */
    private final char actual;

    /**
     * Return the complementary symbol of this symbol.
     */
    @Basic
    public char getComplementary() {
        return complementary;
    }


    /**
     * A variable registering the complementary symbol of this symbol.
     */
    private final char complementary;

}
