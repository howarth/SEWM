package src;
import java.util.ArrayList;

public class ScoreList extends ArrayList<Integer> {

	public ScoreList(){
		super();
	}
	
	public ScoreList(int size){
		super(size);
	}
	
	public static ScoreList convertInvertedList(InvertedList invlist){
		/* Liberal estimation of size of list, so that it does not have to resize */
		int listSize = (new Double(invlist.size() * .75)).intValue(); 
		
		ScoreList list = new ScoreList(listSize);
		
		/* Add document frequency */
		list.add(invlist.get(0));
		int index = 0;
		
		for(int i=0; i<list.get(0); i++){
			index++;
			list.add(invlist.get(index));
		}
	}

}
