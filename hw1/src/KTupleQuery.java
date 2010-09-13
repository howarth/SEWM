package src;

import java.util.ArrayList;
import java.util.PriorityQueue;

public abstract class KTupleQuery implements Query{
	
	protected ArrayList<Query> childQueries;
	
	public KTupleQuery(ArrayList<Query> cqs){
		childQueries = cqs;
	}
	
	public ArrayList<Query> getChildQueries(){
		return childQueries;
	}

}
