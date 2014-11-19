package kodrasritter.connection;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.HashMap;
import java.util.HashSet;

/**
 * Der ChatServer empfanegt Nachrichten der Clients und sendet diese an alle weiter.
 * Verbindet sich ein neuer Client, so wird dieser sofort registriert und erhaelt ebenfalls alle weiteren Nachrichten.<br>
 * Es koennen mehr als 2 Clients somit miteinander kommunizieren.
 * 
 * 
 * @author Mathias Ritter 4AHIT
 * @version 1.0
 *
 */
public class ChatServer implements Server {
	
	private ServerSocket server;
	private HashMap<Client, ClientReceiver> clientThreads;
	private HashSet<Client> clients;
	private NetworkControllable control;
	private boolean beenden;
	
	public ChatServer(NetworkControllable nc, int port) throws IOException {
		server = new ServerSocket(port);
		clientThreads = new HashMap<Client, ClientReceiver>();
		clients = new HashSet<Client>();
		
		beenden = false;
		
	}
	
	/**
	 * @see Server#addClient(Client)
	 */
	public void addClient(Client c) {
		ClientReceiver clientReceiver = (new ClientReceiver(c, this));
		new Thread(clientReceiver).start();
		clientThreads.put(c, clientReceiver);
		clients.add(c);
	}
	
	/**
	 * @see Server#removeClient(Client)
	 */
	public void removeClient(Client c) {
		clientThreads.get(c).beenden();
		clientThreads.remove(c);
		clients.remove(c);
			
	}
	
	/**
	 * @see Server#updateClients(String)
	 */
	public void updateClients(String s) throws IOException {
		for (Client c : clients) {
			c.getOutput().println(s);
		}
		
	}

	/**
	 * @see Server#beenden
	 */
	public void beenden() {
		beenden = true;
	}

	@Override
	public void run() {
		while(!beenden) {
			try {
				//Neue Clients werden hinzugefuegt, sobald sie sich zum Socket connecten
				Client c = new ChatClient(this.server.accept(), control);
				this.addClient(c);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}

}
