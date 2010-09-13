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
	
	public static InvertedList getInvertedList(String term) throws IOException{
		FileReader file = new FileReader("/Users/danielhowarth/classes/f10/11441/hw1/invlists-s/" + term + ".inv");
		Reader stream = new BufferedReader(file);
		
		StreamTokenizer stok = new StreamTokenizer(stream);
		stok.parseNumbers();
		
		stok.nextToken();
		
		/* If file is of size 0 */
		if(stok.ttype == StreamTokenizer.TT_EOF){
			return null;
		}
		
		while(stok.ttype != StreamTokenizer.TT_NUMBER){
			stok.nextToken();
		}
		
		int ctf = (int) stok.nval;

		stok.nextToken();
		int df = 0;
		
		stok.nextToken();
		
		InvertedList invertedList = new InvertedList(df * 7);
		
		invertedList.add(null);
		int tf;
		
		while(stok.ttype != StreamTokenizer.TT_EOF && stok.ttype == StreamTokenizer.TT_NUMBER){
			/* Eat up each docid */
			invertedList.add((int)stok.nval);
			
			/* #body and body_freq */
			stok.nextToken();
			stok.nextToken();
			stok.nextToken();
			stok.nextToken();
			
			tf = (int) stok.nval;
			invertedList.add(tf);
			
			for(int i=0; i<tf; i++){
				stok.nextToken();
				invertedList.add((int) stok.nval);
			}
			
			df++;
			stok.nextToken();
		}		
		
		invertedList.set(0, df - 1);
		System.out.println(invertedList.size());
		return invertedList;
	}
	
	public static void main(String args[]) throws IOException{
		getInvertedList("family.body");
	}
	
}
