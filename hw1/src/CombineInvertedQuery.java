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
		
		for(int i=0; i<childQueries.size(); i++){
			postingsQueue.offer((InvertedList) childQueries.get(i).invertedList());
		}
		return postingsQueue;
	}

}
