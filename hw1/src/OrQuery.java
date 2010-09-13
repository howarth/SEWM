package src;

import java.io.IOException;
import java.util.ArrayList;
import java.util.PriorityQueue;

public class OrQuery extends CombineScoreQuery{
    
    public OrQuery(ArrayList<Query> cqs){
        super(cqs);
    }

	public Query cfold() {
		ArrayList<Query> childQueries = getChildQueries();
		
		System.out.println(childQueries);
		if(childQueries.size() == 1){

			return childQueries.get(0).cfold();
		}
		
		for(int i=0; i<childQueries.size(); i++){
			 childQueries.set(i, childQueries.get(i).cfold());
			 if(childQueries.get(i) instanceof OrQuery){
				 childQueries.addAll(((OrQuery) childQueries.remove(i)).getChildQueries());
				 i--;
			 }
		}
		return new OrQuery(childQueries);
	}
	
    public ScoreList invertedList() throws IOException{
    	PriorityQueue<ScoreList> postingsQueue = getPostingsQueue();
    	
    	ScoreList list;
    	
    	while(postingsQueue.size() > 1){
    		ScoreList smaller = postingsQueue.poll();
    		ScoreList larger = postingsQueue.poll();
    		
    		list = new ScoreList(smaller.size() + larger.size());
    		
    		
    		int smallIndex = 0;
    		int largeIndex = 0;
    		int df = 0;
    		
    		list.add(null);
    		
    		while(smallIndex != smaller.df() && largeIndex != larger.df()){
    			df++;
    			if(smaller.getDocId(smallIndex) < larger.getDocId(largeIndex)){
    				list.add(smaller.getDocId(smallIndex));
    				list.add(1);
    				smallIndex++;
    			} else if (smaller.getDocId(smallIndex) == larger.getDocId(largeIndex)){
    				list.add(smaller.getDocId(smallIndex));
    				list.add(1);
    				smallIndex++;
    				largeIndex++;
    			} else {
    				list.add(larger.getDocId(largeIndex));
    				list.add(1);
    				largeIndex++;
    			}
    		}
    		
    		for(int i=smallIndex; i<smaller.df(); i++){
    			list.add(smaller.getDocId(i));
    			list.add(1);
    		}
    		for(int i=largeIndex; i<larger.df(); i++){
    			list.add(larger.getDocId(i));
    			list.add(1);
    		}
    		
    		list.set(0, df);
    		postingsQueue.offer(list);
    	}
    	return postingsQueue.poll();
    }
	
	public String toString(){
		String str = "#OR(";
		for(Query q : getChildQueries()){
			str += q.toString() + " ";
		}
		
		return str + ")";
	}
}
