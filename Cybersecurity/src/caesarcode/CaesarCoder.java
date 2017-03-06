package caesarcode;

import java.util.Scanner;

public class CaesarCoder {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		System.out.println("Please enter key:");
		String key = scan.nextLine();
		Key k = new Key(key);
		
		while(true){
			System.out.println("Please enter e to encrypt, d to decrypt, or anything else to cancel:");
			String choice = scan.nextLine();
			String inFile, outFile;
			Text t;

			if(choice.equals("e")){
				System.out.println("Please enter filename for uncyphered text:");
				inFile = scan.nextLine();

				System.out.println("Please enter filename for cyphered text:");
				outFile = scan.nextLine();
				
				t  = new Text(inFile, outFile);
				t.encrypt(k);
			}
			else if(choice.equals("d")){
				System.out.println("Please enter filename for cyphered text:");
				inFile = scan.nextLine();

				System.out.println("Please enter filename for uncyphered text:");
				outFile = scan.nextLine();
				
				t = new Text(inFile, outFile);
				t.decrypt(k);
			}
			else break;
			
			t.printMap();
			t.printDecryptMap();
		}
		scan.close();
	}

}
