package src;

import java.io.IOException;
import java.util.ArrayList;
import java.util.PriorityQueue;

public abstract class CombineScoreQuery extends KTupleQuery{

	public CombineScoreQuery(ArrayList<Query> cqs) {
		super(cqs);
	}
	
	public PriorityQueue<ScoreList> getPostingsQueue() throws IOException{
		Query childQuery;
		PriorityQueue<ScoreList> postingsQueue = 
			new PriorityQueue<ScoreList>(childQueries.size(), new ListComparator());
		
		for(int i=0; i<childQueries.size(); i++){
			childQuery = childQueries.get(i);
			if(childQuery instanceof TermQuery){
				postingsQueue.offer(ScoreList.convertInvertedList(childQuery.invertedList()));
			} else {
				postingsQueue.offer((ScoreList) childQuery.invertedList());
			}
		}
		
		return postingsQueue;
	}

}
