package kodrasritter.connection;


/**
 * Dieses Schnittstelle gibt eine Methode zum Senden von Strings vor
 * 
 * @author Mathias Ritter 4AHIT
 * @version 1.0
 */
public interface Sendable {
	
	/**
	 * In dieser Methode soll ein angegebenen String gesendet werden.
	 * Gesendet bedeutet, dass dieser in einen Stream geschrieben wird.
	 * 
	 * @param content Der String, der gesendet werden soll
	 */
	public void send(String content);

}
