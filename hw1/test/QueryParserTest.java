package test;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

import src.QueryParser;

public class QueryParserTest {

	@Test
	public void noOperatorTest(){
		QueryParser qp = new QueryParser("one two three");
		assertEquals(qp.getQuery().cfold().toString(), "#OR(one two three )");
	}
	
	@Test
	public void cFoldOnlyOrTest(){
		QueryParser qp3 = new QueryParser("#OR( #OR(one two ) )");
		assertEquals(qp3.getQuery().cfold().toString(), "#OR(one two )");
		QueryParser qp1 = new QueryParser("#OR( one two #OR( one ) )");
		assertEquals(qp1.getQuery().cfold().toString(), "#OR(one two one )");
		QueryParser qp2 = new QueryParser("#OR(one two #OR( three four #OR( five six) seven ) )");
		assertEquals(qp2.getQuery().cfold().toString(), "#OR(one two three four seven five six )");
	}
}
