package src;

import java.util.ArrayList;

public class QueryParser {
	
	private String query;
	private int position;
	
	public QueryParser(String q){
		newQuery(q);
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
		while((type = getNextType()) != -1){
			switch(type){
				case 0:
					queries.add(parseTermQuery());
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
	
	public Query parseTermQuery(){
		int end_of_term = nextSpacePos() - 1;
		
		if(end_of_term < 0){
			end_of_term = nextParensPos() - 1;
		}

		String term = query.substring(position, end_of_term + 1);

		if(term.split("\\.").length == 1){
			term += ".body";
		}
		
		
		position = end_of_term;
		return new TermQuery(term);
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
		position = parens_pos;
		return new NearQuery(getQueryList(), distance);
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
