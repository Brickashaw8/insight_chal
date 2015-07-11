package textfiles;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.util.List;
import java.io.BufferedWriter;
import java.io.FileWriter;

public class FileData {
	public static void main(String[] args) {
		try{
			// Open the file
			FileInputStream fis = new FileInputStream("tweets.txt");
			BufferedReader br = new BufferedReader(new InputStreamReader(fis));
			String nextLine;
			HashMap<String, Integer> wordBank = new HashMap<String, Integer>();
			
			List twA = new ArrayList();
			int uniCount = 0;
			int lastVal = 0;
			//Read File Line By Line
			while ((nextLine = br.readLine()) != null)   { //next line exists
				uniCount = 0;
				HashMap<String, Integer> individualTweetBank = new HashMap<String, Integer>();
				
				String[] lineArr = nextLine.split(" "); 
				for (int i=0; i < lineArr.length; i++){
					String word = lineArr[i];
					
					if (!wordBank.containsKey(word)) { //first time seeing word
						wordBank.put(word, 1);
						
					} 
					
					else { //we have seen the word before
						int seen = wordBank.get(word); 			
						seen++; //add 1 to the count since we’ve seen it one more time
						wordBank.put(word, seen); //replace the old count with the new count
					}

					if (!individualTweetBank.containsKey(word)) { //needed to keep uniCount correct
						individualTweetBank.put(word, 1);
						uniCount++;
					} else {
						int seen = individualTweetBank.get(word);
						seen++;
						individualTweetBank.put(word,seen);
					}

					
				}
				//median value here
				twA.add(uniCount);
				if (!twA.isEmpty()) {
			        lastVal = (int) twA.get(twA.size() - 1);
			    }				
				double median = ((lastVal + (int)twA.get(0))/2.0);
				System.out.println(median); //print to text file
				FileWriter w2 = new FileWriter("ft2.txt", true);
	            BufferedWriter bW2 = new BufferedWriter(w2);
	 
	            bW2.write(String.valueOf(median)); //convert double to string
	            bW2.newLine();
	            bW2.close();
			}
			
			//Close the input stream
			br.close();
			
			for (String word : wordBank.keySet()) { //print all the words and their counts
				FileWriter w1 = new FileWriter("ft1.txt", true);
	            BufferedWriter bW1 = new BufferedWriter(w1);
	 
	            bW1.write(word + ":" + wordBank.get(word));
	            bW1.newLine();
	 
	            bW1.close();
				System.out.println(word + ":" + wordBank.get(word));  //print to text file
			}
				
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	
	}
	
	
}

	


