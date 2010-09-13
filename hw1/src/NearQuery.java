package src;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;

public class NearQuery extends BinaryQuery{
    
    private int distance;

    public NearQuery(Query first, Query second, int dist){
        super(first, second);
        distance = dist;
    }    

    public QueryList invertedList() throws IOException{
    	
    	return null;
    }

	public Query cfold() {
		return this;
	}
	
	public String toString(){
		String str = "#NEAR/"+distance+"(";
		str += first +" "+second;
		return str + " )";
	}
}
