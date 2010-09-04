import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;

public class NearQuery extends KTupleQuery{
    
    private int distance;

    public NearQuery(Query[] cqs, int dist){
        super(cqs);
        distance = dist;
    }    

    public LinkedList<Integer> invertedList(){
    	PriorityQueue<ArrayList<Integer>> postingsQueue = getPostingsQueue();
    }

	public Query cfold() {
		return null;
	}
	
	public String toString(){
		return "NEAR";
	}
}
