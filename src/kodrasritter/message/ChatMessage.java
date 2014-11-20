package kodrasritter.message;

/**
 * Klasse erbt von Message
 * 
 * @author Dominik Kodras
 * @version 1.0
 */
public class ChatMessage extends Message {

	/**
	 * Gibt den Inhalt der Nachricht zurueck
	 * 
	 * @return Inhlat der Nachricht
	 */
	@Override
	public String process() {
		return super.getContent();
	}
	
}
