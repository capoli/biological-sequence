/**
 *
 */
package be.kuleuven.OOP;


import be.kuleuven.cs.som.annotate.*;
import be.kuleuven.OOP.exceptions.*;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Observable;
import java.util.Set;

/**
 * A class for dealing with sequence repositories involving biological sequences and query results.
 * The sequence repositories represent observable objects.
 *
 * @author delphinecappelle
 * @version 1.0
 * @invar The biological sequences associated with each sequence repository must be proper
 * biological sequences for that sequence repository.
 * | hasProperBiologicalSequences()
 */
public class SequenceRepository extends Observable {

    /**
     * Initialize this new sequence repository as a non-terminated sequence repository with no biological sequences
     *
     * @post No biological sequences are stored in this new sequence repository.
     */
    @Raw
    public SequenceRepository() {
    }

    /**
     * Check whether this sequence repository is already terminated.
     */
    @Basic
    @Raw
    public boolean isTerminated() {
        return this.isTerminated;
    }

    /**
     * Terminate this sequence repository.
     *
     * @post This sequence repository is terminated.
     * | new.isTerminated()
     * @post All biological sequences belonging to this sequence repository upon entry, have been terminated.
     * | for each biological sequence in BiologicalSequence:
     * |   if (this.hasAsBiologicalSequence(biologicalSequence))
     * |     then ((new biologicalSequence).isTerminated())
     */
    public void terminate() {
        if (!isTerminated) {
            biologicalSequences = new HashMap<>();
        }
        isTerminated = true;
    }

    /**
     * Variable registering whether or not this sequence repository has been terminated.
     */
    private boolean isTerminated = false;

    /**
     * Check whether this sequence repository has the given biological sequence as one
     * of its biological sequences.
     *
     * @param biologicalSequence The biological sequence to check.
     */
    @Basic
    @Raw
    public boolean hasAsBiologicalSequence(@Raw BiologicalSequence biologicalSequence) {
        try {
            return biologicalSequences.get(biologicalSequence.getId()) != null;
        } catch (NullPointerException exc) {
            // Because the given biological sequence is raw, it is possible that
            // it does not yet reference an effective sequence identifier.
            assert (biologicalSequence == null) || (biologicalSequence.getId() == null);
            return false;
        }
    }

    /**
     * Check whether this sequence repository can have the given biological sequence
     * as one of its biological sequences.
     *
     * @param biologicalSequence The biological sequence to check.
     * @return True if and only if the given biological sequence is effective and that
     * biological sequence is not already stored in this sequence repository.
     * | result ==
     * |   (biologicalSequence != null) &&
     * |   (! biologicalSequences.containsKey(biologicalSequence.getId()))
     */
    @Raw
    public boolean canHaveAsBiologicalSequence(BiologicalSequence biologicalSequence) {
        return (biologicalSequence != null) && (!biologicalSequences.containsKey(biologicalSequence.getId()));
    }

    /**
     * Check whether this sequence repository has proper biological sequences.
     *
     * @return True if and only if for all biological sequences in this sequence repository,
     * this sequence repository can have that biological sequence as one of its
     * biological sequences, that biological sequence is the (only) biological sequence with this
     * sequence identifier in this sequence repository.
     * | for each biologicalSequence in Biological Sequence:
     * |   if (hasAsBiologicalSequence(biologicalSequence))
     * |     then canHaveAsiologicalSequence(biologicalSequence) &&
     * |          (getBiologicalSequenceOf(biologicalSequence.getId()) == biologicalSequence)
     */
    public boolean hasProperBiologicalSequence() throws IllegalIdException {
        for (BiologicalSequence biologicalSequence : biologicalSequences.values()) {
            if (!canHaveAsBiologicalSequence(biologicalSequence))
                return false;
            if (getBiologicalSequenceOf(biologicalSequence.getId()) != biologicalSequence)
                return false;

        }
        return true;
    }

    /**
     * Return the number of biological sequences in this sequence repository.
     *
     * @return The total number of biological sequences stored in this sequence repository.
     * | result == BiologicalSequences.size()
     */
    public int getNbBiologicalSequences() {
        return biologicalSequences.size();
    }

