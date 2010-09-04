import java.util.ArrayList;
import java.util.PriorityQueue;

public abstract class KTupleQuery implements Query{
	
	private Query[] childQueries;
	
	public KTupleQuery(Query[] cqs){
		childQueries = cqs;
	}
	
	public PriorityQueue<ArrayList<Integer>> getPostingsQueue(){
		PriorityQueue<ArrayList<Integer>> postingsQueue = 
			new PriorityQueue<ArrayList<Integer>>(childQueries.length, new ListComparator<ArrayList>());
		for(int i=0; i<childQueries.length; i++){
			postingsQueue.offer(childQueries[i].invertedList());
		}
		return postingsQueue;
	}

}
