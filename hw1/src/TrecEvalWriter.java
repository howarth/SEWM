package src;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;


public class TrecEvalWriter {
	
	PrintStream p;
	String runId;
	
	public TrecEvalWriter(String output, String rId) throws FileNotFoundException{
		FileOutputStream out = new FileOutputStream(output);
		p = new PrintStream(out);
		runId = rId;
	}
	
	public void writeQuery(String query, ScoreList list){
		String format = "%s\t %s\t %d\t %d\t 1.0\t %s\n";
		System.out.println(list.df());
		for(int i=list.df()-1; i>=0 && i>=list.df()-10000; i--){
			p.printf(format, query, "Q0", list.getDocId(i), list.df()-i, "run-1");
		}
	}
	
	public void close(){
		p.close();
	}
}
