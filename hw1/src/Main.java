package src;
import java.io.BufferedReader;
import java.io.FileReader;


public class Main {

	public static void main(String[] args) throws Exception{
		FileReader file = new FileReader("hw1.query.test.txt");
		BufferedReader stream = new BufferedReader(file);
		
		String line;
		String query[];
		QueryParser qp = new QueryParser(null);
		TrecEvalWriter trec_eval = new TrecEvalWriter("output.txt", "first");
		Query q;
		QueryList ql;
		
		while( (line = stream.readLine()) != null){
			query = line.split(":");
			qp.newQuery(query[1]);
			System.out.println("Query: "+ query[1]);
			q = qp.getQuery().cfold();
			System.out.println(q);
			
			ql = q.invertedList();
			
			trec_eval.writeQuery(query[0], (ScoreList) ql);
		}
		
		trec_eval.close();
		
	}
}
