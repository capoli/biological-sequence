
package be.kuleuven.OOP;

import java.util.List;

import be.kuleuven.OOP.exceptions.*;
import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Raw;

/**
 * A class of biological alphabets.
 *
 * @author delphinecappelle
 * @version 1.0
 */

public abstract class Alphabet {
    /**
     * Initialize this alphabet with given name, given list of symbols,
     * and create a valid symbols string based on the given list of symbols.
     *
     * @param alphabetName The name for this new alphabet.
     * @param symbols      The symbols for this new alphabet.
     * @post The alphabet name for this new alphabet is the same as the given alphabet name.
     * | new.getAlphabetName() == alphabetName
     * @post The symbols for this new alphabet are equal to the given symbols.
     * | new.getSymbols() == symbols
     * @post The resulting string contains all the actual symbols as valid symbols for this new alphabet.
     * | new.getValidSymbols() == validSymbols
     */
    public Alphabet(String alphabetName, List<Symbol> symbols) {
        this.alphabetName = alphabetName;
        this.symbols = symbols;

        StringBuilder allSymbols = new StringBuilder();
        for (Symbol symbol : symbols) {
            allSymbols.append(symbol.getActual());
        }
        this.validSymbols = allSymbols.toString();
    }

    /**
     * Check whether this Alphabet has the given alphabet as one
     * of its alphabets.
     *
     * @param alphabet The alphabet to be checked.
     * @return False if the given alphabet is not effective.
     * | if (alphabet == null)
     * |   then result == false
     */
    public abstract boolean hasAsAlphabet(Alphabet alphabet);

    /**
     * Return the alphabet name of this alphabet.
     */
    @Basic
    @Raw
    public String getAlphabetName() {
        return alphabetName;
    }

    /**
     * Variable registering the name of this alphabet
     */
    private final String alphabetName;

    /**
     * Return the list of symbols of this alphabet.
     */
    @Basic
    @Raw
    public List<Symbol> getSymbols() {
        return symbols;
    }

    /**
     * Variable registering the list of symbols for this alphabet
     */
    private final List<Symbol> symbols;

    /**
     * Return the valid symbols of this alphabet.
     */
    @Basic
    @Raw
    public String getValidSymbols() {
        return validSymbols;
    }

    /**
     * Variable registering the valid symbols for this alphabet
     */
    private final String validSymbols;


    /**
     * Check whether the given nucleotide sequence is a valid nucleotide sequence for this alphabet.
     *
     * @param nucleotideSequence The nucleotide sequence to check
     * @return True if and only if the given nucleotide sequence consists only of valid symbols for this alphabet.
     * | result == nucleotideSequence.toUpperCase().matches("\\[" + validSymbols + "]+")
     * @throws IllegalAlphabetException The given nucleotide sequence is not an valid nucleotide sequence for this alphabet.
     *                                  | ! nucleotideSequence.toUpperCase().matches("\\[" + validSymbols + "]+")
     */
    public boolean isValidNucleotideSequence(String nucleotideSequence) throws IllegalAlphabetException {
        if (nucleotideSequence.toUpperCase().matches("[" + validSymbols + "]+")) {
            return true;
        } else
            throw new IllegalAlphabetException(nucleotideSequence + " has one or more non-valid symbols, does not match the requirements of a " + alphabetName + " sequence.");
    }

    /**
     * Check whether the given symbol is a valid nucleotide symbol for this alphabet.
     *
     * @param symbol The symbol to check
     * @return True if and only if the given symbols occurs in the list of valid symbols for this alphabet.
     * | result == validSymbols.indexOf(symbol) != -1
     * @throws IllegalAlphabetException The given symbol is not a valid symbol for this alphabet. If index of symbol isn't found indexOf will return -1 as index
     *                                  | ! validSymbols.indexOf(symbol) != -1
     */
    public boolean isValidNucleotideSymbol(char symbol) throws IllegalAlphabetException {
        if (validSymbols.indexOf(symbol) != -1)
            return true;
        else
            throw new IllegalAlphabetException(symbol + "is not a valid symbol of the " + alphabetName + " alphabet.");
    }

    /**
     * Check whether the given object, in this case a symbol, is an instance of this alphabet
     *
     * @param obj the reference object with which to compare.
     * @return True if the given object is effective, is an instance of this alphabet and it's equal to this alphabet
     * instance or if all the symbols both alphabet objetcs contains are equal.
     * False for all other cases.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj != null && obj instanceof Alphabet) {
            if (obj == this) return true;
            else {
                Alphabet other = (Alphabet) obj;
                for (Symbol symbol : this.symbols) {
                    boolean foundSymbol = false;
                    for (Symbol otherSymbol : other.getSymbols()) {
                        if (symbol.getActual() == otherSymbol.getActual() && symbol.getComplementary() == otherSymbol.getComplementary()) {
                            foundSymbol = true;
                        }
                    }
                    if (!foundSymbol) return false;
                }
                return true;
            }
        }
        return false;
    }


}
