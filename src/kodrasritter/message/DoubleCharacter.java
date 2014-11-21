package kodrasritter.message;

/**
 * Kasse um alle Buchstaben zwei mal aus zu geben.
 * 
 * @author Dominik Kodras
 * @version 1.0
 */
public class DoubleCharacter extends Modifier {


	/**
	 * Konstruktor ruft den super-Konstruktor von Modifier
	 * 
	 * @param m Message
	 */
	public DoubleCharacter(Message m) {
		super(m);
	}
	
	/**
	 * Verdopplung wird ausgefuehrt
	 * 
	 * @return Inhalt einer veraenderten Nachricht
	 */
	public String process() {
		return doubleCharacters(getMessage().process());
	}
	
	/**
	 * Alle Buchstaben werden verdoppelt
	 * 
	 * @param content Inhalt einer Nachricht
	 * @return verdoppelter Text
	 */
	public String doubleCharacters(String content) {
		
		StringBuilder sb = new StringBuilder();
		
		for (int i=0; i<content.length(); i++)
			for (int k=0; k<2; k++)
				sb.append(content.charAt(i));
		
		return sb.toString();
		
	}

}
