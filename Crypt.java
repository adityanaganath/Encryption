import java.io.*; //Input/ Output exception

public class Crypt {

	
	public static void main(String[] args) { 
		
		try  { //exception handling
			
			String input = args[0]; //first command line argument
			
			if (!(input.equals("-d") || input.equals( "-e"))) { 
				throw new IllegalArgumentException ("Please try again. Format: -(e/d)");
					//error checking
				
			}
			
			String word = args[1]; //second command line argument: keyWord
			
			if (!(word.substring(0,2).equals("-k"))) {
				throw new IllegalArgumentException("Please try again. Format: -k(KeyWord)");
			}	// must be in required format. otherwise error is thrown.
			
			String keyWord = word.substring(2,word.length()); //parses the keyWord by excluding -k.
			
			if (input.equals("-e")) { //calls encrypt method for -e
				Encryption.encrypt(keyWord, new File(args[2]), new File(args[3]));
				System.out.println("Please check your output folder!");
			}
			
			else if (input.equals("-d")) { //calls decrypt method for -d
				Decryption.decrypt(keyWord,new File(args[2]), new File(args[3]));
				System.out.println("Please check your output folder!");
			}
		}
			
		catch(FileNotFoundException e) //error handling for missing file
		{
			System.out.println("You entered one or more invalid filenames.");
			
		}
		
		catch(ArrayIndexOutOfBoundsException e) //error handling for missing command line args.
		{
			
			System.out.println("Please specify the required command line arguments.");
			System.out.println("Usage: <-(e/d)> <-k(KEY WORD)> <Input File> <Output File>");
		}
		
		catch(IllegalArgumentException e) { //error handling for input in incorrect format.
			System.out.println(e.getMessage());
		}

	}

}
