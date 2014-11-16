package kodrasritter.connection;

/**
 * Dieses Interface stellt eine Netzwerkschnittstelle zum Versenden/Empfangen von Nachrichten dar.<br>
 * Es koennen Nachrichten in Form eines Strings gesendet und Empfangen werden.<br>
 * Vor dem Senden/Empfangen muss eine Verbindung aufgebaut werden, danach sollte diese wieder geschlossen
 * werden.
 * 
 * @author Mathias Ritter 4AHIT
 * @version 1.0
 *
 */
public interface NetworkControllable {
	
	/**
	 * Diese Methode soll aufgerufen werden, wenn eine Nachricht von einer bereits aufgebauten
	 * Verbindung empfangen wird.<br>
	 * Der Inhalt der Nachricht wird als String uebergeben.
	 * 
	 * @param content Der Content, der empfangen wird.
	 */
	public void receive(String content);
	
	/**
	 * Diese Methode dient dem Senden von Nachrichten in einer bereits aufgebauten Verbindung
	 * in Form eines Strings. <br>
	 * Der Inhalt der Nachricht wird als String uebergeben.
	 * 
	 * @param content Der Content, der gesendet wird.
	 */
	public void send(String content);
	
	/**
	 * Diese Methode dient dem Schliessen einer bestehenden Verbindung.
	 * Sie soll am Ende der gesamten Kommunikation aufgerufen werden
	 */
	public void closeConnection();
	
	/**
	 * Diese Methode dient der Herstellung einer neuen Verbindung.
	 * Vor dem Senden/Empfangen muss diese aufgerufen werden.
	 * 
	 * @param ip Die IP-Adresse, an die die Nachricht geschickt werden soll
	 * @param port Der Port, ueber die die Kommunikation ablaufen soll
	 * @param ttl Die "Time-to-Live" des Pakets (max. Anzahl an Routern)
	 */
	public void initConnection(String ip, int port, int ttl);

	
}
