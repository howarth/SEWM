package src;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.io.StreamTokenizer;

import java.util.ArrayList;

public class InvertedListParser {

	private String folder;
	
	public InvertedListParser(String folder){
		this.folder = folder;
	}
	
	public static void getInvertedList(String term) throws IOException{
		FileReader file = new FileReader("/home/dan/classes/f10/11441/hw1/invlists/invlists-s/" + term);
		Reader stream = new BufferedReader(file);
		
		StreamTokenizer stok = new StreamTokenizer(stream);
		stok.parseNumbers();
		
		stok.nextToken();
		while(stok.ttype != StreamTokenizer.TT_NUMBER){
			stok.nextToken();
		}
		
		int ctf = (int) stok.nval;
		stok.nextToken();
		int df = (int) stok.nval;
		
		ArrayList<Integer> invertedList = new ArrayList<Integer>(df * 7);
		
		
		while(stok.ttype == StreamTokenizer.TT_NUMBER){
			invertedList.add((int)stok.nval);
			stok.nextToken();
		}		
		
		System.out.println(invertedList.size());
	}
	
	public static void main(String args[]) throws IOException{
		getInvertedList("obama.body.inv");
	}
	
}
