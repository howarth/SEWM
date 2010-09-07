package src;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;

public class NearQuery extends KTupleQuery{
    
    private int distance;

    public NearQuery(ArrayList<Query> cqs, int dist){
        super(cqs);
        distance = dist;
    }    

    public ArrayList<Integer> invertedList(){
    	PriorityQueue<ArrayList<Integer>> postingsQueue = getPostingsQueue();
    	return null;
    }

	public Query cfold() {
		return this;
	}
	
	public String toString(){
		String str = "#NEAR/"+distance+"(";
		for(Query q : getChildQueries()){
			str += q.toString() + " ";
		}	
		return str + ")";
	}
}
