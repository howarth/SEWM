package src;

import java.io.IOException;
import java.util.ArrayList;

public class TermQuery implements Query {
    
    private String term; 
    
    public TermQuery(String t){
        term = t;
    }
    

	public Query cfold() {
		return this;
	}

	public InvertedList invertedList() throws IOException {
		return InvertedListParser.getInvertedList(term);
	}
	
    public String toString(){
    	return term;
    }

}
