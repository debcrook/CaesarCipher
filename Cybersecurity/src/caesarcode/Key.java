package caesarcode;

public class Key {
	int keyLength;
	String key;
	
	public Key(String rawkey){
		keyLength = rawkey.length();
		
		key = "";
		
		//construct the fixed key
		for(int i = 0; i < keyLength; i++){
			
			//ignore anything that isn't a letter
			if(Character.isLetter(rawkey.charAt(i))){
				
				//convert character to upper case
				char charTest = Character.toUpperCase(rawkey.charAt(i));
				
				//add to key if not already in it
				if(key.indexOf(charTest) == -1) key = key + charTest;
			}
		}
		
		System.out.println("Key is " + key);
	}

	public String getKey() {
		return key;
	}

	public int getKeyLength() {
		return keyLength;
	}

}
