import java.util.Comparator;

public class LetterFreqComparator implements Comparator<LetterFreq> {
	
	public int compare(LetterFreq letter1, LetterFreq letter2){
		int freqDiff = letter1.getFreq() - letter2.getFreq();
		
		if (freqDiff != 0) return freqDiff;
		
		int letterDiff = (int) letter1.getLetter() - (int) letter2.getLetter();
		return letterDiff;
	}

}
