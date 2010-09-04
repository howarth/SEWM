import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;

public class OrQuery extends KTupleQuery{
    
    public OrQuery(Query[] cqs){
        super(cqs);
    }

    public ArrayList<Integer> invertedList(){
    	PriorityQueue<ArrayList<Integer>> postingsQueue = getPostingsQueue();
    }

	public Query cfold() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public String toString(){
		return "OR";
	}
}
