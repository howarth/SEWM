package src;

import java.util.ArrayList;

public class TermQuery implements Query {
    
    private String term; 
    
    public TermQuery(String t){
        term = t;
    }
    

	public Query cfold() {
		return this;
	}

	public ArrayList<Integer> invertedList() {
		// TODO Auto-generated method stub
		return null;
	}
	
    public String toString(){
    	return term;
    }

}
