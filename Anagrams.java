//package anagrams;

//Tyler Soiferman "I pledge my honor that I have abided by the Stevens Honor System."

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class Anagrams {
	final Integer[] primes =  
			{2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 
			31, 37, 41, 43, 47, 53, 59, 61, 67, 
			71, 73, 79, 83, 89, 97, 101};
	Map<Character,Integer> letterTable = new HashMap<>();
	Map<Long,ArrayList<String>> anagramTable = new HashMap<>();
	
	//building the letter table
	public void buildLetterTable() {
		final Character[] letters = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
		//puts the letters in the letter table
		for (int i = 0; i<26; i++) {
			letterTable.put(letters[i], primes[i]);
		}
	}

	Anagrams() {
		buildLetterTable();
		anagramTable = new HashMap<Long,ArrayList<String>>();
	}
	
	//adds word to anagram table
	public void addWord(String s) {
		if (anagramTable.get(myHashCode(s)) == null) {
			ArrayList<String> the_list = new ArrayList<String>();
			the_list.add(s);
			anagramTable.put(myHashCode(s), the_list);
		} 
		else {
			ArrayList<String> the_list = anagramTable.get(myHashCode(s));
			the_list.add(s);
			anagramTable.replace(myHashCode(s), the_list);
		}
	}
	
	//gets a hash code for an anagram
	public long myHashCode(String s) {
		    if (s == "") {
		    	throw new IllegalArgumentException("Empty string!");
		    }
		    long the_hash = 1;
		    for (int i = 0; i < s.toCharArray().length; i++) {
		    	the_hash = the_hash * letterTable.get(s.toCharArray()[i]);
		    }
		    return the_hash;
	}
	
	//processes file (given in template)
	public void processFile(String s) throws IOException {
		FileInputStream fstream = new FileInputStream(s);
		BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
		String strLine;
		while ((strLine = br.readLine()) != null)   {
		  this.addWord(strLine);
		}
		br.close();
	}
	
	//gets the max entry (or entries if multiple)
	public ArrayList<Map.Entry<Long,ArrayList<String>>> getMaxEntries() {
	    ArrayList<Map.Entry<Long, ArrayList<String>>> maximum = new ArrayList<Map.Entry<Long, ArrayList<String>>>();
	    for (long key:anagramTable.keySet()) {
	    	if (maximum.size() == 0) {
	    		maximum.add(Map.entry((Long) key, anagramTable.get(key)));
	    	}
	    	else if (anagramTable.get(key).size() > maximum.get(0).getValue().size()) {
	    		maximum.clear();
	    		maximum.add(Map.entry((Long) key, anagramTable.get(key)));
	    	}
	    	else if (anagramTable.get(key).size() == maximum.get(0).getValue().size() ) {
	    		maximum.add(Map.entry((Long) key, anagramTable.get(key)));
	    	}
	    }
	    return maximum;
	}
	
	//main
	public static void main(String[] args) {
		Anagrams a = new Anagrams();
		final long startTime = System.nanoTime();    
		try {
			a.processFile("words_alpha.txt");
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		ArrayList<Map.Entry<Long,ArrayList<String>>> maxEntries = a.getMaxEntries();
		final long estimatedTime = System.nanoTime() - startTime;
		final double seconds = ((double) estimatedTime/1000000000);
		System.out.println("Time: "+ seconds);
		System.out.println("List of max anagrams: "+ maxEntries);
	}
}
