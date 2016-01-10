package be.kuleuven.OOP;

import java.util.*;

import be.kuleuven.OOP.exceptions.*;

import be.kuleuven.cs.som.annotate.*;
import be.kuleuven.cs.som.taglet.*;

/**
 * A class of biological sequences involving a unique sequence identifier, an organism from where the sequence originates,
 * a nucleotide sequence and an alphabet.
 *
 * @author delphinecappelle
 * @version 2.0
 */
public class BiologicalSequence {
    /**
     * Initialize this new biological sequence with given sequence identifier, given organism,
     * given nucleotide sequence and given alphabet.
     *
     * @param id                 The sequence identifier for this new biological sequence.
     * @param organism           The organism from where this new biological sequence originates.
     * @param nucleotideSequence The sequence of nucleotides for this new biological sequence.
     * @param alphabet           The alphabet corresponding to this new biological sequence.
     * @throws IllegalIdException                                     (id,this)
     *                                                                The given sequence identifier is not a valid identifier for any biological sequence.
     *                                                                | ! isValidId(id)
     * @throws IllegalNucleotideSequenceException(nucleotideSequence, this)
     *                                                                The given nucleotide sequence is not a valid nucleotide sequence for any biological sequence.
     *                                                                | ! isValidNucleotideSequence(nucleotideSequence)
     * @throws IllegalAlphabetException(message)                      This new biological sequence cannot have the given alphabet as its alphabet.
     *                                                                | ! canHaveAsAlphabet(alphabet)
     * @post The new identifier, organism, nucleotide sequence and alphabet of this new biological sequence are retrieved from the instantiateBiologicalSequence method     * | new.getId() == id
     * | new.InstantiateBiologicalSequence() == instantiateBiologicalSequence(id, organism, nucleotideSequence, alphabet)
     */

    @Raw
    public BiologicalSequence(String id, String organism, String nucleotideSequence, Alphabet alphabet)
            throws IllegalIdException, IllegalNucleotideSequenceException, IllegalAlphabetException {
        instantiateBiologicalSequence(id, organism, nucleotideSequence, alphabet);
    }

    /**
     * Initialize this new biological sequence from an unknown organism with given sequence identifier,
     * given nucleotide sequence and given alphabet.
     *
     * @param id                 The sequence identifier for this new biological sequence.
     * @param nucleotideSequence The sequence of nucleotides for this new biological sequence.
     * @param alphabet           The alphabet corresponding to this new biological sequence.
     * @effect This new biological sequence is initialized with the given sequence identifier as its identifier,
     * the given nucleotide sequence as its nucleotide sequence, the given alphabet as its alphabet,
     * and unknown as its organism.
     * | this(id, nucleotideSequence, "unknown", alphabet)
     */

    @Raw
    public BiologicalSequence(String id, String nucleotideSequence, Alphabet alphabet)
            throws IllegalIdException, IllegalNucleotideSequenceException, IllegalAlphabetException {
        this(id, "unknown", nucleotideSequence, alphabet);
    }


    /**
     * Initialize this new biological sequence with given sequence identifier, given organism, given alphabet,
     * and nucleotide sequence that is a concatenation of nucleotide sequences from two given biological sequences.
     *
     * @param firstBiologicalSequence  The biological sequence from which the sequence identifier, organism, alphabet and nucleotide sequence
     *                                 will be retrieved.
     * @param secondBiologicalSequence BiologicalSequence
     *                                 The biological sequence from which the sequence identifier, organism, alphabet and nucleotide sequence
     *                                 will be retrieved.
     * @throws IllegalAlphabetException( )
     *                                   The given biological sequences do not have the same alphabet.
     */
    public BiologicalSequence(BiologicalSequence firstBiologicalSequence, BiologicalSequence secondBiologicalSequence)
            throws IllegalIdException, IllegalNucleotideSequenceException, IllegalAlphabetException {
        if (firstBiologicalSequence.getAlphabet().equals(secondBiologicalSequence.getAlphabet()))
            throw new IllegalAlphabetException("Both sequences don't have the same Alphabet");
        instantiateBiologicalSequence(
                firstBiologicalSequence.getId(),
                firstBiologicalSequence.getOrganism(),
                firstBiologicalSequence.getNucleotideSequence().concat(secondBiologicalSequence.getNucleotideSequence()),
                firstBiologicalSequence.getAlphabet()
        );
    }

