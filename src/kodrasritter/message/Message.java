package kodrasritter.message;

/**
 * Abstrakte Klasse Message, 
 * 
 * @author Dominik Kodras
 * @version 1.0
 */
public abstract class Message {
	
	private String content;
	
	/**
	 * Gibt den Inhalt der Nachricht zurueck
	 * 
	 * @return Inahlt der Nachricht
	 */
	public String getContent() {
		return this.content;
	}
	
	/**
	 * Setzt den Inhalt der Nachricht
	 * 
	 * @param content Inhalt einer Nachricht, der gesetzt werden soll
	 */
	public void setContent(String content) {
		this.content = content;
	}
	
	/**
	 * Fuehrt das veraendern der Nachricht aus
	 * 
	 * @return veraenderte Nachricht
	 */
	public abstract String process();

}
