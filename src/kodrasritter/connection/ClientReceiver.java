package kodrasritter.connection;

import java.io.IOException;

/**
 * Diese Klasse ist der ClientReceiver fuer einen Server.<br>
 * Der Server update alle Clients, sobald er eine Nachricht eines bestimmten Clients erhaelt.
 * 
 * @author Mathias Ritter 4AHIT
 * @version 1.0
 */
public class ClientReceiver implements Runnable {

	private Client c;
	private Server s;
	private boolean beenden;
	
	public ClientReceiver(Client c, Server s) {
		this.s = s;
		this.c = c;
		this.beenden = false;
	}
	
	@Override
	public void run() {
		while (!beenden) {
			String inputLine;
			try {
				//Nachricht vom Client empfangen
				while ((inputLine = c.getInput().readLine()) != null) {
					//Empfangene Nachrichten an alle Clients senden
					s.updateClients(inputLine);
				}
			} catch (IOException e) {
				try {
					s.removeClient(c);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
		
		
	}
	
	/**
	 * Setzt ein Attribut, um das Empfangen von Nachrichten eines Clients zu beenden
	 */
	public void beenden() {
		this.beenden = true;
	}

}
