package kodrasritter.message;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * Diese Klasse ersetzt boese Woerter in der Nachricht.
 * 
 * @author Dominik Kodras
 * @version 1.0
 */
public class Censorship extends Modifier{

	private HashMap<String, String> change;
	
	/**
	 * Im Konstruktor wird der super-Konstrukter aufgerufen
	 * 
	 * @param m
	 */
	public Censorship(Message m) {
		super(m);
	}
	
	/**
	 * Zensiertes Wort hinzufuegen
	 * 
	 * @param censoredWord
	 * @param newWord
	 */
	public void addCensoredWords(String censoredWord, String newWord) {
		change.put(censoredWord, newWord);
	}
	
	/**
	 * Zensiertes Wort entfernen
	 * 
	 * @param censoredWord
	 */
	public void removeCensoredWords(String censoredWord) {
		change.remove(censoredWord);
	}
	
	/** 
	 * Zensur wird ausgefuehrt
	 * 
	 * @return zensierte Nachricht
	 */
	public String process() {
		return censor(getMessage().process());
	}
	
	/**
	 * Woerter in der Nachricht werden Zensiert
	 * 
	 * @param content
	 * @return zensierte Nachricht
	 */
	public String censor(String content) {
		
		List<String> temp = Arrays.asList(content.split(" "));
		
		for (int i=0; i<temp.size(); i++) {
			if (change.containsKey(temp)) {
				temp.set(i, change.get(temp));
			}
		}
		
		StringBuilder sb = new StringBuilder();
		
		String trenner = "";
		for (String act : temp) {
			sb.append(trenner + act);
			trenner = " ";
		}
		
		return sb.toString();
	}
	
}
