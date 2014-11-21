package kodrasritter.message;

/**
 * Abstrakte Klasse Modifier, erbt von Message
 * 
 * @author Dominik Kodras
 */
public abstract class Modifier extends Message {

	private Message m;
	
	/**
	 * Konstruktor setzt Message
	 * 
	 * @param m Zu setzende Message
	 */
	public Modifier(Message m) {
		this.setMessage(m);
	}
	

	/**
	 * Gibt die Nachricht zurueck
	 * 
	 * @return Nachricht
	 */
	public Message getMessage() {
		return m;
	}

	/**
	 * Setzt die Message
	 * 
	 * @param m Zu setzende Message
	 */
	public void setMessage(Message m) {
		this.m = m;
	}
	
	/**
	 * Fuehrt das veraendern der Nachricht aus
	 * 
	 * @return veraenderte Nachricht
	 */
	public abstract String process();
	

}
