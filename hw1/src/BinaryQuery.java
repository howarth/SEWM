package src;

public abstract class BinaryQuery implements Query {

	protected Query first;
	protected Query second;
	
	public BinaryQuery(Query first, Query second){
		this.first = first;
		this.second = second;
	}
	
}
