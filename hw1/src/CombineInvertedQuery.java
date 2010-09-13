package src;

import java.io.IOException;
import java.util.ArrayList;
import java.util.PriorityQueue;

public abstract class CombineInvertedQuery extends KTupleQuery{

	public CombineInvertedQuery(ArrayList<Query> cqs) {
		super(cqs);
	}
	
	public PriorityQueue<InvertedList> getPostingsQueue() throws IOException{
		PriorityQueue<InvertedList> postingsQueue = 
			new PriorityQueue<InvertedList>(childQueries.size(), new ListComparator());
		
		InvertedList invlist;
		for(int i=0; i<childQueries.size(); i++){
			invlist = (InvertedList) childQueries.get(i).invertedList();
			if(invlist != null){
				postingsQueue.offer((InvertedList) childQueries.get(i).invertedList());
			}
		}
		return postingsQueue;
	}

}
