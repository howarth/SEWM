public class TermQuery implements Query {
    
    private String term; 
    
    public TermQuery(String t){
        term = t;
    }
    
    public String toString(){
    	return term;
    }

}
