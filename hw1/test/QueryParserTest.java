package test;
import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import src.QueryParser;

public class QueryParserTest {
	static QueryParser _qp;
	
	@Before
	public void setUp() throws Exception {
		_qp = new QueryParser(null);
	}
	
	@Test
	public void noOperatorTest(){
		testQuery("one", "#OR(one.body )");
		testQuery("one.title two three", "#OR(one.title two.body three.body )");
	}
	
	@Test
	public void cFoldOnlyOrTest(){
		testQuery("#OR( #OR(one.title two ) )", "#OR(one.title two.body )");
		testQuery("#OR( one two #OR(one))", "#OR(one.body two.body one.body )");
		testQuery("#OR(one two #OR( three four #OR( five six) seven ))",
				  "#OR(one.body two.body three.body four.body seven.body five.body six.body )");
	}
	
	@Test
	public void cFoldOnlyAndTest(){
		testQuery("#AND(one.title)", "#AND(one.title )");
		testQuery("#AND(one.body #AND(two #AND(three.title) four))",
				  "#AND(one.body two.body four.body three.title )");
	}
	
	@Test
	public void cFoldOnlyNearTest(){
		testQuery("#NEAR/2(one two)", "#NEAR/2(one.body two.body )");
		testQuery("#NEAR/245(one two)", "#NEAR/245(one.body two.body )");
	}
	
	@Test public void combinedTest(){
		testQuery("one.title two #AND(sup pup.title yup) #OR(three #AND(four #AND(five)))",
				  "#OR(one.title two.body #AND(sup.body pup.title yup.body ) three.body #AND(four.body five.body ) )");
		testQuery("obama #NEAR/4(family tree )", "#OR(obama.body #NEAR/4(family.body tree.body ) )");
	}
	
	/* Helper methods */
	public void testQuery(String query, String expected){
		_qp.newQuery(query);
		assertEquals(expected, _qp.getQuery().cfold().toString());
	}
}
