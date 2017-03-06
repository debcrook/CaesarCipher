
public class LetterFreq {
	
	private char letter;
	private int freq;
	
	public LetterFreq(int letterNum){
		letter = (char)letterNum;
		freq = 0;
	}

	public char getLetter() {
		return letter;
	}

	public int getFreq() {
		return freq;
	}
	
	public void increment(){
		freq++;
	}

}
