package src;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;

public class NearQuery extends BinaryQuery{
    
    private int distance;

    public NearQuery(Query first, Query second, int dist){
        super(first, second);
        distance = dist;
    }    

    public QueryList invertedList() throws IOException{
    	InvertedList firstList = (InvertedList) first.invertedList();
    	InvertedList secondList = (InvertedList) second.invertedList();
    	
    	InvertedList smaller, larger;
    	boolean firstSmaller = true;
    	
    	if(firstList.size() <= secondList.size()){
    		smaller = firstList;
    		larger = secondList;
    	} else {
    		smaller = secondList;
    		larger = firstList;
    		firstSmaller = false;
    	}
    	
    	ScoreList list = new ScoreList(smaller.size());
    	
    	int smallIndex = 0;
    	int largeIndex = 0;
    	int df = 0;
    	int cmp;
    	
    	list.add(null);
    	
    	smallIndex = smaller.nextDocPos();
    	largeIndex = larger.nextDocPos();
    	
    	int firstStart, firstIndex, firstTF, secondStart, secondIndex, secondTF;
    	    	
    	while(smallIndex < smaller.size() && largeIndex != larger.size()){
    		cmp = smaller.get(smallIndex) - larger.get(largeIndex);
    		if(cmp == 0){
    			if(firstSmaller){
    				firstTF = smaller.get(smallIndex+1);
    				firstStart = smallIndex;
    				firstIndex = smallIndex+2;
    				secondTF = larger.get(largeIndex+1);
    				secondStart = largeIndex;
    				secondIndex = largeIndex+2;
    			} else {
    				secondTF = smaller.get(smallIndex+1);
    				secondStart = smallIndex;
    				secondIndex = smallIndex+2;
    				firstTF = larger.get(largeIndex+1);
    				firstStart = largeIndex;
    				firstIndex = largeIndex+2;    				
    			}
    		
    			while(firstIndex <= firstStart + firstTF + 1 &&
    					secondIndex <= secondStart + secondTF + 1){
    				cmp = firstList.get(firstIndex) - secondList.get(secondIndex);
    				
    				if(cmp <= 0 && cmp >= -distance){
    					// Within range
    					list.add(smaller.get(smallIndex));
    					list.add(1);
    					df++;
    					break;
    				}  if (cmp < 0){
    					firstIndex++;
    				} else {
    					secondIndex++;
    				}
    			}
    			
    			smallIndex = smaller.nextDocPos();
    			largeIndex = larger.nextDocPos();
    		} else if (cmp < 0) {
    			smallIndex = smaller.nextDocPos();
    		} else {
    			largeIndex = larger.nextDocPos();
    		}
    	}
    	
    	list.set(0, df);
    	return list;
    }

	public Query cfold() {
		return this;
	}
	
	public String toString(){
		String str = "#NEAR/"+distance+"(";
		str += first +" "+second;
		return str + " )";
	}
}
