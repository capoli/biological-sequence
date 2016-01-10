package be.kuleuven.OOP;

import java.util.Arrays;
import java.util.List;

import be.kuleuven.OOP.exceptions.*;

/**
 * A class referencing the symbols of a DNA alphabet
 *
 * @author delphinecappelle
 * @version 1.0
 */

public class DnaAlphabet extends Alphabet {
    /**
     * Initialize this DNA alphabet with the given alphabet name and given symbols.
     */
    public DnaAlphabet() {
        super("DNA",
                Arrays.asList(
                        new Symbol('A', 'T'),
                        new Symbol('C', 'G'),
                        new Symbol('G', 'C'),
                        new Symbol('T', 'A'),
                        new Symbol('N', 'N')
                ));
    }

    @Override
    public boolean hasAsAlphabet(Alphabet alphabet) {
        return alphabet == this;
    }

    /**
     * Check whether the given nucleotide sequence is a valid nucleotide sequence for this DNA alphabet.
     */
    public boolean isValidNucleotideSequence(String nucleotideSequence) throws IllegalAlphabetException {
        return super.isValidNucleotideSequence(nucleotideSequence);
    }


    /**
     * Check whether the given symbol is a valid nucleotide symbol for this DNA alphabet.
     */
    public boolean isValidNucleotideSymbol(char symbol) throws IllegalAlphabetException {
        return super.isValidNucleotideSymbol(symbol);
    }
}
