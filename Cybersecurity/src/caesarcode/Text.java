package caesarcode;

import java.io.FileReader;
import java.io.PrintWriter;
import java.util.Scanner;

public class Text {
	
	String inFileName, outFileName;
	
	//The input text is either the unencrypted text or the encrypted text
	//depending on what the user's choice was
	String inText;
	
	char[] map;			//stores the encryption map
	char[] decodeMap;	//stores the decryption map
	
	@SuppressWarnings("resource")
	public Text(String inFile, String outFile){
		inFileName = inFile;
		outFileName = outFile;
		map = new char[26];
		decodeMap = new char[26];
		inText = "";
		
		//read input text
		try{
			Scanner in = new Scanner(new FileReader(inFileName));
			while(in.hasNextLine()){
				inText = inText + in.nextLine() + "\n";
			}		
		}
		
		catch (Exception ex){			
		}
		
	}
	
	/*This method builds both the encryption and decryption maps based on the key*/
	/*The encryption is a modified Caesar cipher*/
	private void constructMap(Key key){
		
		//initial offset
		int offset = (key.getKeyLength() - 1)%26;
		
		String keyText = key.getKey();
		
		//tracks how many elements of the map have been stored so far
		int mapCount = 0;

		//tracks which character is most recently added
		//so it can move to the next one along for the second part of the algorithm
		char charCurrent = 'A';
		
		//add the characters from the key text
		//(since all characters are upper-case
		//they have int values in the range 65-90
		//which are mapped to the array indices 0-25)
		
		for(int i = 0; i < keyText.length(); i++){
			charCurrent = keyText.charAt(i);
			
			//store the encryption map
			map[(mapCount + offset)%26] = charCurrent;			//the % makes sure it loops round if it reaches the end of the array
			
			//store the opposite pairing for the decryption map
			//(must make sure the charCurrent is converted to a number between 0 and 25)
			decodeMap[(int)charCurrent - 65] = (char)((mapCount + offset) %26 + 65);
			
			mapCount++;
		}
		
		//now add the rest of the characters until the array is full
		while(mapCount < 26){
			
			//move to the next character
			int x = (int) charCurrent + 1;
			x = (x-65)%26 + 65;				//correct it by looping around if it has reached Z
			charCurrent = (char)x;
			
			//add the new character if it's not already present (i.e. was added earlier in the key text)
			if(keyText.indexOf(charCurrent) == -1){
				
				map[(mapCount + offset) %26] = charCurrent;
				decodeMap[(int)charCurrent - 65] = (char)((mapCount + offset) %26 + 65);
				mapCount++;
			}
		}
	}
	
	/*Encrypts the input file based on given key and writes it to the output file*/
	public void encrypt(Key key){
		constructMap(key);
		
		try{
			PrintWriter out = new PrintWriter(outFileName);
		
			for(int i = 0; i < inText.length(); i++){

				if(Character.isLetter(inText.charAt(i))){
					//convert everything to upper case
					char charToCrypt = Character.toUpperCase(inText.charAt(i));

					//calculate the encrypted form from the encryption map array
					char crypted = map[((int)charToCrypt - 65)];
					
					out.print(crypted);
				}

				//non-letter characters are just written directly
				else out.print(inText.charAt(i));
				
			}
			out.close();
		}
		catch(Exception ex){
			
		}

		
	}
	
	/*Decrypts the input file based on the given key and writes it to an output file*/
	public void decrypt(Key key){
		constructMap(key);
		
		try{
			PrintWriter out = new PrintWriter(outFileName);
		
			for(int i = 0; i < inText.length(); i++){

				if(Character.isLetter(inText.charAt(i))){
					//convert everything to upper case
					char charToDecrypt = Character.toUpperCase(inText.charAt(i));

					//calculate the decrypted form from the decryption map array
					char decrypted = decodeMap[((int)charToDecrypt - 65)];
					out.print(decrypted);
				}

				//non-letter characters are just written directly
				else out.print(inText.charAt(i));;
				
			}
			out.close();
		}
		catch(Exception ex){
			
		}
		
	}
	
	/*Prints the encryption map array*/
	public void printMap(){
		System.out.println("Encryption map:");

		for(int i = 0; i < 26; i++){
			System.out.print((char)(i + 65) + " ");
		}
		System.out.println();
		for(int i = 0; i < 26; i++){
			System.out.print(map[i] + " ");
		}
		System.out.println("\n");
	}
	
	/*Prints the decryption map array*/
	public void printDecryptMap(){
		System.out.println("Decryption map:");

		for(int i = 0; i < 26; i++){
			System.out.print((char)(i + 65) + " ");
		}
		System.out.println();
		for(int i = 0; i < 26; i++){
			System.out.print(decodeMap[i] + " ");
		}
		
		System.out.println("\n");
	}

}