    /**
     * Initialize this new biological sequence with given sequence identifier, given organism,
     * given nucleotide sequence and given alphabet.
     *
     * @param id                 The sequence identifier for this new biological sequence.
     * @param organism           The organism from where this new biological sequence originates.
     * @param nucleotideSequence The sequence of nucleotides for this new biological sequence.
     * @param alphabet           The alphabet corresponding to this new biological sequence.
     * @throws IllegalIdException                                     (id,this)
     *                                                                The given sequence identifier is not a valid identifier for any biological sequence.
     *                                                                | ! isValidId(id)
     * @throws IllegalNucleotideSequenceException(nucleotideSequence, this)
     *                                                                The given nucleotide sequence is not a valid nucleotide sequence for any biological sequence.
     *                                                                | ! isValidNucleotideSequence(nucleotideSequence)
     * @throws IllegalAlphabetException("This new biological sequence cannot have the given alphabet as its alphabet")
     *                                                                This new biological sequence cannot have the given alphabet as its alphabet.
     *                                                                | ! canHaveAsAlphabet(alphabet)
     * @post The new sequence identifier of this new biological sequence is equal to the given sequence identifier.
     * | new.getId() == id
     * @post The new organism for this new biological sequence is equal to the given organism.
     * | new.getOrganism() == organism
     * @post The new nucleotide sequence for this new biological sequence is equal to the given nucleotide sequence.
     * | new.getNucleotideSequence() == nucleotideSequence
     */
    private void instantiateBiologicalSequence(String id, String organism, String nucleotideSequence, Alphabet alphabet)
            throws IllegalIdException, IllegalNucleotideSequenceException, IllegalAlphabetException {
        if (!isValidAlphabet(alphabet))
            throw new IllegalAlphabetException("This new biological sequence cannot have the given alphabet as its alphabet");
        setId(id);
        setOrganism(organism);
        this.alphabet = alphabet;
        setNucleotideSequence(nucleotideSequence);
    }

    /**
     * Return the sequence identifier of this biological sequence.
     * The identifier of a biological sequences serves to identify a biological sequence.
     */
    @Basic
    @Raw
    @Immutable
    public String getId() {
        return this.id;
    }

    /**
     * Check whether the given sequence identifier is a valid identifier for any biological sequence.
     *
     * @param id The identifier to check.
     * @return True if and only if the identifier is not empty
     * | id != null
     */
    public static boolean isValidId(String id) {
        return !id.isEmpty();
    }

    /**
     * Set the identifier of this biological sequence to the given identifier.
     *
     * @param id The new identifier for this biological sequence.
     * @throws IllegalIdException (message)
     *                            The given identifier is not a valid identifier for any biological sequence.
     *                            | ! isValidId(id)
     * @post The new identifier of this biological sequence is equal to the given identifier.
     * | new.getId() == id
     */
    @Raw
    @Model
    private void setId(String id) throws IllegalIdException {
        if (!isValidId(id))
            throw new IllegalIdException("The given identifier is not a valid identifier for any biological sequence.");
        this.id = id;
    }

    /**
     * Variable registering the identifier of this biological sequence.
     */
    private String id;

    /**
     * Return the organism of this biological sequences
     * The organism corresponds to the organism from which the biological sequence originates.
     */
    @Basic
    @Raw
    public String getOrganism() {
        return organism;
    }

    /**
     * Set the organism of this biological sequence to the given organism.
     *
     * @param organism The new organism for this biological sequence
     * @post The new organism of this biological sequence is equal to the given organism
     * | new.getOrganism == organism
     */
    @Raw
    @Model
    private void setOrganism(String organism) {
        this.organism = organism;
    }

    /**
     * Variable registering the organism of this biological sequence
     */
    private String organism;

    /**
     * Return the alphabet of this biological sequence.
     */
    @Basic
    @Raw
    @Immutable
    public Alphabet getAlphabet() {
        return this.alphabet;
    }

    /**
     * Check whether the given alphabet is a valid alphabet for any biological sequence.
     *
     * @param alphabet The alphabet to check
     * @return True if and only if the given alphabet is a subclass of the superclass alphabet
     * | result == alphabet instanceof Alphabet
     */
    public static boolean isValidAlphabet(Alphabet alphabet) {
        return alphabet instanceof Alphabet;
    }

