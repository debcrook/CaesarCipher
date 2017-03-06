import java.io.FileReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

public class Cracker {
	String text;
	String outputFileName;
	
	LetterFreq[] letterFrequencies;
	char[] standardFrequencies;
	
	public Cracker(){
		letterFrequencies = new LetterFreq[26];
		for(int i = 0; i < 26; i++){
			letterFrequencies[i] = new LetterFreq(65 + i);
		}
		
		standardFrequencies = new char[]{'Z','Q','X','J','K','V','B','W','P','Y','G','U','M','C','F','L','D','H','S','I','R',
				'N','O','A','T','E'};
	}
	
	
	/*Reads in the text & stores it, gets the frequency of each letter*/
	public void analyseText(String inFile){
		
		text = "";
		
		try{
			Scanner in = new Scanner(new FileReader(inFile));

			while(in.hasNextLine()){

				String nextWord = in.nextLine();
				
				for(int i = 0; i < nextWord.length(); i++){
					
					char nextChar = nextWord.charAt(i);
					text = text + nextChar;
					
					if (!Character.isLetter(nextChar)) continue;
					
					nextChar = Character.toUpperCase(nextChar);
					
					letterFrequencies[(int)nextChar - 65].increment();
				}
				
				
				text = text + '\n';

			}
			in.close();
		}
		
		catch (Exception ex){			
		}

		//print array of frequencies
		System.out.println("Frequencies by letter:");
		for(int i = 0; i < 26; i++){
			System.out.println(letterFrequencies[i].getLetter() + ": " + letterFrequencies[i].getFreq());
		}
		
		System.out.println();
	}
	
	/*sorts by letter frequency to find the likely decryption mapping*/
	private void findMapping(){
		LetterFreqComparator lfCompare = new LetterFreqComparator();
		
		Arrays.sort(letterFrequencies, lfCompare);
		
		System.out.println("Sorted frequencies:");
		for(int i = 0; i < 26; i++){
			System.out.println(letterFrequencies[i].getLetter() + ": " + letterFrequencies[i].getFreq());
		}
		System.out.println();
		
		
		System.out.println("Reconstructed map:");
		int[] inOrderMap = new int[26];
		
		for(int i = 0; i < 26; i++){
			int j;
			for(j = 0; j < 26; j++) 
				if (standardFrequencies[j] == (char) (i + 65)) break;
			inOrderMap[i] = j;
			
			System.out.print((char)(i + 65) + " ");
		}
		System.out.println();
		
		for(int i = 0; i < 26; i++){
			System.out.print(letterFrequencies[inOrderMap[i]].getLetter() + " ");
		}
		System.out.println();
		
	}
	
	/*decrypts the text according to the frequency analysis and outputs it to a file*/
	public void decrypt(String outFile){
		findMapping();
		
		try{
			PrintWriter out = new PrintWriter(outFile);
		
			for(int i = 0; i < text.length(); i++){

				if(!Character.isLetter(text.charAt(i))) {
					out.print(text.charAt(i));
					continue;
				}
				
				char encryptedLetter = Character.toUpperCase(text.charAt(i));
				
				//find where the letter falls in the table of encrypted letter frequencies
				int j;
				for(j = 0; j < 26; j++){
					if (letterFrequencies[j].getLetter() == encryptedLetter) break;
				}
				
				//print out the corresponding letter from the standard frequencies table
				out.print(standardFrequencies[j]);
				
			}
			out.close();
		}
		catch(Exception ex){
			
		}
	}

}
