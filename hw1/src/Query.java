package src;

import java.io.IOException;
import java.util.ArrayList;
/**
  *
  *
  */

public interface Query {

	public Query cfold();
	
    public QueryList invertedList() throws IOException;
    
    public String toString();
    
}