    /**
     * Return the biological sequence of the given sequence identifier, if any, registered in this sequence repository.
     *
     * @param id The id to check.
     * @return The resulting biological sequence is effective, if and only if this sequence repository
     * has a biological sequences involving the given id.
     * | (result != null) ==
     * |   (for some biologicalSequence in BiologicalSequence:
     * |     (biologicalSequence.getId() == id) &&
     * |      this.hasAsBiologicalSequence(biologicalSequence) )
     * @throws IllegalIdException ("The id cannot be null")
     * @throws IllegalIdException ("The id cannot be empty")
     */
    @Basic
    public BiologicalSequence getBiologicalSequenceOf(String id) throws IllegalIdException {
        if (id == null) throw new IllegalIdException("The id cannot be null");
        if (id.isEmpty()) throw new IllegalIdException("The id cannot be empty");
        if (!biologicalSequences.containsKey(id)) return null;
        return biologicalSequences.get(id);
    }

    /**
     * Check whether this sequence repository has a biological sequence of the given sequence identifier.
     *
     * @param id The id to check.
     * @return True if this sequence repository includes a biological sequences of the
     * given id; false otherwise.
     * | result == (getBiologicalSequenceOf(id) != null)
     */
    public boolean hasBiologicalSequenceOf(String id) throws IllegalIdException {
        return (getBiologicalSequenceOf(id) != null);
    }

    /**
     * Add the given biological sequence to the set of biological sequences stored in this
     * sequence repository.
     *
     * @param biologicalSequence The biological sequence to be added.
     * @throws The  given biological sequence is not effective.
     *              | biologicalSequence == null
     * @throws The  given biological sequence is not referencing an effective sequence identifier.
     *              | biologicalSequence.getId() == null
     * @throws This sequence repository already includes a biological sequence of the
     *              sequence identifier involved in the given biological sequence.
     *              | hasBiologicalSequenceOf(biologicalSequence.getId())
     * @post The given biological sequence is registered as one of the
     * biological sequences for this sequence repository.
     * | new.hasAsBiologicalSequence(biologicalSequence)
     */
    void addBiologicalSequence(@Raw BiologicalSequence biologicalSequence) throws IllegalBiologicalSequenceException, IllegalIdException {
        if (biologicalSequence == null)
            throw new IllegalBiologicalSequenceException("The biological sequence cannot be null");
        if (biologicalSequence.getId() == null)
            throw new IllegalIdException("The id cannot be null");
        if (hasBiologicalSequenceOf(biologicalSequence.getId()))
            throw new IllegalIdException("The id is already stored in this sequence repository");
        biologicalSequences.put(biologicalSequence.getId(), biologicalSequence);
    }

    /**
     * Remove the given biological sequence from the biological sequences registered in
     * this sequence repository.
     *
     * @param biologicalSequence The biological sequence to be removed.
     * @throws IllegalBiologicalSequenceException ("The biological sequence is not a sequence of this sequence repository")
     *                                            The given biological sequence is not registered as one of the biological sequences
     *                                            for this sequence repository.
     *                                            | ! hasAsBiologicalSequence(biologicalSequence)
     * @post This sequence repository no longer has the given biological sequence as one
     * of its biological sequences.
     * | ! new.hasAsBiologicalSequence(this)
     */
    void removeBiologicalSequence(@Raw BiologicalSequence biologicalSequence) throws IllegalBiologicalSequenceException {
        if (!hasAsBiologicalSequence(biologicalSequence))
            throw new IllegalBiologicalSequenceException("The biological sequence is not a sequence of this sequence repository");
        biologicalSequences.remove(biologicalSequence.getId());
        setChanged();
        notifyObservers(biologicalSequence);
    }

