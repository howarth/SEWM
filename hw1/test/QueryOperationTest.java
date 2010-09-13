package test;
import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.ArrayList;

import src.Query;

import org.junit.Before;
import org.junit.Test;

import src.QueryParser;

public class QueryOperationTest {
	
	static QueryParser _qp;
	
	@Before
	public void setUp() throws Exception{
		_qp = new QueryParser(null);
	}
	
	@Test
	public void testSmallOrQueries() throws Exception {
		testQuery("howarth family", new ArrayList<Integer>());	
	}
	
	/* Helper methods */
	public void testQuery(String query, ArrayList<Integer> expectedList) throws IOException{
		_qp.newQuery(query);
		Query q = _qp.getQuery();
		
		q.cfold();
		
		System.out.println(q.invertedList());
		assertEquals(true ,q.invertedList().equals(expectedList));
	}
}
