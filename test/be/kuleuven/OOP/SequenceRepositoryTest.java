/**
 *
 */
package be.kuleuven.OOP;

import be.kuleuven.OOP.exceptions.*;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author delphinecappelle
 */
//TODO: Check if all necessary tests are included (I think some are missing ;-))
public class SequenceRepositoryTest {

    private SequenceRepository repository;

    @Before
    public void setUp() throws IllegalNucleotideSequenceException {
        repository = new SequenceRepository();
        try {
            repository.addBiologicalSequence(new BiologicalSequence("DNAGLAPROTEIN", "human", "ACGTN", new DnaAlphabet()));
            repository.addBiologicalSequence(new BiologicalSequence("DNAGLAPROTEIN2", "rabbit", "ACGTNTT", new DnaAlphabet()));
            repository.addBiologicalSequence(new BiologicalSequence("DNAGLAPROTEIN3", "chicken", "ACGTNTGC", new DnaAlphabet()));

            repository.addBiologicalSequence(new BiologicalSequence("RNAGLAPROTEIN", "human", "ACGUN", new RnaAlphabet()));
            repository.addBiologicalSequence(new BiologicalSequence("RNAGLAPROTEIN1", "rabbit", "ACGUNUU", new RnaAlphabet()));
            repository.addBiologicalSequence(new BiologicalSequence("RNAGLAPROTEIN2", "chicken", "ACGUNCGU", new RnaAlphabet()));
        } catch (IllegalBiologicalSequenceException | IllegalIdException | IllegalAlphabetException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testCanAddDnaBiologicalSequence() {
        try {
            BiologicalSequence biologicalDnaSequence = new BiologicalSequence("NEWDNASTRING", "BEER", "ATCG", new DnaAlphabet());

            int oldDnaSizeRepo = repository.getAllIds(DnaAlphabet.class).getQueryResult().size();

            repository.addBiologicalSequence(biologicalDnaSequence);

            int newDnaSizeRepo = repository.getAllIds(DnaAlphabet.class).getQueryResult().size();

            assertEquals("Expected size of dna repository dooesn't match", oldDnaSizeRepo + 1, newDnaSizeRepo);
        } catch (IllegalIdException | IllegalNucleotideSequenceException | IllegalAlphabetException | QueryResultException | IllegalBiologicalSequenceException iie) {
            iie.printStackTrace();
        }
    }

    @Test
    public void testCanAddRnaBiologicalSequence() {
        try {
            BiologicalSequence biologicalRnaSequence = new BiologicalSequence("NEWRNASTRING", "BEER", "AUCG", new RnaAlphabet());

            int oldRnaSizeRepo = repository.getAllIds(RnaAlphabet.class).getQueryResult().size();

            repository.addBiologicalSequence(biologicalRnaSequence);

            int newRnaSizeRepo = repository.getAllIds(RnaAlphabet.class).getQueryResult().size();

            assertEquals("Expected size of rna repository doesn't match", oldRnaSizeRepo + 1, newRnaSizeRepo);
        } catch (IllegalIdException | IllegalNucleotideSequenceException | IllegalAlphabetException | QueryResultException | IllegalBiologicalSequenceException iie) {
            iie.printStackTrace();
        }
    }

    @Test
    public void testCanRemoveDnaBiologicalSequence() {
        try {
            BiologicalSequence biologicalDnaSequence = new BiologicalSequence("NEWDNASTRING", "BEER", "ATCG", new DnaAlphabet());

            repository.addBiologicalSequence(biologicalDnaSequence);

            int oldDnaSizeRepo = repository.getAllIds(DnaAlphabet.class).getQueryResult().size();

            repository.removeBiologicalSequence(biologicalDnaSequence);

            int newDnaSizeRepo = repository.getAllIds(DnaAlphabet.class).getQueryResult().size();

            assertEquals("Expected size of dna repository doesn't match", oldDnaSizeRepo - 1, newDnaSizeRepo);
        } catch (IllegalIdException | IllegalNucleotideSequenceException | IllegalAlphabetException | QueryResultException | IllegalBiologicalSequenceException iie) {
            iie.printStackTrace();
        }
    }

    @Test
    public void testCanRemoveRnaBiologicalSequence() {
        try {
            BiologicalSequence biologicalRnaSequence = new BiologicalSequence("NEWRNASTRING", "BEER", "AUCG", new RnaAlphabet());

            repository.addBiologicalSequence(biologicalRnaSequence);

            int oldRnaSizeRepo = repository.getAllIds(RnaAlphabet.class).getQueryResult().size();

            repository.removeBiologicalSequence(biologicalRnaSequence);

            int newRnaSizeRepo = repository.getAllIds(RnaAlphabet.class).getQueryResult().size();

            assertEquals("Expected size of rna repository doesn't match", oldRnaSizeRepo - 1, newRnaSizeRepo);
        } catch (IllegalIdException | IllegalNucleotideSequenceException | IllegalAlphabetException | QueryResultException | IllegalBiologicalSequenceException iie) {
            iie.printStackTrace();
        }
    }

    @Test
    public void testCanGetAllDnaBiologicalSequences() {
        try {
            QueryResult allIds = repository.getAllIds(DnaAlphabet.class);
            assertEquals(3, allIds.getQueryResult().size());
        } catch (QueryResultException | IllegalAlphabetException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testCanGetAllDnaBiologicalSequencesWithGivenSubsequence() {
        try {
            QueryResult allIds = repository.getIdsForSubsequence("TT", DnaAlphabet.class);
            assertEquals(1, allIds.getQueryResult().size());
        } catch (QueryResultException | IllegalAlphabetException | IllegalNucleotideSequenceException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testCanGetAllDnaBiologicalSequencesWithGivenOrganism() {
        try {
            QueryResult allIds = repository.getIdsForOrganism("human", DnaAlphabet.class);
            assertEquals(1, allIds.getQueryResult().size());
        } catch (QueryResultException | IllegalAlphabetException e) {
            e.printStackTrace();
        }
    }


}