    /**
     * Set the alphabet for this biological sequence to the given alphabet.
     *
     * @param alphabet the new alphabet of this biological sequence.
     * @throws IllegalAlphabetException("The given alphabet is not a valid alphabet")
     *                                       The given alphabet is not a valid alphabet for any biological sequence.
     *                                       | ! isValidSequence(alphabet)
     * @post The new alphabet of this biological sequence is the same as the given alphabet.
     * | new.getAlphabet() == alphabet
     */
    @Raw
    private void setAlphabet(Alphabet alphabet) throws IllegalAlphabetException {
        if (!isValidAlphabet(alphabet))
            throw new IllegalAlphabetException("The given alphabet is not a valid alphabet");
        this.alphabet = alphabet;
    }

    /**
     * Variable registering the alphabet of this biological sequence.
     */
    private Alphabet alphabet;

    /**
     * Return the nucleotide sequence of this biological sequence.
     * The nucleotide sequence is expresses the successive order of nucleotides in a biological sequence.
     * The nucleotides are represented by their corresponding single letter codes ('A', 'T', 'C', 'G', 'U' or 'N').
     */
    @Basic
    @Raw
    public String getNucleotideSequence() {
        return nucleotideSequence;
    }


    /**
     * Set the nucleotide sequence of this biological sequence to the given nucleotide sequence.
     *
     * @param nucleotideSequence The new nucleotide sequence for this biological sequence
     * @throws IllegalNucleotideSequenceException("The given nucleotide sequence is not a valid nucleotide sequence")
     *                                                 The given nucleotide sequence is not a valid nucleotide sequence for any biological sequence.
     *                                                 | ! isValidNucleotideSequence(nucleotideSequence)
     * @post The new nucleotide sequence of this biological sequence is equal to the given nucleotide sequence.
     * | new.getNucleotideSequence() == nucleotideSequence
     */
    @Raw
    @Model
    private void setNucleotideSequence(String nucleotideSequence) throws IllegalNucleotideSequenceException, IllegalAlphabetException {
        if (!alphabet.isValidNucleotideSequence(nucleotideSequence))
            throw new IllegalNucleotideSequenceException("The given nucleotide sequence is not a valid nucleotide sequence for any biological sequence");
        this.nucleotideSequence = nucleotideSequence;
    }

    /**
     * Variable registering the nucleotide sequence of a biological sequence.
     */
    private String nucleotideSequence;


    /**
     * Return the length of the nucleotide sequence of this biological sequence
     *
     * @return The length of the nucleotide sequence of this biological sequence.
     * | result == getNucleotideSequence().length()
     */
    public int getNucleotideSequenceLength() {
        return getNucleotideSequence().length();
    }

    /**
     * Return the number of times a given nucleotide occurs in the nucleotide sequence of
     * this biological sequence.
     *
     * @param nucleotideSymbol The nucleotide represented as symbol for which the occurrence in this sequence has to be calculated
     * @return Number of times the given nucleotide occurs in the sequence.
     * @throws IllegalAlphabetException("Entered an illegal nucleotide symbol")
     *                                           The entered nucleotide symbol is not a valid symbol for this Alphabet
     *                                           | ! isValidNucleotideSymbol (nucleotideSymbol)
     */
    public int getNumberOfNucleotide(char nucleotideSymbol) throws IllegalAlphabetException {
        if (!alphabet.isValidNucleotideSymbol(nucleotideSymbol))
            throw new IllegalAlphabetException("Entered an illegal nucleotide symbol");
        int count = 0;
        for (char element : nucleotideSequence.toCharArray()) {
            if (element == nucleotideSymbol) count++;
        }
        return count;
    }


    /**
     * Return a map with as key each nucleotide and as value the number of occurences of the nucleotide in the sequence
     *
     * @return A map with as key each nucleotide and as value the number of occurences of the nucleotide in the sequence.
     */
    public Set<Map.Entry<Character, Integer>> getNumberForEachNucleotide() {
        Map<Character, Integer> symbols = new HashMap<>();
        for (char element : nucleotideSequence.toCharArray()) {
            if (symbols.containsKey(element)) symbols.put(element, symbols.get(element) + 1);
            else symbols.put(element, 1);
        }
        return symbols.entrySet();
    }

