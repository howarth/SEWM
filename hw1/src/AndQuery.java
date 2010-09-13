package src;

import java.io.IOException;
import java.util.ArrayList;
import java.util.PriorityQueue;

public class AndQuery extends CombineScoreQuery {

    public AndQuery(ArrayList<Query> cqs){
        super(cqs); 
    }
    
    public Query cfold(){
		ArrayList<Query> childQueries = getChildQueries();
		
		if(childQueries.size() == 1){
			if(!(childQueries.get(0) instanceof TermQuery))
				return childQueries.get(0).cfold();
		}
		
		for(int i=0; i<childQueries.size(); i++){
			 childQueries.set(i, childQueries.get(i).cfold());
			 if(childQueries.get(i) instanceof AndQuery){
				 childQueries.addAll(((AndQuery) childQueries.remove(i)).getChildQueries());
				 i--;
			 }
		}
		return new AndQuery(childQueries);
    }

    public ScoreList invertedList() throws IOException{
        PriorityQueue<ScoreList> postingsQueue = getPostingsQueue();
        
        ScoreList list;
        
        while(postingsQueue.size() > 1){
        	ScoreList smaller = postingsQueue.poll();
        	ScoreList larger = postingsQueue.poll();
        	
        	list = new ScoreList(smaller.size());
        	
        	int smallIndex = 0;
        	int largeIndex = 0;
        	int df = 0;
        	int cmp;
        	
        	list.add(null);
        	
        	while(smallIndex != smaller.df() && largeIndex != larger.df()){
        		cmp = smaller.getDocId(smallIndex) 
        			- larger.getDocId(largeIndex);
        		if(cmp == 0){
        			df++;
        			list.add(smaller.getDocId(smallIndex));
        			list.add(1);
        			smallIndex++;
        			largeIndex++;
        		} else if (cmp < 0) {
        			smallIndex++;
        		} else {
        			largeIndex++;
        		}
        	}
        	
        	list.set(0, df);
        	postingsQueue.offer(list);
        }
        return postingsQueue.poll();
    }
    
    public String toString(){
		String str = "#AND(";
		for(Query q : getChildQueries()){
			str += q.toString() + " ";
		}
		
		return str + ")";
    }
    
}
