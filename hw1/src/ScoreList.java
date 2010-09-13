package src;
import java.util.ArrayList;

public class ScoreList extends QueryList {

	public ScoreList(){
		super();
	}
	
	public ScoreList(int size){
		super(size);
	}
	
public static ScoreList convertInvertedList(QueryList invlist){
		/* Liberal estimation of size of list, so that it does not have to resize */
		int listSize = (new Double(invlist.size() * .75)).intValue(); 
		
		ScoreList list = new ScoreList(listSize);
		
		/* Add document frequency */
		list.add(invlist.get(0));
		int index = 0;
		
		for(int i=0; i<list.get(0); i++){
			index++;
			list.add(invlist.get(index));
			index += invlist.get(index+1) + 1;
			list.add(1);
		}
		
		return list;
	}
	
	public int getDocId(int index){
		return this.get(2*index + 1);
	}
	
	public int df(){
		return this.get(0);
	}

}
