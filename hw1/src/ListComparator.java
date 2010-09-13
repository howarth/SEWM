package src;

import java.util.Comparator;
import java.util.List;

public class ListComparator implements Comparator{

	public int compare(Object o1, Object o2) {
		return ((List) o1).size() - ((List) o2).size(); 
	}

}
