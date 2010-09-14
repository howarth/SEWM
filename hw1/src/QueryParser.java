package src;

import java.util.ArrayList;
import java.util.HashSet;

public class QueryParser {
	
	private String query;
	private int position;
	
	private HashSet<String> stopWords = new HashSet<String>();
	
	public QueryParser(String q){
		newQuery(q);
		/* This is bad. */
		stopWords.add("a");
		stopWords.add("an");
		stopWords.add("and");
		stopWords.add("are");
		stopWords.add("as");
		stopWords.add("at");
		stopWords.add("for");
		stopWords.add("i");
		stopWords.add("if");
		stopWords.add("in");
		stopWords.add("is");
		stopWords.add("it");
		stopWords.add("of");
		stopWords.add("on");
		stopWords.add("so");
		stopWords.add("that");
		stopWords.add("the");
		stopWords.add("to");
	}
	
	public void newQuery(String q){
		query = "#OR("+q+" )";
		/* Pad all right parenthesis for now.*/
		query = query.replaceAll("\\)", " )");
		position = 0;
	}
	
	public Query getQuery(){
		return parseOrQuery();
	}

	public ArrayList<Query> getQueryList(){
		ArrayList<Query> queries = new ArrayList<Query>();
		int type;
		String term;
		while((type = getNextType()) != -1){
			switch(type){
				case 0:
					if( (term = nextTerm()) != null)
						queries.add(parseTermQuery(term));
					break;
				case 1:
					queries.add(parseOrQuery());
					break;
				case 2:
					queries.add(parseAndQuery());
					break;
				case 3:
					queries.add(parseNearQuery());
					break;
			}
		}
		
		return queries;
	}
	
	public Query parseTermQuery(String term){
		return new TermQuery(term);
	}
	
	public String nextTerm(){
		int end_of_term = nextSpacePos() - 1;
		
		if(end_of_term < 0){
			end_of_term = nextParensPos() - 1;
		}

		String term = query.substring(position, end_of_term + 1);

		if(term.split("\\.").length == 1){
			term += ".body";
		}
		
		position = end_of_term + 1;

		if(stopWords.contains(term.split("\\.")[0]))
			return null;

		return term;
	}
	
	public Query parseOrQuery(){
		position += 3;
		return new OrQuery(getQueryList());
	}
	
	public Query parseAndQuery(){
		position += 4;
		return new AndQuery(getQueryList());
	}
	
	public Query parseNearQuery(){
		int parens_pos = query.indexOf('(', position);

		int distance = Integer.parseInt(query.substring(position+6, parens_pos));
		position = parens_pos + 1;
		
		String first = nextTerm();
		getNextType();
		String second = nextTerm();
		return new NearQuery(new TermQuery(first), new TermQuery(second), distance);
	}
	/*
	 * 0  = term
	 * 1  = OR
	 * 2  = AND
	 * 3  = NEAR
	 * -1 = END
	 */
	public int getNextType(){
		
		while(query.charAt(++position) == ' '){}
		
		switch(query.charAt(position)){
			case ')':
				return -1;
			case '#':
				switch(query.charAt(position+1)){
					case 'O': // OR
						return 1;
					case 'A': // AND
						return 2;
					case 'N': // NEAR
						return 3;
				}
				break;
			default:
				return 0;
		}
		return -2; // Should never get here.
	}
	
	/* Helper methods */
	public int nextSpacePos(){
		return query.indexOf(' ', position);
	}
	
	public int nextParensPos(){
		return query.indexOf(')', position);
	}
	
}
