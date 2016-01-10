/**
 *
 */
package be.kuleuven.OOP;

import java.util.HashSet;

import be.kuleuven.OOP.exceptions.QueryResultException;
import be.kuleuven.cs.som.annotate.*;

import java.util.Observable;
import java.util.Observer;
import java.util.Set;

/**
 * A class representing query results involving sequence identifiers of biological sequences.
 * The query result objects are observers of the sequence repositories to account for consistency of
 * the association with sequence repository.
 *
 * @author delphinecappelle
 * @invar Each query result must have proper sequence identifiers.
 * | hasProperIds()
 */
public class QueryResult implements Observer {

    /**
     * Initialize this new query result with no biological sequences attached to it.
     *
     * @param idList A set containing different sequence identifiers of biological sequences.
     * @throws QueryResultException ("idList cannot be null")
     *                              The given idList must be effective (i.e., cannot be null).
     */
    @Raw
    public QueryResult(Set<String> idList) throws QueryResultException {
        if (idList == null) throw new QueryResultException("idList cannot be null");
        queryResult = idList;
    }

    /**
     * Check whether this query result is already terminated.
     */
    @Basic
    @Raw
    public boolean isTerminated() {
        return this.isTerminated;
    }


    /**
     * Terminate this query result if it isn't terminated yet.
     *
     * @post This query result is terminated.
     * | new.isTerminated()
     * @effect Initialize this.queryResult with a new empty set to remove all memory links from this class.
     * | this.queryResult = new HashSet<>();
     * | this.isTerminated = true;
     */
    public void terminate() {
        if (!isTerminated()) {
            this.queryResult = new HashSet<>();
        }
        this.isTerminated = true;
    }

    /**
     * Variable registering whether or not this Query result has been
     * terminated.
     */
    private boolean isTerminated = false;

    /**
     * Set collecting references to biological sequences (i.e., sequence identifiers) attached
     * to this query result.
     */
    private Set<String> queryResult = new HashSet<String>();

    @Basic
    public Set<String> getQueryResult() {
        return this.queryResult;
    }


    /**
     * When a change occurs in observable (i.e., sequence repository), update the query results listening
     * to the changes done in the observable.
     */
    @Override
    public void update(Observable o, Object sequenceId) {
        if (sequenceId instanceof String) {
            queryResult.remove(sequenceId);
        }
    }
}
