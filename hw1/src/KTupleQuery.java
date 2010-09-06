package src;

import java.util.ArrayList;
import java.util.PriorityQueue;

public abstract class KTupleQuery implements Query{
	
	private ArrayList<Query> childQueries;
	
	public KTupleQuery(ArrayList<Query> cqs){
		childQueries = cqs;
	}
	
	public PriorityQueue<ArrayList<Integer>> getPostingsQueue(){
		PriorityQueue<ArrayList<Integer>> postingsQueue = 
			new PriorityQueue<ArrayList<Integer>>(childQueries.size(), new ListComparator<ArrayList>());
		for(int i=0; i<childQueries.size(); i++){
			postingsQueue.offer(childQueries.get(i).invertedList());
		}
		return postingsQueue;
	}
	
	public ArrayList<Query> getChildQueries(){
		return childQueries;
	}

}
