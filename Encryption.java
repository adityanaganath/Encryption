import java.io.File;
import java.io.FileNotFoundException; // error detection
import java.util.Scanner;
import java.io.PrintWriter; // allows us to write onto an output file

public class Encryption {

	private final static String[] alphabets = {"A", "B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};
	private static String [] cipherLetters = new String [26]; //length will be fixed at 26
	

	public Encryption(){
	
	}
	
	
	public static void encrypt (String keyWord, File source, File target) throws FileNotFoundException { 
		
		for (int i = 0; i<keyWord.length()-1; i++) { //for removing duplicates from keyWord
			for (int j = i+1; j<keyWord.length(); j++) {
				if (j==keyWord.length()-1 && keyWord.substring(i,i+1).equals(keyWord.substring(j,j+1)))
					keyWord=keyWord.substring(0,j); //if duplicate letter is last
				else while (j<keyWord.length() && keyWord.substring(i,i+1).equals(keyWord.substring(j,j+1))) {
					keyWord=keyWord.substring(0,j)+keyWord.substring(j+1); //executes if we have duplicates together
	
				}
						
			}
		}
		
		int curIndex=0;
		for (int i=0; i<keyWord.length(); i++){
			cipherLetters[curIndex]=keyWord.substring(i,i+1);
			curIndex++; //Adding keyWord to cipher letters
		}
		
		for (int i=alphabets.length-1; i>=0; i--){ //looping in reverse order
			if (!(keyWord.contains(alphabets[i]))){
				cipherLetters[curIndex]=alphabets[i];
				curIndex++; //Adding remaining letters of alphabet in reverse order
			}
		}
		
		for (int i =0; i<cipherLetters.length; i++) {
			System.out.println(cipherLetters[i]);
		}
		
		PrintWriter writer = new PrintWriter(target); //Will write onto output file: target
		Scanner readSource = new Scanner(source); //Accepts input file
		
		while(readSource.hasNextLine()) { //Scans individual line
			
			String word = readSource.nextLine();
			String newString="";
			
			for (int i = 0; i<word.length(); i++) {
				boolean isLetter=false; //for identifying a letter
				for (int j= 0; j<alphabets.length; j++) {
					if (alphabets[j].equalsIgnoreCase(word.substring(i,i+1))) {
						newString+=cipherLetters[j];
						isLetter=true; //replaces relevant alphabet letter with cipher letter
					}
				}
				if (!isLetter)
					newString+=word.substring(i,i+1); //Adds special characters not part of the alphabet
			}
			
			writer.print(newString+"\n"); // writes the encrypted string onto output file
		}
		
		writer.close(); //closes writer once it is done because pointer is at end.
		
	}

		
		


	
	
		
	

}