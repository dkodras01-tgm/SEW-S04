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
				while ((inputLine = c.getInput().readLine()) != null) {
					s.updateClients(inputLine);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		
	}
	
	public void beenden() {
		this.beenden = true;
	}

}
