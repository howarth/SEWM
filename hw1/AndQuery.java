import java.util.ArrayList;
import java.util.PriorityQueue;

public class AndQuery extends KTupleQuery {

    public AndQuery(Query[] cqs){
        super(cqs); 
    }
    
    public Query cfold(){
    	
    }

    public ArrayList<Integer> invertedList(){
        PriorityQueue<ArrayList<Integer>> postingsQueue = getPostingsQueue();
    }
    
    public String toString(){
    	return "AND";
    }
    
}
