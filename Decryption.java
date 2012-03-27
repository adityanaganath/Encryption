import java.io.File; 
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.PrintWriter;

public class Decryption { //see comments for encryption class
						//other than for code dealing with decryption part
	
	private final static String[] alphabets = {"A", "B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};
	private static String [] cipherLetters = new String [26];
	
	public Decryption () {
		
	}
	
	public static void decrypt(String keyWord, File source, File target) throws FileNotFoundException {
		
		for (int i = 0; i<keyWord.length()-1; i++) {
			for (int j = i+1; j<keyWord.length(); j++) {
				if (j==keyWord.length()-1 && keyWord.substring(i,i+1).equals(keyWord.substring(j,j+1)))
					keyWord=keyWord.substring(0,j);
				else while (j<keyWord.length() && keyWord.substring(i,i+1).equals(keyWord.substring(j,j+1))) {
					keyWord=keyWord.substring(0,j)+keyWord.substring(j+1);
				}
						
			}
		}
		
		int curIndex=0;
		for (int i=0; i<keyWord.length(); i++){
			cipherLetters[curIndex]=keyWord.substring(i,i+1);
			curIndex++;
		}
		
		for (int i=alphabets.length-1; i>=0; i--){
			if (!(keyWord.contains(alphabets[i]))){
				cipherLetters[curIndex]=alphabets[i];
				curIndex++;
			}
		}
		
		//for (int i =0; i<cipherLetters.length; i++) {
			//System.out.println(cipherLetters[i]);
		//}
		
		PrintWriter writer = new PrintWriter(target);
		Scanner readSource = new Scanner(source);
		
		while(readSource.hasNextLine()) {
			String word = readSource.nextLine();
			String newString="";
			
			for (int i = 0; i<word.length(); i++) {
				boolean isLetter=false;
				for (int j= 0; j<cipherLetters.length; j++) {
					if (cipherLetters[j].equalsIgnoreCase(word.substring(i,i+1))) {//finds required cipher letter
						newString+= alphabets[j]; //replaces the cipher letter with corresponding alphabet
						isLetter=true;
					}
				}
				
				if (!isLetter)
					newString+=word.substring(i,i+1);
			}
			
			writer.print(newString+"\n");
			
			
		
	}
		
		writer.close();
	
}


}
