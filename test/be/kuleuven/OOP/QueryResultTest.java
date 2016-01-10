package be.kuleuven.OOP;

import be.kuleuven.OOP.exceptions.QueryResultException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;

//TODO: Check if all necessary tests are included

public class QueryResultTest {

    private Set<String> idList = new HashSet<>();

    @Before
    public void setUp() {
        idList.add("first");
        idList.add("second");
        idList.add("third");
    }

    @Test
    public void testConstructorSetProperIdList() throws QueryResultException {
        QueryResult queryResult = new QueryResult(idList);
        assertEquals(idList, queryResult.getQueryResult());
    }

    @Test(expected = QueryResultException.class)
    public void testConstructorIdListIsNull() throws QueryResultException {
        QueryResult queryResult = new QueryResult(null);
    }
}
