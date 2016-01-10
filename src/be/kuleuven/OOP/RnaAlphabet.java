package be.kuleuven.OOP;

import java.util.Arrays;
import java.util.List;

import be.kuleuven.OOP.exceptions.*;

/**
 * A class referencing the symbols of a RNA alphabet
 *
 * @author delphinecappelle
 * @version 1.0
 */


public class RnaAlphabet extends Alphabet {
    /**
     * Initialize this DNA alphabet with given alphabet name and given symbols.
     */
    public RnaAlphabet() {
        super("RNA",
                Arrays.asList(
                        new Symbol('A', 'U'),
                        new Symbol('C', 'G'),
                        new Symbol('G', 'C'),
                        new Symbol('U', 'A'),
                        new Symbol('N', 'N')
                ));
    }

    @Override
    public boolean hasAsAlphabet(Alphabet alphabet) {
        return alphabet == this;
    }

    /**
     * Check whether the given nucleotide sequence is a valid nucleotide sequence for this RNA alphabet.
     */
    public boolean isValidNucleotideSequence(String nucleotideSequence) throws IllegalAlphabetException {
        return super.isValidNucleotideSequence(nucleotideSequence);
    }

    /**
     * Check whether the given symbol is a valid nucleotide symbol for this RNA alphabet.
     */
    public boolean isValidNucleotideSymbol(char symbol) throws IllegalAlphabetException {
        return super.isValidNucleotideSymbol(symbol);
    }
}
