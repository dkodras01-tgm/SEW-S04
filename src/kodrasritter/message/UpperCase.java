package kodrasritter.message;

/**
 * Klasse die alle Buchstaben gross schreibt
 * 
 * @author Dominik Kodras
 * @version 1.0
 */
public class UpperCase extends Modifier {
	
	
	/**
	 * Konstruktor ruft den super-Konstruktor auf
	 * 
	 * @param m Message
	 */
	public UpperCase(Message m) {
		super(m);
	}
	
	/**
	 * Grossschreibung wird ausgefuehrt
	 * 
	 * @return grossgeschriebene Nachricht
	 */
	public String process() {
		return contentUppercase(getMessage().process());
	}
	
	/**
	 * Nachricht wird in Grossbuchstaben umgewandelt
	 * 
	 * @param content Inhalt einer Nachricht
	 * @return grossgeschriebene Nachricht
	 */
	private String contentUppercase(String content) {
		return content.toUpperCase();
	}

}
