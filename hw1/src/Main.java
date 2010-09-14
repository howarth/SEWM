package src;
import java.io.BufferedReader;
import java.io.FileReader;


public class Main {

	public static void main(String[] args) throws Exception{
		FileReader file = new FileReader(args[0]);
		BufferedReader stream = new BufferedReader(file);
		
		String outputFile = args[0] + "_" + args[1] + "_" + args[2];
		String line;
		String query[];
		QueryParser qp = new QueryParser(null);
		TrecEvalWriter trec_eval = new TrecEvalWriter(outputFile, args[2], Integer.parseInt(args[1]));
		Query q;
		QueryList ql;
		
		while( (line = stream.readLine()) != null){
			query = line.split(":");

			qp.newQuery(query[1]);
			//System.out.println("Query:          "+ query[1]);
			q = qp.getQuery();
			q = q.cfold();

			ql = q.invertedList();
			
			trec_eval.writeQuery(query[0], (ScoreList) ql);
		}
		
		trec_eval.close();
		
	}
}
