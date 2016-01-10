/**
 *
 */
package be.kuleuven.OOP;

import be.kuleuven.OOP.exceptions.IllegalAlphabetException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * @author delphinecappelle
 */
//TODO: Check if all necessary tests are included
//
public class AlphabetTest {

    private Alphabet alphabetdna;
    private Alphabet alphabetrna;

    @Before
    public void setUp() throws Exception {
        alphabetdna = new DnaAlphabet();
        alphabetrna = new RnaAlphabet();
    }

    @Test
    public final void isValidNucleotideSequence_TrueCase() throws IllegalAlphabetException {
        assertTrue(alphabetdna.isValidNucleotideSequence("GTAGGN"));
        assertTrue(alphabetrna.isValidNucleotideSequence("UGACN"));
    }

    @Test(expected = IllegalAlphabetException.class)
    public final void isValidNucleotideSequence_FalseCase() throws IllegalAlphabetException {
        assertFalse(alphabetdna.isValidNucleotideSequence("GMUBAA"));
        assertFalse(alphabetrna.isValidNucleotideSequence("TTAT"));
    }


    @Test
    public final void testIsValidNucleotideSymbol_TrueCase() throws IllegalAlphabetException {
        assertTrue(alphabetdna.isValidNucleotideSymbol('T'));
        assertTrue(alphabetrna.isValidNucleotideSymbol('U'));
    }

    @Test(expected = IllegalAlphabetException.class)
    public final void testIsValidNucleotideSymbol_FalseCase() throws IllegalAlphabetException {
        assertFalse(alphabetdna.isValidNucleotideSymbol('H'));
        assertFalse(alphabetrna.isValidNucleotideSymbol('T'));
    }
}