    /**
     * Return the nucleotides present in the sequence
     *
     * @return The nucleotides in the sequence.
     */
    public Set<Character> getNucleotides() {
        Set<Character> symbols = new TreeSet<>();
        for (char element : nucleotideSequence.toCharArray()) {
            symbols.add(element);
        }
        return symbols;
    }

    /**
     * Check if the given subsequence is a part of this nucleotide sequence
     *
     * @param subsequence The subsequence to check
     * @throws IllegalNucleotideSequenceException The given nucleotide sequence is not a valid nucleotide sequence for any biological sequence.
     */

    public boolean containsSubsequence(String subsequence) throws IllegalNucleotideSequenceException {
        return nucleotideSequence.contains(subsequence);
    }

    /**
     * return the complementary string of the given biological sequence
     *
     * @param sequence To biological sequence from which the complementary string needs to be returned.
     * @return From the given biological sequence get the nucleotide sequence. For each symbol (representing a nucleotide)
     * in the nucleotide sequence get the complementary symbol to construct the complementary nucleotide sequence.
     * Return a new biological sequence object containing the sequence identifier, the organism, and the complementary nucleotide sequence.
     * @throws IllegalAlphabetException           The nucleotide symbol is not a valid symbol for this Alphabet
     * @throws IllegalIdException                 The identifier is not a valid identifier
     * @throws IllegalNucleotideSequenceException The nucleotide sequence is not a valid nucleotide sequence for any biological sequence.
     * @throws IllegalBiologicalSequenceException ("The biological sequence cannot be null")
     *                                            The given biological sequence must be effective (i.e., cannot be null)
     */

    public static BiologicalSequence complementaryNucleotideSequenceOf(BiologicalSequence sequence)
            throws IllegalAlphabetException, IllegalIdException, IllegalNucleotideSequenceException, IllegalBiologicalSequenceException {
        if (sequence == null)
            throw new IllegalBiologicalSequenceException("The biological sequence cannot be null");
        String strand = sequence.getNucleotideSequence();
        char[] complementaryString = new char[strand.toCharArray().length];
        int index = 0;
        for (char nucleotide : strand.toUpperCase().toCharArray()) {
            for (Symbol symbol : sequence.getAlphabet().getSymbols()) {
                if (nucleotide == symbol.getActual()) complementaryString[index++] = symbol.getComplementary();
            }
        }
        return new BiologicalSequence(sequence.getId(), sequence.getOrganism(), new String(complementaryString), sequence.alphabet);
    }

    /**
     * Replace a subsequence by another subsequence given a start position in the original sequence, the length
     * of the subsequence, and the new subsequence.
     *
     * @param startPosition  The start position in the original biological sequence.
     * @param length         The length of the subsequence in the original sequence that has to be replaced.
     * @param newSubSequence The new subsequence that is inserted in the original biological sequence.
     * @return The new sequence, that includes the new subsequence.
     * |
     * @throws IllegalArgumentException("Illegal startposition")
     *                                            |
     * @throws IllegalArgumentException("Illegal combo of startposition and length")
     *                                            |
     * @throws IllegalNucleotideSequenceException The given nucleotide sequence is not a valid nucleotide sequence for any biological sequence.
     *                                            |
     * @throws IllegalArgumentException("Illegal length")
     *                                            |
     */
    public String mutateNucleotideSequence(int startPosition, int length, String newSubSequence) throws IllegalArgumentException, IllegalNucleotideSequenceException, IllegalAlphabetException {
        if (startPosition < 0 || startPosition > nucleotideSequence.length() - 1)
            throw new IllegalArgumentException("Illegal startpostion");
        if (length < 0 || length > nucleotideSequence.length()) throw new IllegalArgumentException("Illegal length");
        if (startPosition + length > nucleotideSequence.length())
            throw new IllegalArgumentException("Illegal combo of startposition and length");
        if (!alphabet.isValidNucleotideSequence(newSubSequence))
            throw new IllegalNucleotideSequenceException("New subsequence: " + newSubSequence + " is not a valid subsequence");

        return String.format("%s%s%s",
                nucleotideSequence.substring(0, startPosition),
                newSubSequence,
                nucleotideSequence.substring(startPosition + length));
    }
}
