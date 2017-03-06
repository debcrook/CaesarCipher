
public class frequencyCracker {

	public static void main(String[] args) {
		String inputFileName = args[0];
		String outputFileName;
		if(args.length >= 2){
			outputFileName = args[1];
		}
		else outputFileName = "output.txt";
		
		Cracker cracker = new Cracker();
		
		cracker.analyseText(inputFileName);
		cracker.decrypt(outputFileName);
	}

}
