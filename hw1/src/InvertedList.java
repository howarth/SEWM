package src;
import java.util.ArrayList;

public class InvertedList extends QueryList {

	int pos;
	
	public InvertedList(){
		super();
		pos = 0;
	}
	
	public InvertedList(int size){
		super(size);
	}

	public int df() { return this.get(0); }
	
	/* Returns the position of the next docID */
	public int nextDocPos(){
		if(this.pos == 0)
			this.pos = 1;
		else
			this.pos = this.get(pos+1) + pos + 2;
		return this.pos;
	}
	
}
