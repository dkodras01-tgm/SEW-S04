package kodrasritter.connection;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Dieses Interface stellt einen Client dar, welche einfache Nachrichten in Form eines Strings von
 * einem Server empfaengt.<br>
 * 
 * Der Client kann neue Nachrichten an einen Server senden.
 * 
 * @author Mathias Ritter 4AHIT
 * @version 1.0
 *
 */
public interface Client extends Runnable {
	
	/**
	 * Gibt den aktuellen BufferedReaderzum Empfangen von Nachrichten zurueck
	 * 
	 * @return BufferedReader des Clients
	 */
	public BufferedReader getInput();
	
	/**
	 * Gibt den aktuellen PrintWriter zum Senden von Nachrichten zurueck
	 * 
	 * @return Printwriter des Clients
	 */
	public PrintWriter getOutput();
	
	/**
	 * Gibt den Socket des Clients zurueck
	 * 
	 * @return Socket des Clients
	 */
	public Socket getSocket();
	
	/**
	 * In dieser Methode werden die Nachrichten des Clients an den Server gesendet
	 * 
	 * @param content Inhalt der zu sendenen Nachricht
	 * @throws IOException Fehler waehrend der Kommunikation
	 */
	public void send(String content) throws IOException;

}
