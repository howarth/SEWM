package src;

import java.util.ArrayList;
import java.util.PriorityQueue;

public class OrQuery extends KTupleQuery{
    
    public OrQuery(ArrayList<Query> cqs){
        super(cqs);
    }

	public Query cfold() {
		ArrayList<Query> childQueries = getChildQueries();
		
		if(childQueries.size() == 1){
			return childQueries.get(0).cfold();
		}
		
		for(int i=0; i<childQueries.size(); i++){
			 childQueries.set(i, childQueries.get(i).cfold());
			 if(childQueries.get(i) instanceof OrQuery){
				 childQueries.addAll(((OrQuery) childQueries.remove(i)).getChildQueries());
				 i--;
			 }
		}
		return new OrQuery(childQueries);
	}
	
    public ArrayList<Integer> invertedList(){
    	PriorityQueue<ArrayList<Integer>> postingsQueue = getPostingsQueue();
    	return null;
    }
	
	public String toString(){
		String str = "#OR(";
		for(Query q : getChildQueries()){
			str += q.toString() + " ";
		}
		
		return str + ")";
	}
}
