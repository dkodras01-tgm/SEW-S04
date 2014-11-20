package kodrasritter.connection;

import java.io.IOException;

/**
 * Dieses Interface stellt einen Server dar, welcher Nachrichten an alle registrierten Clients schickt.<br>
 * Clients koennen an- und wieder abgemeldet werden.
 * 
 * @author Mathias Ritter 4AHIT
 * @version 1.0
 */
public interface Server extends Runnable {
	
	/**
	 * In dieser Methode wird ein neuer Client hinzugefuegt.<br>
	 * Alle hinzugefuegten Clients erhalten die Nachrichten, die an den Server gesendet wurden.
	 * 
	 * @param c Client
	 */
	public void addClient(Client c);
	
	/**
	 * In dieser Methode wird ein Client abgemeldet.<br>
	 * Das heisst, dass er ab sofort nicht mehr die Nachrichten des Servers erhaelt.
	 * 
	 * @param c
	 * @throws IOException 
	 */
	public void removeClient(Client c) throws IOException;
	
	/**
	 * In dieser Methode wird eine Nachricht an alle angemeldeten Clients verschickt.
	 * 
	 * @param s Zu sendende Nachricht
	 * @throws IOException Fehler wahrend der Kommunikation
	 */
	public void updateClients(String s) throws IOException;
	
	/**
	 * Mit Aufruf dieser Methode wird der Server beendet
	 * @throws IOException 
	 */
	public void beenden() throws IOException;

}
