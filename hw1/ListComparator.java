import java.util.Comparator;
import java.util.List;

public class ListComparator<E> implements Comparator<E>{

	public int compare(E o1, E o2) {
		return ((List) o1).size() - ((List) o2).size(); 
	}

}
