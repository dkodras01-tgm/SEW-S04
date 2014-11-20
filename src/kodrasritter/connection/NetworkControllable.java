package kodrasritter.connection;

import java.io.IOException;

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
	 * Diese Methode empfaengt neue Nachrichten und updatet die Anzeige (Display),
	 * wenn eine neue Nachricht empfangen wird.<br>
	 * Voraussetzung ist eine bereits aufgebaute Verbindung mit {@link #initConnection(String, int)}.<br>
	 * Diese Methode wird am Ende von {@link #initConnection(String, int)} aufgerufen
	 * 
	 * @throws IOException Fehler waehrend der Kommunikation
	 */
	public void receive(String content) throws IOException;
	
	/**
	 * Diese Methode dient dem Senden von Nachrichten in Form eines Strings. <br>
	 * Voraussetzung ist eine mit {@link #initConnection(String, int)} bereits
	 * aufgebaute Verbindung.
	 * 
	 * @param content Der Content, der gesendet wird.
	 * @throws IOException Fehler beim Senden (z.B. Verbindung noch nicht initialisiert)
	 */
	public void send(String content) throws IOException;
	
	/**
	 * Diese Methode dient dem Schliessen einer bestehenden Verbindung.
	 * Sie soll am Ende der gesamten Kommunikation aufgerufen werden
	 * 
	 * @throws IOException Fehler beim Beenden der Kommunikation (z.B. Beenden ohne vorherigen Start)
	 */
	public void closeConnection() throws IOException;
	
	/**
	 * Diese Methode dient der Herstellung einer neuen Verbindung.
	 * Vor dem erstmaligen Senden muss diese ein Mal aufgerufen werden.
	 * 
	 * @param ip Die IP-Adresse, an die die Nachricht geschickt werden soll
	 * @param port Der Port, ueber die die Kommunikation ablaufen soll
	 * @throws IOException Fehler beim Initialisieren der Verbindung
	 */
	public void initConnection(String ip, int port) throws IOException;


	
}
