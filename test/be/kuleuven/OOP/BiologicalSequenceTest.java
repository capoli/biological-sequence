package be.kuleuven.OOP;

import be.kuleuven.OOP.exceptions.IllegalAlphabetException;
import be.kuleuven.OOP.exceptions.IllegalBiologicalSequenceException;
import be.kuleuven.OOP.exceptions.IllegalIdException;
import be.kuleuven.OOP.exceptions.IllegalNucleotideSequenceException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

//TODO: Check if all necessary tests are included
public class BiologicalSequenceTest {

    private BiologicalSequence dnasequence1, dnasequence2, dnasequence3;
    private Alphabet dnaAlphabet = new DnaAlphabet();

    @Before
    public void setUp() throws Exception {
        dnasequence1 = new BiologicalSequence("GLOBIN", "MOUSE", "ACTGG", dnaAlphabet);
        dnasequence2 = new BiologicalSequence("GLAPROTEIN", "CHICKEN", "ACTGGN", dnaAlphabet);
        dnasequence3 = new BiologicalSequence("ALB", "HUMAN", "ACTGGN", dnaAlphabet);
    }

    @Test
    public void testMostExtendedConstructor_LegalCase() throws IllegalAlphabetException, IllegalIdException, IllegalNucleotideSequenceException {
        BiologicalSequence biodnasequence = new BiologicalSequence("EPO", "HUMAN", "GGATTAATAC", dnaAlphabet);
        assertEquals("EPO", biodnasequence.getId());
        assertEquals("HUMAN", biodnasequence.getOrganism());
        assertEquals("GGATTAATAC", biodnasequence.getNucleotideSequence());
        assertEquals(dnaAlphabet, biodnasequence.getAlphabet());
    }

    @Test(expected = IllegalIdException.class)
    public void testMostExtendedConstructor_IllegalCaseNonLegalId() throws IllegalAlphabetException, IllegalIdException, IllegalNucleotideSequenceException {
        new BiologicalSequence("", "HUMAN", "GGACTG", dnaAlphabet);
    }

    @Test(expected = IllegalAlphabetException.class)
    public void testMostExtendedConstructor_IllegalCaseIllegalNucleotideSequence() throws IllegalAlphabetException, IllegalIdException, IllegalNucleotideSequenceException {
        new BiologicalSequence("EPO", "HUMAN", "GHJU", dnaAlphabet);
    }

    @Test
    public void testLeastExtentedConstructor_LegalCase() throws IllegalAlphabetException, IllegalIdException, IllegalNucleotideSequenceException {
        BiologicalSequence biodnasequence = new BiologicalSequence("ALB", "GGTATAC", dnaAlphabet);
        assertEquals("ALB", biodnasequence.getId());
        assertEquals("unknown", biodnasequence.getOrganism());
        assertEquals("GGTATAC", biodnasequence.getNucleotideSequence());
        assertEquals(dnaAlphabet, biodnasequence.getAlphabet());
    }

    @Test(expected = IllegalIdException.class)
    public void testLeastExtentedConstructor_IllegalCaseNonLegalId() throws IllegalAlphabetException, IllegalIdException, IllegalNucleotideSequenceException {
        new BiologicalSequence("", "GGACTG", dnaAlphabet);
    }

    @Test(expected = IllegalAlphabetException.class)
    public void testLeastExtendedConstructor_IllegalCaseIllegalNucleotideSequence() throws IllegalAlphabetException, IllegalIdException, IllegalNucleotideSequenceException {
        new BiologicalSequence("TROMB", "GOCTGS", dnaAlphabet);
    }

    @Test
    public void isValidId_TrueCase() {
        assertTrue(BiologicalSequence.isValidId("ALB"));
    }

    @Test
    public void isValidId_FalseCase() throws IllegalIdException{
        assertFalse(BiologicalSequence.isValidId(""));
    }

    @Test
    public void testGetNumberOfNucleotide_TrueCase() throws IllegalAlphabetException {
        assertEquals(1, dnasequence1.getNumberOfNucleotide('A'));
    }

    @Test(expected = IllegalAlphabetException.class)
    public void testGetNumberOfNucleotide__IllegalNucleotide() throws IllegalAlphabetException {
        dnasequence1.getNumberOfNucleotide('F');
    }

    @Test
    public void mutateNucleotideSequence__TrueCase() throws IllegalNucleotideSequenceException, IllegalAlphabetException {
        assertEquals("AATGG", dnasequence1.mutateNucleotideSequence(1, 2, "AT"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void mutateNucleotideSequence__IllegalStartposition() throws IllegalNucleotideSequenceException, IllegalAlphabetException {
        assertEquals("AGTGG", dnasequence1.mutateNucleotideSequence(-5, 2, "AT"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void mutateNucleotideSequence_lSequence_IllegalLength() throws IllegalNucleotideSequenceException, IllegalAlphabetException {
        assertEquals("AGTGG", dnasequence1.mutateNucleotideSequence(1, 8, "AT"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void mutateNucleotideSequence__IllegalCombinationStarpoistionAndLength() throws IllegalNucleotideSequenceException, IllegalAlphabetException {
        dnasequence1.mutateNucleotideSequence(8, 3, "AT");
    }

    @Test(expected = IllegalAlphabetException.class)
    public void mutateNucleotideSequence__IllegalDnaSequence() throws IllegalNucleotideSequenceException, IllegalAlphabetException {
        assertEquals("AGTGG", dnasequence1.mutateNucleotideSequence(1, 1, "HS"));
    }

    @Test
    public void complementaryNucleotideSequenceOf_LegalCase() throws IllegalAlphabetException, IllegalIdException, IllegalNucleotideSequenceException, IllegalBiologicalSequenceException {
        String actualString = "ACTGGN";
        String complemntaryString = "TGACCN";

        BiologicalSequence biologicalSequence = new BiologicalSequence("ALB", "HUMAN", actualString, dnaAlphabet);
        BiologicalSequence complementaryBiologicalSequence = BiologicalSequence.complementaryNucleotideSequenceOf(biologicalSequence);

        assertEquals(complemntaryString, complementaryBiologicalSequence.getNucleotideSequence());
    }

    @Test(expected = IllegalBiologicalSequenceException.class)
    public void complementaryNucleotideSequenceOf_IllegalCase() throws IllegalNucleotideSequenceException, IllegalBiologicalSequenceException, IllegalIdException, IllegalAlphabetException {
        BiologicalSequence dnasequence4 = BiologicalSequence.complementaryNucleotideSequenceOf(null);
    }


}