    /**
     * Variable referencing a map collecting all the sequences stored in this sequence repository
     *
     * @invar The referenced map is effective.
     * | biologicalSequences != null
     * @invar Each key registered in the map is an effective string
     * | for each key in biologicalSequences.keySet():
     * | (key != null)
     * @invar Each value associated with a key in the map is an effective, non-terminated biological sequence
     * involving this sequence repository and involving a sequence identifier identical to the association key.
     * | for each key in biologicalSequences.keySet():
     * | (biologicalSequences.get(key) != null) &&
     * | (! biologicalSequences.get(key).isTerminated()) &&
     * | (biologicalSequences.get(key).getSequenceRepository() == this) &&
     * | (biologicalSequences.get(key).getId().equals(key))
     */
    private Map<String, BiologicalSequence> biologicalSequences = new HashMap<String, BiologicalSequence>();

    /**
     * Return a QueryResult object that contains the sequence identifiers of all biological sequences
     * stored in this sequence repository for a given alphabet
     *
     * @param typeOfAlphabet the type of alphabet from which the biological sequence identiefiers must be retrieved
     */
    public QueryResult getAllIds(Class<? extends Alphabet> typeOfAlphabet) throws QueryResultException, IllegalAlphabetException {
        Set<String> idList = new HashSet<>();

        for (String key : biologicalSequences.keySet()) {
            if (typeOfAlphabet.isInstance(biologicalSequences.get(key).getAlphabet()))
                idList.add(key);
        }

        return createQueryResultAndAddAsObserver(idList);
    }

    /**
     * Return a QueryResult object that contains the sequence identifiers of all biological sequences
     * from the given organism stored in this sequence repository for a given alphabet
     *
     * @param organism the organism from which the biological sequence identiefiers must be retrieved
     * @param typeOfAlphabet the type of alphabet from which the biological sequence identiefiers must be retrieved
     *
     * @throws IllegalAlphabetException
     * @throws QueryResultException
     */
    public QueryResult getIdsForOrganism(String organism, Class<? extends Alphabet> typeOfAlphabet) throws IllegalAlphabetException, QueryResultException {
        Set<String> idList = new HashSet<>();

        for (String key : this.biologicalSequences.keySet()) {
            BiologicalSequence biologicalSequence = this.biologicalSequences.get(key);
            if ((biologicalSequence.getOrganism()).equals(organism) && typeOfAlphabet.isInstance(biologicalSequence.getAlphabet()))
                idList.add(biologicalSequence.getId());
        }

        return createQueryResultAndAddAsObserver(idList);
    }

    /**
     * Return a QueryResult object that contains the sequence identifiers of all biological sequences
     * from the given subsequence stored in this sequence repository for a given alphabet
     *
     * @param subsequence the subsequence from which the biological sequence identiefiers must be retrieved
     * @param typeOfAlphabet the type of alphabet from which the biological sequence identiefiers must be retrieved
     *
     * @throws QueryResultException
     * @throws IllegalNucleotideSequenceException
     * @throws IllegalAlphabetException
     */
    public QueryResult getIdsForSubsequence(String subsequence, Class<? extends Alphabet> typeOfAlphabet) throws QueryResultException, IllegalNucleotideSequenceException, IllegalAlphabetException {
        Set<String> idList = new HashSet<>();

        for (String key : this.biologicalSequences.keySet()) {
            BiologicalSequence biologicalSequence = this.biologicalSequences.get(key);
            if (biologicalSequence.containsSubsequence(subsequence) && typeOfAlphabet.isInstance(biologicalSequence.getAlphabet()))
                idList.add(biologicalSequence.getId());
        }

        return createQueryResultAndAddAsObserver(idList);
    }

    /**
     * Initialize a QueryResult object as a list of the sequence identifiers and add the QueryResult as observer of this
     * sequence repository.
     *
     * @param idList the list of biological sequence identifiers for which a QueryResult object will be created
     *
     * @throws QueryResultException
     */
    private QueryResult createQueryResultAndAddAsObserver(Set<String> idList) throws QueryResultException {
        QueryResult queryResult = new QueryResult(idList);
        this.addObserver(queryResult);
        return queryResult;
    }

    /**
     * Terminate the given query result as it is no longer needed.
     *
     * @param queryResult the QueryResult object that need to be terminated
     */
    public void terminateQueryResult(QueryResult queryResult) {
        deleteObserver(queryResult);
        queryResult.terminate();
    }
}

